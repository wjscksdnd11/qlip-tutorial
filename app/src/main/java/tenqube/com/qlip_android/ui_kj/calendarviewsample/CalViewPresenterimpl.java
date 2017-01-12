package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.SearchData;
import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.UserTransactionData;

import static tenqube.com.qlip_android.ui_kj.calendarviewsample.CalViewActivity.COUNT_PAGE;
import static tenqube.com.qlip_android.util.LogUtil.LOGI;

class CalViewPresenterimpl implements CalViewPresenter {

    private static boolean refreshTag = false;
    private CalViewActivity mActivity;
    private CalViewModel mModel;
    private AdapterFrgCalendar adapterFrgCalendar;
    private Context mContext;
    private ArrayList<Long> mSelectedIdentifierList = new ArrayList<>();
    private ArrayList<UserTransactionData> mUserTransactionDataList = new ArrayList<>();

    CalViewPresenterimpl(CalViewActivity activity) {
        this.mActivity = activity;
        this.mModel = new CalViewModel(activity);
        this.mContext = activity;
    }

    @Override
    public void settingToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.qlip_main_black_lv0));
    }

    @Override
    public void changeOption(int spinnerType, int sumType) {
        adapterFrgCalendar.setOption(spinnerType, sumType);
    }


    @Override
    public String[] queryStartDate(long timeByMillis) {
        return mModel.queryStartDate(timeByMillis);
    }

    @Override
    public ArrayList<SearchStringData> changeSearchData(SearchData searchData) {
        return mModel.setSearchStringData(searchData);
    }

    @Override
    public void setSearchData(ArrayList<SearchStringData> searchStringDatas) {
        adapterFrgCalendar.setSearchData(searchStringDatas);
    }

    @Override
    public void refreshView() {
        if (adapterFrgCalendar != null) {
            LOGI(getClass().getSimpleName(), "refreshView");
            adapterFrgCalendar.notifyDataSetChanged();
        }
    }


    @Override
    public void setTag(boolean refresh) {
        refreshTag = refresh;
        mActivity.invalidateOptionsMenu();
        mActivity.ACTIONBAR_MODE = 1;

    }

    @Override
    public boolean refreshTag() {
        return refreshTag;
    }

    @Override
    public void selectedIdentifierList(int position) {
        boolean checked = false;
        if (mActivity.ACTIONBAR_MODE == 1 && mActivity.getSupportActionBar() != null) {
            if (mSelectedIdentifierList.contains(mUserTransactionDataList.get(position).identifier)) {
                checked = true;
            }
            if (!checked) {
                mSelectedIdentifierList.add(mUserTransactionDataList.get(position).identifier);
            } else {
                mSelectedIdentifierList.remove(mUserTransactionDataList.get(position).identifier);
            }

            mActivity.getSupportActionBar().setTitle(mSelectedIdentifierList.size() + "개 선택됨");
        }
    }


    @Override
    public void removeIdentifier() {
        mModel.removeIdentifireList(mSelectedIdentifierList);
        adapterFrgCalendar.notifyDataSetChanged();
    }

    @Override
    public void setUserTransactionDataList(ArrayList<UserTransactionData> mList) {
        if (mList != null) {
            this.mUserTransactionDataList = mList;
        }
    }

    @Override
    public void clearIdentifier() {
        mSelectedIdentifierList.clear();
    }

    @Override
    public boolean checkIdentifier(long identifier) {

        return mSelectedIdentifierList.contains(identifier);
    }


    @Override
    public void initControl(final ViewPager pager, int spinner_type, int sum_type) {
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, mActivity.getResources().getDisplayMetrics());
        adapterFrgCalendar = new AdapterFrgCalendar(mActivity.getSupportFragmentManager());
        pager.setAdapter(adapterFrgCalendar);
        pager.setPageMargin(pageMargin);
        adapterFrgCalendar.setNumOfMonth(COUNT_PAGE);
        pager.setCurrentItem(COUNT_PAGE);
        final PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) mActivity.findViewById(R.id.tabLayout);
        pagerSlidingTabStrip.setViewPager(pager);
        pagerSlidingTabStrip.setIndicatorColor(ContextCompat.getColor(mContext, R.color.qlip_main_green));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    adapterFrgCalendar.addPrev();
                    pagerSlidingTabStrip.notifyDataSetChanged();
                    adapterFrgCalendar.notifyDataSetChanged();
                    pager.setCurrentItem(COUNT_PAGE, false);
                } else if (position == adapterFrgCalendar.getCount() - 1) {
                    adapterFrgCalendar.addNext();
                    pagerSlidingTabStrip.notifyDataSetChanged();
                    adapterFrgCalendar.notifyDataSetChanged();
                    pager.setCurrentItem(adapterFrgCalendar.getCount() - (COUNT_PAGE + 1), false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public HashMap<String, ArrayList<UserTransactionData>> spentDataMap(String start_date, String end_date, int spinner_type, int sumType, ArrayList<SearchStringData> searchStringDataList) {
        return mModel.loadSpentDataMap(start_date, end_date, spinner_type, sumType, searchStringDataList);
    }


}
