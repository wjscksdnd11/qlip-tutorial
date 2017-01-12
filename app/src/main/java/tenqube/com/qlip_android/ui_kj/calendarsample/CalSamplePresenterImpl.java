package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 10. 17..
 */

public class CalSamplePresenterImpl implements CalSamplePresenter {
    private CalSampleActivity mActivity;
    private CalSampleModel mModel;
    private ArrayList<CalendarData> calendarList;
    private SectionPagerAdapter mSectionPageAdapter;
    public int mParentPos = -1;
    public int mChildPos = -1;
    private String start_date = "";
    private String end_date = "";
    private int dw_type;
    private int sumType;
    public CalSamplePresenterImpl(CalSampleActivity activity) {


        this.mActivity = activity;
        this.mModel = new CalSampleModel(activity);

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
    public void onRefresh(final int parentPos, int childPos) {
//        Log.i("OnclickListener", "OnClick" + parentPos);
//        if(mChildPos!=-1){
//            calendarList.get(mParentPos).dayDataList.get(mChildPos).isChecked=false;
//        }
//
//        mParentPos = parentPos;
//        mChildPos = childPos;
//
//        calendarList.get(parentPos).dayDataList.get(childPos).isChecked=true;
//        mSectionPageAdapter.notifyDataSetChanged();
    }


    @Override
    public void changeSpinner(int spinner_type, int sumType) {
        mSectionPageAdapter.onReceivedSpinnerPosition(spinner_type, sumType);




    }

//    @Override
//    public void setData(int position, int dwtype, int sumType) {
//        mParentPos = position;
//        this.dw_type = dwtype;
//        this.sumType = sumType;
////
////        SpentDataSetTask spentDataSetTask = new SpentDataSetTask();
////        spentDataSetTask.execute();
//    }


    @Override
    public void initAdapter(ViewPager viewpager, int spinner_type, int sumType) {
        mSectionPageAdapter = new SectionPagerAdapter(mActivity.getSupportFragmentManager(), mActivity, spinner_type, sumType);

        viewpager.setAdapter(mSectionPageAdapter);

    }


    @Override
    public void loadData() {
        new LoadCalendarTask().execute();
    }


    @Override
    public ArrayList<CalendarData> getCalendarList() {

        return calendarList;
    }

//    @Override
//    public HashMap<String, UserTransactionData> spentHashMap(String start_date, String end_date, int dw_type) {
//        this.start_date = start_date;
//        this.end_date = end_date;
//        return mModel.loadCalendarDataMap(start_date, end_date, dw_type);
//    }

    @Override
    public ArrayList<UserTransactionData> spentArrayList(String start_date, String end_date, int spinner_type, int sumType) {

        return mModel.loadCalendarDataList(start_date, end_date, spinner_type, sumType);
    }

    public class LoadCalendarTask extends AsyncTask<Void, Void, ArrayList<CalendarData>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CalendarData> doInBackground(Void... voids) {
            //두달전 ~ 두달 후 5개월 데이터 로드


            return mModel.getCalendarList();
        }

        @Override
        protected void onPostExecute(ArrayList<CalendarData> calList) {
            super.onPostExecute(calList);
            calendarList = calList;

            mSectionPageAdapter.setTitles(calList);

            mSectionPageAdapter.notifyDataSetChanged();

        }



    }
//    public class SpentDataSetTask extends AsyncTask<Void, Void, ArrayList<CalendarSpentData>> {
//
//        @Override
//        protected ArrayList<CalendarSpentData> doInBackground(Void... params) {
//            ArrayList<UserTransactionData> spentDataArrayList;
//            ArrayList<CalendarData> tempList;
//            tempList = calendarList;
//            if (calendarList == null ||calendarList.isEmpty()||calendarList.get(mParentPos).dayDataList.size()==0){return null;}
//            spentDataArrayList = spentArrayList(calendarList.get(mParentPos).dayDataList.get(0).date, calendarList.get(mParentPos).dayDataList.get(41).date, dw_type, sumType);
//            if (spentDataArrayList.size() > 0) {
//
//
////            calendarFragment = (CalendarFragment) mSectionPageAdapter.getCurrentFragment(mParentPos);
//            for (int i = 0; i < 42; i++) {
//                tempList.get(mParentPos).dayDataList.get(i).spentdata = null;
//                for (int j = 0; j < spentDataArrayList.size(); j++) {
//                    Log.i("spentarr",spentDataArrayList.size()+"");
//                    if (tempList.get(mParentPos).dayDataList.get(i).date.equals(spentDataArrayList.get(j).spentDate.substring(0, 10))) {
//                        tempList.get(mParentPos).dayDataList.get(i).spentdata = new ArrayList<>();
//                        tempList.get(mParentPos).dayDataList.get(i).spentdata.add(spentDataArrayList.get(j));
//                   Log.i("spenttype","Impl 데이터 바인딩 하는부분 개수"+ tempList.get(mParentPos).dayDataList.get(i).spentdata.get(0).spentMoney);
//
//                    }
//                }
//
//
//                }
//            }
//            return tempList.get(mParentPos).dayDataList;
//
//        }
//
//
//        @Override
//        protected void onPostExecute(ArrayList<CalendarSpentData> userTransactionDatas) {
//            super.onPostExecute(userTransactionDatas);
//
//            if (userTransactionDatas != null) {
////                calendarList.get(mParentPos).dayDataList = userTransactionDatas;
////                calendarFragment = (CalendarFragment) mSectionPageAdapter.getItem(mParentPos);// 이부분은 현재 프래그먼트 가져오는것이 해결되면 고치는 걸로 ..
//
//
//            }
//        }
//    }

}
