package tenqube.com.qlip_android.ui_kj.calendar_kj;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;

public class CalendarPresenterImpl implements  CalendarPresenter {
    private CalendarActivity mActivity;
    private CalendarAdapter calendarAdapter;
    private CalendarModel calendarModel;
    private ArrayList<CalendarData> calendarList = new ArrayList();
    public int mParentPos = -1;
    public int mChildPos = -1;


    public CalendarPresenterImpl(CalendarActivity mActivity) {
        this.mActivity = mActivity;
        calendarModel = new CalendarModel(mActivity);
    }



    @Override
    public void settingToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.qlip_main_black_lv0));
    }




    @Override
    public void initAdapter(ViewPager viewpager) {
        calendarAdapter = new CalendarAdapter(mActivity.getSupportFragmentManager());
        viewpager.setAdapter(calendarAdapter);
    }


    @Override
    public void loadData() {
        new LoadCalendarTask().execute();
    }

    @Override
    public void onRefresh(final int parentPos, int childPos) {

        if(mChildPos!=-1){
            calendarList.get(mParentPos).calendarSubList.get(mChildPos).isChecked=false;
        }

        mParentPos = parentPos;
        mChildPos = childPos;

        calendarList.get(parentPos).calendarSubList.get(childPos).isChecked=true;
        calendarAdapter.notifyDataSetChanged();
    }

    @Override
    public ArrayList<CalendarData> getList() {
        return null;
    }


    public class CalendarAdapter extends FragmentStatePagerAdapter {

        public CalendarAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CalendarFragment.newInstance(calendarList.get(position), position);
        }

        @Override
        public int getCount() {
            return calendarList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return calendarList.get(position).titleDate;
        }

        @Override
        public int getItemPosition(Object object) {
            CalendarFragment frag = (CalendarFragment) object;
            if (frag != null) {
                frag.updateFragment();
            }
            return super.getItemPosition(object);
        }
    }



    public class LoadCalendarTask extends AsyncTask<Void, Void, ArrayList<CalendarData>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CalendarData> doInBackground(Void... voids) {
            //두달전 ~ 두달 후 5개월 데이터 로드
            return calendarModel.getCalendarList();
        }

        @Override
        protected void onPostExecute(ArrayList<CalendarData> calList) {
            super.onPostExecute(calList);
            calendarList = calList;
            calendarAdapter.notifyDataSetChanged();

        }

    }

}