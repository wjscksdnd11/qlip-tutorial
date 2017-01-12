package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tenqubeon 2016. 10. 17..
 */

public class SectionPagerAdapter extends FragmentStatePagerAdapter implements CalSampleActivity.SpinnerChangeListener {

    private CalSamplePresenter calSamplePresenter;
    private Context mContext;
    private ArrayList<CalendarData> titles ;
    private int spinner_position;
    private int sumType;

    public SectionPagerAdapter(FragmentManager fm ,CalSampleActivity activity, int dw_type, int sumType) {
        super(fm);

        mContext = activity;
        spinner_position = dw_type;
        titles = new ArrayList<>();
        this.sumType = sumType;

    }
    public int getTodayPosition(){
       String date =  Calendar.getInstance().get(Calendar.YEAR)+"."+Calendar.getInstance().get(Calendar.MONTH);
        for (int i =0;i<titles.size();i++)
        {
            if(titles.get(i).yearMonth.equals(date)){
                int this_month  = i;

                return this_month;
            }
        }
        return titles.size()/2;

    }
CalendarData tempSpentData;
    public void setTitles(ArrayList<CalendarData> calendarDataArrayList){
        this.titles = calendarDataArrayList;


    }
    int parentpos;
    @Override
    public Fragment getItem(int position) {
        parentpos = position;
        this.tempSpentData = titles.get(position);



        return CalendarFragment.newInstance(tempSpentData,spinner_position,position,sumType);
}


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).yearMonth;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public void onReceivedSpinnerPosition(int position, int sumType) {
        spinner_position = position;
        this.sumType = sumType;



        notifyDataSetChanged();

//        Log.i("position","spinner_position : "+spinner_position);
//        notifyDataSetChanged();
    }
}