package tenqube.com.qlip_android.ui_kj.calendar;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.query.CalendarHelper;

/**
 * Created by tenqube on 2016. 10. 10..
 */

public class CalendarModel {
    private Context mContext;
    private CalendarHelper calendarHelper;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat curYeardate = new SimpleDateFormat("yyyy", Locale.KOREA);
    SimpleDateFormat curMonthdate = new SimpleDateFormat("MM", Locale.KOREA);
    SimpleDateFormat curDayDate = new SimpleDateFormat("dd", Locale.KOREA);

    Calendar mCal = Calendar.getInstance();

    public CalendarModel(Context context) {
        this.mContext = context;
        calendarHelper = new CalendarHelper(context);

    }


    public String initData() {
        String msg;
        if (mCal.get(Calendar.MONTH) < 9) {

            msg = mCal.get(Calendar.YEAR) + "-" + "0" + (mCal.get(Calendar.MONTH) + 1);

        } else {
            msg = mCal.get(Calendar.YEAR) + "-" + (mCal.get(Calendar.MONTH) + 1);

        }
        return msg;
    }

    public ArrayList<String> loadCalendarDataList(int month) {

        month = (month) + Integer.parseInt(curMonthdate.format(now));


        ArrayList<String> dayList = new ArrayList<>();
        mCal.set(Integer.parseInt(curYeardate.format(now)), (month - 1), 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            dayList.add(" ");

        }

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            if (i < 9) {
                dayList.add("0" + (i + 1));
            } else {
                dayList.add("" + (i + 1));
            }
        }


        return dayList;
    }

    public HashMap<String, UserTransactionData> loadMonthUserTransactionData(String date, int dw_type) {

        if (date.length() > 7) {
            date = date.substring(0, 7);
        }
        ArrayList<UserTransactionData> monthUserTransactionDataList;
        monthUserTransactionDataList = calendarHelper.loadMonthUserTransactionDataList(date, dw_type);
        Log.i("array", monthUserTransactionDataList.size() + "");
        HashMap<String, UserTransactionData> monthUserTransactionMap = new HashMap<>();
        String day;
        for (UserTransactionData data : monthUserTransactionDataList) {

            day = data.spentDate;
            monthUserTransactionMap.put(day.substring(0, 10), data);

        }


        return monthUserTransactionMap;
    }
}
