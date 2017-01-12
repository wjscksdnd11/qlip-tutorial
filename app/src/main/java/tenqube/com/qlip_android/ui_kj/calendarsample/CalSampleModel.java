package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.query.CalendarHelper;
import tenqube.com.qlip_android.util.DateUtil;
import tenqube.com.qlip_android.util.DecimalFormatUtil;

/**
 * Created by tenqube on 2016. 10. 17..
 */

public class CalSampleModel {
    private String curDate;
    private Context mContext;
    private String start_date;
    private String end_date;
    private String current_date;

    public CalSampleModel(Context context) {
        this.mContext = context;
        calendarHelper = new CalendarHelper(context);
    }

    private CalendarHelper calendarHelper;
//    long now = System.currentTimeMillis();
//    Date date = new Date(now);
//    SimpleDateFormat parseDate = new SimpleDateFormat("yyyy.MM.dd");
//    SimpleDateFormat curYeardate = new SimpleDateFormat("yyyy", Locale.KOREA);
//    SimpleDateFormat curMonthdate = new SimpleDateFormat("MM", Locale.KOREA);
//    SimpleDateFormat curDayDate = new SimpleDateFormat("dd", Locale.KOREA);
//
//    Calendar mCal = Calendar.getInstance();
//
//
//    //현재 날짜 String 리턴.
//    public String initData() {
//        String msg;
//        if (mCal.get(Calendar.MONTH) < 9) {
//
//            msg = mCal.get(Calendar.YEAR) + "-" + "0" + (mCal.get(Calendar.MONTH) + 1);
//
//        } else {
//            msg = mCal.get(Calendar.YEAR) + "-" + (mCal.get(Calendar.MONTH) + 1);
//
//        }
//        curDate = msg;
//        return msg;
//    }
//
//    private void settingQueryString(int thisyear, int thismonth, int thisdate, int prev_year, int prev_month, int prev_last_date, int lastweek, int dayofweek,String lastdate) {
//
//
//        if (dayofweek > 1) {
//                start_date = prev_year + "-" + prev_month + "-" + (prev_last_date - dayofweek + 2);
//
//        }else {
//                start_date = thisyear + "-" + thismonth + "-" + "1";
//        }
//
//
//            end_date = thisyear+"-"+thismonth+"-"+lastdate;
//
//
//        current_date =  thisyear+"-"+thismonth+"-"+thisdate;
//
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
//        Date date ;
//
//        try {
//            date =  simpleDateFormat.parse(end_date);
//
//            end_date =  simpleDateFormat.format(date);
//            date = simpleDateFormat.parse(start_date);
//            start_date = simpleDateFormat.format(date);
//            date = simpleDateFormat.parse(current_date);
//            current_date = simpleDateFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    private int count_day(String start_date,String end_date){
//
//
//        Date startDate = new Date ( );
//
//        Calendar cal = Calendar.getInstance ( );
//
//        cal.setTime ( startDate );
//        Calendar cal2 = Calendar.getInstance ( );
//
//        cal2.set ( 2000, 3, 12 );
//        int count = 0;
//        while ( !cal2.after ( cal ) ) {
//            count++;
//
//            cal2.add ( Calendar.DATE, 1 );
//        }
//
//        return -1;
//    }
//    private ArrayList<CalendarData> customCalcalendar(String start_date,String this_month){
//        ArrayList<CalendarData> calData = new ArrayList<>();
//        Calendar start_cal = Calendar.getInstance();
//        Calendar prev_cal = Calendar.getInstance();
//
//        String end_date = CalendarUtil.getStartEndDate(Integer.parseInt(start_date),0).get(0);
//        start_cal.set(start_cal.get(Calendar.YEAR),start_cal.get(Calendar.MONTH),Integer.parseInt(start_date));
//        prev_cal.set(start_cal.get(Calendar.YEAR),start_cal.get(Calendar.MONTH),Integer.parseInt(start_date));
//
//
//
//        int dayofweek = start_cal.get(start_cal.DAY_OF_WEEK);
//        for (int i = 1; i < dayofweek; i++) {
//            CalendarData calendarData  = new CalendarData();
//            if (dayofweek > 1) {
//
//                prev_cal.add(Calendar.DATE,-dayofweek);
//                calendarData.setDay((prev_cal.get(Calendar.DATE)+1)+"");
//                calendarData.setDate(prev_cal.get(Calendar.YEAR)+"-"+DecimalFormatUtil.decimalFormat.format(prev_cal.get(Calendar.MONTH))+
//                        "-"+DecimalFormatUtil.decimalFormat.format(prev_cal.get(Calendar.DATE)));
//                calData.add(calendarData);
//            }
//
//        }
//
//
//
//
//
//        return calData;
//    }
//    private ArrayList<CalendarData> calCalaendar(Calendar mCal) {
//        ArrayList<CalendarData> caldata = new ArrayList<>(42);
//
//        int thisyear = mCal.get(mCal.YEAR);
//        int thismonth = mCal.get(mCal.MONTH)+1;
//        Log.i("calendar",thismonth+"");
//        int thisdate = mCal.get(mCal.DATE);
//        int thislastday = mCal.getActualMaximum(mCal.DAY_OF_MONTH);
//        mCal.set(thisyear, thismonth - 1, 1); //현재 달의 첫째일
//        int dayofweek = mCal.get(mCal.DAY_OF_WEEK);
//        int lastday = mCal.getActualMaximum(mCal.DAY_OF_MONTH);
//        mCal.set(thisyear, thismonth - 1, lastday);//현재 달의 마지막 일
//        int lastweek = mCal.get(mCal.DAY_OF_WEEK);
//        mCal.set(thisyear, thismonth - 2, 1);//이전달
//        int prev_last_date = mCal.getActualMaximum(mCal.DAY_OF_MONTH);
//        int prev_year = mCal.get(mCal.YEAR);
//        int prev_month = mCal.get(mCal.MONTH) + 1;
//        int calspace = thislastday + dayofweek - 1 + (7 - lastweek);
//
//
//        for (int i = 1; i < dayofweek; i++) {
//            CalendarData calendarData  = new CalendarData();
//            if (dayofweek > 1) {
//                calendarData.setDay((prev_last_date - dayofweek + 1 + i )+ "");
//                calendarData.setDate(prev_year+"-"+prev_month+"-"+calendarData.getDay());
//                caldata.add(calendarData);
//            }
//
//        }
//        for (int i = 1; i <= thislastday; i++) {
//            CalendarData calendarData  = new CalendarData();
//            calendarData.setDay(i+"");
//            calendarData.setDate(thisyear+"-"+thismonth+"-"+calendarData.getDay());
//            caldata.add(calendarData);
//        }
//        int maxcal = 42-caldata.size();
//        for (int i = 1; i <= maxcal; i++) {
//            CalendarData calendarData  = new CalendarData();
//            calendarData.setDay(i+"");
//            calendarData.setDate(thisyear+"-"+(thismonth+1)+"-"+calendarData.getDay());
//            caldata.add(calendarData);
//
//        }
//
//        //쿼리를 만들어주는 메소드
//        settingQueryString(thisyear, thismonth, thisdate, prev_year, prev_month, prev_last_date, lastweek, dayofweek,caldata.get(caldata.size()-1).getDay());
//
//        return caldata;
//    }
//
//
//    //캘린더 날짜 데이터
//    public ArrayList<CalendarData> loadCalendarDataList(String date, int spinner_flag) {
//
//        //현재 날짤르 세팅해서 calCalendar에 넣으면 화면에 달력을 계산해서 list로 리턴한다. 데이터셋보다 load캘린더가 먼저 불려야 쿼리를 할 수있다
//
//
//
//        Calendar calendar_gre = Calendar.getInstance();
//
//        calendar_gre.set(Integer.parseInt(date.substring(0, 4)), (Integer.parseInt(date.substring(5, 7)) - 1), 1);
//        Log.i("monthtest",calendar_gre.get(calendar_gre.DATE)+"");
//        ArrayList<CalendarData>calendarDatas = calCalaendar(calendar_gre);// 이걸해줘야 캐싱이된다 start,end
//
//        return calendarHelper.loadMonthUserCalendarDataList(start_date,end_date,calendarDatas,spinner_flag);
//    }
//
//
//
//    // 탭 영역
//    public ArrayList<String> loadTabTitle() {
//        ArrayList<String> tabtitles = new ArrayList<>();
//        Calendar cal = new GregorianCalendar(Locale.KOREA);
//        Calendar curCal = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
//
//        String firstdate = calendarHelper.loadTabTitles();
//        String curdate = curCal.get(Calendar.YEAR) + "." + (curCal.get(Calendar.MONTH) + 1);
//
//        if (firstdate.length() < 7) {
//            firstdate = firstdate.substring(0, 4) + ".0" + firstdate.substring(5, 6);
//        }
//
//
//        int firstyear = Integer.parseInt(firstdate.substring(0, 4));
//        int firstmonth = Integer.parseInt(firstdate.substring(5, 7));
//        int curyear = Integer.parseInt(curdate.substring(0, 4));
//        int curmonth = Integer.parseInt(curdate.substring(5, 7));
//        int temp = (curyear - firstyear) * 12 + (curmonth - firstmonth);
//        cal.set(firstyear, firstmonth - 1, 1);//gregory는 0base이기 때문에 -1 을 해줘야 한다
//
//        if (temp == 0) {
//            tabtitles.add(curdate);
//            return tabtitles;
//        } else {
//            for (int i = 0; i <= temp; i++) {//add를 마지막에 해주기때문에 마지막 한번 더 돌아야 현재 시간까지 포함을 한다.
//
//                String tempdate = simpleDateFormat.format(cal.getTime());
//                tabtitles.add(tempdate);
//                cal.add(Calendar.MONTH, 1);
//
//            }
//
//        }
//
//        return tabtitles;
//    }

