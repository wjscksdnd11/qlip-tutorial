package tenqube.com.qlip_android.ui_kj.calendar_kj;

import android.content.Context;


import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.util.DateUtil;


public class CalendarModel {

    private Context mContext;
    public CalendarModel(CalendarActivity mActivity) {
        mContext = mActivity;
    }


    protected ArrayList<CalendarData> getCalendarList(){

        ArrayList<CalendarData> calendarList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -5); //120 개월
        calendar.set(Calendar.DATE, 1);

        for (int i=0; i<120; i++){
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            CalendarData calendarData = new CalendarData();
            calendarData.titleDate = DateUtil.getStringDateAsYYYYMM(calendar);
            calendarData.calendar =calendar;
            if(month == 0){
                year -=1;
                month = 11;
            }else{
                month -=1;
            }
            Calendar tempCal = Calendar.getInstance();
            tempCal.set(year, month, 1);
            int beforeStartDay;
            if(dayOfWeek == Calendar.SUNDAY){
                beforeStartDay = 7;
            }else{
                beforeStartDay = dayOfWeek-1;
            }

            Calendar dayCal = Calendar.getInstance();
            dayCal.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
            dayCal.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
            dayCal.set(Calendar.DATE,calendar.get(Calendar.DATE));
            dayCal.add(Calendar.DATE, -beforeStartDay);

            ArrayList<CalendarSubData> calendarSubList = new ArrayList<>();
            for(int j=0; j<42;j++){

                CalendarSubData calendarSubData = new CalendarSubData();
                calendarSubData.day = dayCal.get(Calendar.DATE);
                calendarSubList.add(calendarSubData);
                dayCal.add(Calendar.DATE,1);

            }

            calendarData.calendarSubList= calendarSubList;
            calendarList.add(calendarData);
            calendar.add(Calendar.MONTH, 1);
        }

        return  calendarList;

    }


}
