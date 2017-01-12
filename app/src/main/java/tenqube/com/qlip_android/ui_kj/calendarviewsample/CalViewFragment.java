package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.ui_kj.caledarwidget.CalendarManager;
import tenqube.com.qlip_android.ui_kj.caledarwidget.CalendarWidgetItemView;
import tenqube.com.qlip_android.ui_kj.caledarwidget.CalendarWidgetView;
import tenqube.com.qlip_android.ui_kj.caledarwidget.ResizeManager;
import tenqube.com.qlip_android.ui_kj.detail.DetailTransactionActivity;
import tenqube.com.qlip_android.util.DecimalFormatUtil;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;

public class CalViewFragment extends Fragment implements View.OnClickListener{
    private static final String PARENTPOSITION = "parentPosition";
    private static final String TIME_BY_MILLIS = "timeByMillis";
    private static final String SPINNER_TYPE = "spinnerType";
    private static final String SUM_TYPE = "sumType";
    private static final String SEARCH_STRING_DATA_LIST = "searchStringDataList";
    private static final int MONTH_DAY_COUNT = 42;
    CalendarWidgetView mCalendarView;
    private long mTimeByMillis;
    private CalViewPresenter mCalViewPresenter;
    private int spinnerType;
    private int sumType;
    private HashMap<String, ArrayList<UserTransactionData>> mSpentDataMap;
    private CalViewFrgAdapter mAdapter;
    private Context mContext;
    private ArrayList<SearchStringData> mSearchStringDataList;
    private String mKey;
    private ResizeManager mResizeManager;
    private CalendarManager mManager;
    public static CalViewFragment newInstance(int position, Long mTimeByMillis, int spinnerType, int sumType, ArrayList<SearchStringData> searchStringDataList) {
        CalViewFragment frg = new CalViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARENTPOSITION, position);
        bundle.putLong(TIME_BY_MILLIS, mTimeByMillis);
        bundle.putInt(SPINNER_TYPE, spinnerType);
        bundle.putInt(SUM_TYPE, sumType);
        bundle.putSerializable(SEARCH_STRING_DATA_LIST, searchStringDataList);
        frg.setArguments(bundle);
        return frg;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContext = getActivity().getApplicationContext();
            this.mTimeByMillis = getArguments().getLong(TIME_BY_MILLIS);
            this.spinnerType = getArguments().getInt(SPINNER_TYPE);
            this.sumType = getArguments().getInt(SUM_TYPE);
            this.mSearchStringDataList = (ArrayList<SearchStringData>) getArguments().getSerializable(SEARCH_STRING_DATA_LIST);
        }
        LOGI("onCreate", "onCreate !!" + mTimeByMillis);
        CalViewActivity calViewActivity = (CalViewActivity) getActivity();
        if (calViewActivity.getPresenter() != null) {
            mCalViewPresenter = calViewActivity.getPresenter();
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_cal_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCalendarView = (CalendarWidgetView) view.findViewById(R.id.calendar_widget);
        CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, LocalDate.now(), LocalDate.now().plusYears(1));
        mCalendarView.init(manager);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            BlockScrollLinearLayoutManager linearLayoutManager = new BlockScrollLinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            mAdapter = new CalViewFrgAdapter(getContext(), mCalViewPresenter);
            recyclerView.setAdapter(mAdapter);
        }
        addCalendarItemView();
        initView();
        loadData();


        mAdapter.setOnClickSpentItmeListener(new CalViewFrgAdapter.OnClickSpentItemListener() {
            @Override
            public void onReceivedIdentifier(long identifier) {
                Constants.lv2_refresh = true;
                Intent intent = new Intent(getContext(), DetailTransactionActivity.class);
                intent.putExtra(Constants.IntentParameter.IDENTIFIER, identifier);
                getActivity().startActivity(intent);
            }
        });
        mAdapter.setOnClickRemoveIdentifierListener(new CalViewFrgAdapter.OnSpentItemRemoveListenter() {
            @Override
            public void onReceiveRemoveIdentifierList(int position) {
                mCalViewPresenter.selectedIdentifierList(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        mCalendarView.setOnCalendarItemSelectedListener(new CalendarWidgetView.onCalendarItemSelectedListener() {
            @Override
            public void onSelectedItem(long millis, View v, boolean isDoubleClicked) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(millis);
                mKey = calendar.get(Calendar.YEAR) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.MONTH) + 1) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.DATE));
                if (isDoubleClicked) {
                    Toast.makeText(mContext, "double Click!!", Toast.LENGTH_SHORT).show();
                }
                if (mSpentDataMap != null && mSpentDataMap.containsKey(mKey)) {
                    mAdapter.addAll(millis, mSpentDataMap.get(mKey));
                } else {
                    mAdapter.addAll(millis, null);
                }

            }
        });

        //// FIXME: 2016. 12. 22.
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Constants.lv2_refresh) {
            loadData();
        }
    }

    private ArrayList<CalendarWidgetItemView> mCalendarWidgetItemViewArrayList = new ArrayList<>();

    private void addCalendarItemView() {
        if (mCalendarWidgetItemViewArrayList.size()==0) {
            for (int i = 0; i < 49; i++) {
                CalendarWidgetItemView calendarWidgetItemView = new CalendarWidgetItemView(mContext);
                mCalendarView.addChildView(calendarWidgetItemView);
            }
        }
    }

    private ArrayList<String> mKeyList = new ArrayList<>();
    private String mStartDate;
    private String mEndDate;

    protected void initView() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mTimeByMillis);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DATE, -dayOfWeek);
        for (int i = 0; i < MONTH_DAY_COUNT; i++) {
            String key = calendar.get(Calendar.YEAR) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.MONTH) + 1) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.DATE));
            mKeyList.add(key);
            if (i == 0) {
                mStartDate = key;
            } else if (i == MONTH_DAY_COUNT - 1) {
                mEndDate = key;
            }
            CalendarWidgetItemView child = mCalendarView.getChildView(i);
            child.setDate(calendar.getTimeInMillis());

            if (isThisMonth(calendar.getTimeInMillis())) {
                child.setIsThisMonth(true);
            }
            calendar.add(Calendar.DATE, 1);
            child.setPosition(i);
            mCalendarView.addView(child);
        }
    }

    public boolean isThisMonth(long millis) {
        Calendar checkCalendar = Calendar.getInstance();
        Calendar thisCalendar = Calendar.getInstance();
        checkCalendar.setTimeInMillis(millis);
        thisCalendar.setTimeInMillis(this.mTimeByMillis);

        return checkCalendar.get(Calendar.YEAR) == thisCalendar.get(Calendar.YEAR) && checkCalendar.get(Calendar.MONTH) == thisCalendar.get(Calendar.MONTH);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (mAdapter != null && isVisibleToUser) {
            mAdapter.notifyDataSetChanged();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    public void loadData() {
        if (mCalViewPresenter != null) {
            SpentDataTask spentDataTask = new SpentDataTask();
            spentDataTask.execute();

        }

    }


    @Override
    public void onClick(View v) {

    }

    public class SpentDataTask extends AsyncTask<Void, Void, HashMap<String, ArrayList<UserTransactionData>>> {

        @Override
        protected HashMap<String, ArrayList<UserTransactionData>> doInBackground(Void... params) {

            return mCalViewPresenter.spentDataMap(mStartDate, mEndDate, spinnerType, sumType, mSearchStringDataList);
        }

        @Override
        protected void onPostExecute(HashMap<String, ArrayList<UserTransactionData>> longArrayListHashMap) {
            mSpentDataMap = longArrayListHashMap;
            for (int i = 0; i < MONTH_DAY_COUNT; i++) {
                CalendarWidgetItemView child = mCalendarView.getChildView(i);
                if (mSpentDataMap != null && mSpentDataMap.containsKey(mKeyList.get(i))) {
                    child.checkSumType(sumType);
                    child.addText(mSpentDataMap.get(mKeyList.get(i)));
                    child.invalidate();
                }
            }

            if (Constants.lv2_refresh) {
                mCalViewPresenter.refreshView();
                if (mSpentDataMap != null && mSpentDataMap.containsKey(mKey)) {
                    mAdapter.addAll(0, mSpentDataMap.get(mKey));
                }
                Constants.lv2_refresh = false;
            }

        }
    }

}