    //캘린더 날짜 데이터 맵
//    public HashMap<String,UserTransactionData> loadCalendarDataMap(String date, String end_date, int spinner_flag) {
//
//
//
//        return calendarHelper.loadMonthUserCalendarDataMap(date,end_date,spinner_flag);
//
//    }
    public ArrayList<UserTransactionData> loadCalendarDataList(String date,String end_date,int spinner_flag, int sumType){
        return calendarHelper.loadMonthUserCalendarDataList(date,end_date,spinner_flag,sumType);
    }

    protected ArrayList<CalendarData> getCalendarList(){

        ArrayList<CalendarData> calendarList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -5); //120 개월
        calendar.set(Calendar.DATE, 1);

        for (int i=0; i<120; i++){
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            CalendarData calendarData = new CalendarData();
            calendarData.yearMonth = DateUtil.getStringDateAsYYYYMM(calendar);
            calendarData.calendar =calendar;

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



            ArrayList<CalendarSpentData> calendarDayList = new ArrayList<>();
            for(int j=0; j<=41;j++){
                int day = dayCal.get(Calendar.DATE);
                month = dayCal.get(Calendar.MONTH)+1;
                year = dayCal.get(Calendar.YEAR);
                CalendarSpentData calendarSpentData = new CalendarSpentData();
                calendarSpentData.day  = day;
                calendarSpentData.date = year+"-"+DecimalFormatUtil.decimalFormat.format(month)+"-"+DecimalFormatUtil.decimalFormat.format(day);

                calendarDayList.add(calendarSpentData);

                dayCal.add(Calendar.DATE,1);


            }
            Log.i("caldate","startdate :"+ calendarDayList.get(0).date+" , enddate : "+calendarDayList.get(41).date);
            calendarData.dayDataList= calendarDayList;
            calendarList.add(calendarData);
            calendar.add(Calendar.MONTH, 1);
        }

        return  calendarList;

    }
}
