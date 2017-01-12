package tenqube.com.qlip_android.util;

import android.content.Context;
import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;

import static android.provider.CalendarContract.Instances.START_DAY;
import static tenqube.com.qlip_android.constans.Constants.Repeat.REPEAT_EVERY_MONTH;
import static tenqube.com.qlip_android.constans.Constants.Repeat.REPEAT_EVERY_WEEK;
import static tenqube.com.qlip_android.util.LogUtil.LOGI;


public class DateUtil {


    public static final SimpleDateFormat fullDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    public static final SimpleDateFormat ymdDF = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    public static final SimpleDateFormat hhmmDF = new SimpleDateFormat("HH:mm", Locale.KOREA);

    public static final String[] FULL_MOON_LIST={
            "2016-05-22 06:16:00",
            "2016-06-20 20:04:00",
            "2016-07-20 07:59:00",
            "2016-08-18 18:29:00",
            "2016-09-17 04:07:00",
            "2016-10-16 13:25:00",
            "2016-11-14 22:53:00",
            "2016-12-14 09:06:00",
            "2017-01-12 20:35:00",
            "2017-02-23 09:33:00",
            "2017-03-23 23:54:00",
            "2017-04-22 15:09:00",
            "2017-05-22 06:43:00",
            "2017-06-20 22:11:00",
            "2017-07-20 13:08:00",
            "2017-08-18 03:12:00",
            "2017-09-17 16:04:00",
            "2017-10-16 03:41:00",
            "2017-11-14 14:24:00",
            "2017-12-14 12:48:00"
    };
    private DateUtil(){

    }

    public static String getStringDateAsHHmmss(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR_OF_DAY))+":"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE))+":"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.SECOND));

    }
    public static String getStringDateAsHHmm(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR_OF_DAY))+":"+
                        DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE));

    }

    public static String getStringDetailDate(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"년 "+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH) + 1))+"월 "+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE))+"일 "+
                "("+ConvertStringUtil.getDateDay(cal.get(Calendar.DAY_OF_WEEK))+")";


    }
    public static String getStringDateAsYYYYMMddAsDot(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"."+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"."+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE));


    }

    public static String getStringDateAsYYYYMMddAsDot(int year , int month, int date){

        return  year+"."+
                DecimalFormatUtil.decimalFormat.format(month)+"."+
                DecimalFormatUtil.decimalFormat.format(date);


    }
    public static String getStringDetailTime(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();

        if(cal.get(Calendar.AM_PM)==0){
            return  "오전"+" "+
                    DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR))+":"+
                    DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE));


        }else{
            return  "오후"+" "+
                    DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR)==0?12:cal.get(Calendar.HOUR))+":"+
                    DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE));


        }

    }

    public static String getStringDateAsYYYYMMddHHmmss(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"-"+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"-"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE))+" "+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR_OF_DAY))+":"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE))+":"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.SECOND));

    }
    public static String getStringDateAsYYYYMMdd(Calendar cal){

        if(cal == null)cal = Calendar.getInstance();

        return  cal.get(Calendar.YEAR)+"-"+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"-"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE));


    }

    public static String getStringDateAsYYYYMMddHHmm(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"-"+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"-"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE))+" "+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.HOUR_OF_DAY))+":"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.MINUTE));
    }
    public static String getStringDateAsYYYYMMdd2(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"년 "+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH) + 1))+"월 "+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE))+"일";
    }



    public static String getStringDateAsYYYYMM(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  cal.get(Calendar.YEAR)+"."+
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"";



    }

    public static String getStringDateAsMM(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return  DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"월";
    }
    public static String mmDD(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();

        return
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"-"+
                DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE));

    }

    public static String mmDDSlash(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();

        return
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"/"+
                        DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE));

    }
    public static String mdDF2(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"."+
                        DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE));


    }
    public static String mdDF(Calendar cal){
        if(cal == null)cal = Calendar.getInstance();
        return
                DecimalFormatUtil.decimalFormat.format((cal.get(Calendar.MONTH)+1))+"월 "+
                        DecimalFormatUtil.decimalFormat.format(cal.get(Calendar.DATE))+"일";


    }

    public static Calendar convertStringToCalendarFULL(String date){
        Calendar resultCal= Calendar.getInstance();
        Date d;
        if(date==null|| TextUtils.isEmpty(date)){
            return  resultCal;
        }

        try {

            d=fullDF.parse(date);
            resultCal.setTime(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  resultCal;
    }

    public static Calendar convertStringToCalendarYMD(String date){
        Calendar resultCal= Calendar.getInstance();
        Date d;
        if(date==null){
            return  resultCal;
        }
        try {
            d=ymdDF.parse(date);

            resultCal.setTime(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  resultCal;
    }

    public static boolean compareDate(Calendar beforeDate, Calendar afterDate){

            return  beforeDate.before(afterDate)||beforeDate.equals(afterDate);

    }
    public static boolean compareDateBefore(Calendar beforeDate, Calendar afterDate){

        return  beforeDate.before(afterDate);

    }
    public static  void delayTime(int delayValue){

        try {
            Thread.sleep(delayValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static  void delayTimeSybc(int delayValue){

        for (int i = 0 ;i <delayValue ;i ++){

        }

    }
    public static ArrayList<String> distinctDate(ArrayList<String> dateList){
        ArrayList<String> resultList; //결과를 담을 어레이리스트
        resultList = new ArrayList<>(new HashSet<>(dateList));
        return  resultList;

    }
    public static ArrayList<Long> distinctIdentifier(ArrayList<Long> dateList){
        ArrayList<Long> resultList; //결과를 담을 어레이리스트
        resultList = new ArrayList<>(new HashSet<>(dateList));
        return  resultList;

    }

    public static int getDiffDateAsCalendar(Calendar before, Calendar today) {
        long start, end;
        start = before.getTimeInMillis();
        end = today.getTimeInMillis();
        Long diff = (end - start) / (1000 * 60 * 60 * 24);
        return diff.intValue()+1;


    }


    public static ArrayList<String> requestDateBetweenStartAndEndAsExpected(int starDay , int position) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        ArrayList<String> resultDateItems = new ArrayList<>();
        String startDateStr="";
        String resultEndDate;
        String resultEndDatePlusOne;
        Calendar tempCal = Calendar.getInstance(); //2015 6 4
        Calendar tempCal2 = Calendar.getInstance(); //2015 6 4
        Calendar todayCal = Calendar.getInstance();

        int posTemp = -1 * position;

        if(todayCal.get(Calendar.DATE)<starDay){
            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
        }


        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30


        if(actualDay>starDay){ //30> 1
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+DecimalFormatUtil.decimalFormat.format(starDay);
        }else{//30,31
            startDateStr=currentYear+"-"+DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+DecimalFormatUtil.decimalFormat.format(actualDay);
        }

        tempCal=DateUtil.convertStringToCalendarYMD(startDateStr);
        if(!compareDate(todayCal,tempCal)){
            startDateStr=currentYear+"-"+DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+DecimalFormatUtil.decimalFormat.format(todayCal.get(Calendar.DATE)+1);
        }
        resultDateItems.add(startDateStr);


        if(todayCal.get(Calendar.DATE)<starDay){
            tempCal2.add(Calendar.MONTH,posTemp); //2015 7

        }else{
            tempCal2.add(Calendar.MONTH,posTemp+1); //2015 7

        }
        currentYear = tempCal2.get(Calendar.YEAR);//2015
        currentMonth = tempCal2.get(Calendar.MONTH);

        actualDay=tempCal2.getActualMaximum(Calendar.DATE);

        if(actualDay>starDay){
            tempCal2.set(currentYear, currentMonth, starDay);
        }else{
            tempCal2.set(currentYear, currentMonth, actualDay);

        }


        tempCal2.add(Calendar.DATE,-1);

        resultEndDate=tempCal2.get(Calendar.YEAR)+"-"+DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));


        resultDateItems.add(resultEndDate);


        tempCal2.add(Calendar.DATE,1);

        resultEndDatePlusOne=tempCal2.get(Calendar.YEAR)+"-"+DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));


        resultDateItems.add(resultEndDatePlusOne);


        return resultDateItems;


    }



    /**
     *
     * @param starDay 시작일
     * @param position 이번달 0 지난달 1 지지난달 2 ...
     * @return resultDateItems ['2016-10-01','2016-10-31','2016-10-20']
     * resultDateItems[0] = startDate ,
     * resultDateItems[1] = realEndDate, (예산 계산할때 필요해서 미리 뽑아놓음)
     * resultDateItems[2] = endDate (실제 쿼리 사용),(오늘날짜면 오늘날짜 + 1 , 과거면 마자막일 + 1
     */

    public static ArrayList<String> requestDateBetweenStartAndEnd(int starDay, int position) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        ArrayList<String> resultDateItems = new ArrayList<>();
        String startDateStr;
        String resultEndDate;
        String resultEndDatePlusOne;
        Calendar startCal = Calendar.getInstance(); //2015 6 4
        Calendar endCal = Calendar.getInstance(); //2015 6 4
        Calendar todayCal =Calendar.getInstance();


        /**
         * 시작일 구하기
         */
        int posTemp = -1 * position;

        if(todayCal.get(Calendar.DATE)<starDay){
            startCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
        }else{
            startCal.add(Calendar.MONTH, posTemp); //2015 6 4
        }

        int currentYear = startCal.get(Calendar.YEAR); //2015
        int currentMonth = startCal.get(Calendar.MONTH);//2015
        int actualDay = startCal.getActualMaximum(Calendar.DATE);//30


        if(actualDay>starDay){ //30> 1
            startDateStr=
                    currentYear+"-"+
                            DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+
                            DecimalFormatUtil.decimalFormat.format(starDay);
        }else{//30,31
            startDateStr=
                    currentYear+"-"+
                            DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+
                            DecimalFormatUtil.decimalFormat.format(actualDay);
        }
        resultDateItems.add(startDateStr);

        /**
         * 마지막일 구하기
         */

        if(todayCal.get(Calendar.DATE)<starDay){
            endCal.add(Calendar.MONTH,posTemp); //2015 7
        }else{
            endCal.add(Calendar.MONTH,posTemp+1); //2015 7
        }

        currentYear = endCal.get(Calendar.YEAR);//2015
        currentMonth = endCal.get(Calendar.MONTH);
        actualDay = endCal.getActualMaximum(Calendar.DATE);

        if(actualDay>starDay){
            endCal.set(currentYear, currentMonth, starDay);
        }else{
            endCal.set(currentYear, currentMonth, actualDay);
        }

        endCal.add(Calendar.DATE,-1);

        resultEndDate =
                endCal.get(Calendar.YEAR)+"-"+
                        DecimalFormatUtil.decimalFormat.format((endCal.get(Calendar.MONTH)+1))+"-"+
                        DecimalFormatUtil.decimalFormat.format(endCal.get(Calendar.DATE));
        resultDateItems.add(resultEndDate);

        /**
         * 마지막일 구하기 +1 오늘 날짜인경우
         */

        if(compareDate(todayCal,endCal)){

            todayCal.add(Calendar.DATE,1);
            resultEndDatePlusOne =
                    todayCal.get(Calendar.YEAR)+"-"+
                            DecimalFormatUtil.decimalFormat.format((todayCal.get(Calendar.MONTH)+1))+"-"+
                            DecimalFormatUtil.decimalFormat.format(todayCal.get(Calendar.DATE));

        }else{
            endCal.add(Calendar.DATE,1);
            resultEndDatePlusOne =
                    endCal.get(Calendar.YEAR)+"-"+
                            DecimalFormatUtil.decimalFormat.format((endCal.get(Calendar.MONTH)+1))+"-"+
                            DecimalFormatUtil.decimalFormat.format(endCal.get(Calendar.DATE));
        }
        resultDateItems.add(resultEndDatePlusOne);
        return resultDateItems;
    }

    public static Calendar getMaxCalendar(int startDay, int maxPosition){
        String startDateStr;
        Calendar tempCal = Calendar.getInstance(); //2015 6 4
        tempCal.add(Calendar.MONTH, maxPosition); //2015 6 4

        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30


        if(actualDay>startDay){ //30> 1
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(startDay);
        }else{//30,31
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
        }
        return  DateUtil.convertStringToCalendarYMD(startDateStr);
    }

    public static ArrayList<String> requestDateBetweenStartAndEndAsExcept(int starDay, int position, int maxPosition) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        ArrayList<String> resultDateItems = new ArrayList<>();
        String startDateStr;
        String resultEndDate;
        String resultEndDatePlusOne;
        Calendar tempCal = getMaxCalendar(starDay,maxPosition); //2015 6 4
        Calendar tempCal2 =getMaxCalendar(starDay,maxPosition); //2015 6 4; //2015 6 4

        int posTemp = -1 * position;
        if(tempCal.get(Calendar.DATE)<starDay){
            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
        }

        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30


        if(actualDay>starDay){ //30> 1
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);
        }else{//30,31
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
        }

        if(tempCal.get(Calendar.DATE)<starDay){
            tempCal2.add(Calendar.MONTH,posTemp); //2015 7
        }else{
            tempCal2.add(Calendar.MONTH,posTemp+1); //2015 7
        }

        currentYear = tempCal2.get(Calendar.YEAR);//2015
        currentMonth = tempCal2.get(Calendar.MONTH);

        actualDay=tempCal2.getActualMaximum(Calendar.DATE);

        if(actualDay>starDay){
            tempCal2.set(currentYear, currentMonth, starDay);
        }else{
            tempCal2.set(currentYear, currentMonth, actualDay);
        }


        resultDateItems.add(startDateStr);

        tempCal2.add(Calendar.DATE,-1);

        resultEndDate=tempCal2.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));


        resultDateItems.add(resultEndDate);


        tempCal2.add(Calendar.DATE,1);
        resultEndDatePlusOne=tempCal2.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));



        resultDateItems.add(resultEndDatePlusOne);


        return resultDateItems;


    }
    public static ArrayList<String> requestDateBetweenStartAndEndAsStartDay(int starDay) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        ArrayList<String> resultDateItems = new ArrayList<>();
        String startDateStr;
        String resultEndDate;
        String resultEndDatePlusOne;
        Calendar tempCal = Calendar.getInstance(); //2015 6 4
        Calendar tempCal2 = Calendar.getInstance(); //2015 6 4
        Calendar todayCal = Calendar.getInstance();

        int posTemp = 0;

        if(todayCal.get(Calendar.DATE)<starDay){
            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4

        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
        }


        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30


        if(actualDay>starDay){ //30> 1

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);


        }else{//30,31

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);

        }


        if(todayCal.get(Calendar.DATE)<starDay){
            tempCal2.add(Calendar.MONTH,posTemp); //2015 7

        }else{
            tempCal2.add(Calendar.MONTH,posTemp+1); //2015 7

        }

        currentYear = tempCal2.get(Calendar.YEAR);//2015
        currentMonth = tempCal2.get(Calendar.MONTH);

        actualDay=tempCal2.getActualMaximum(Calendar.DATE);

        if(actualDay>starDay){
            tempCal2.set(currentYear, currentMonth, starDay);
        }else{
            tempCal2.set(currentYear, currentMonth, actualDay);

        }


        resultDateItems.add(startDateStr);


        tempCal2.add(Calendar.DATE,-1);

        resultEndDate=tempCal2.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));


        resultDateItems.add(resultEndDate);





        if(compareDate(todayCal,tempCal2)){


            todayCal.add(Calendar.DATE,1);
            resultEndDatePlusOne=todayCal.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((todayCal.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(todayCal.get(Calendar.DATE));

        }else{
            tempCal2.add(Calendar.DATE,1);

            resultEndDatePlusOne=tempCal2.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((tempCal2.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(tempCal2.get(Calendar.DATE));

        }


        resultDateItems.add(resultEndDatePlusOne);


        return resultDateItems;


    }
    public static Calendar requestStartDate(int position, Calendar tempCal) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        String startDateStr;

        int starDay=tempCal.get(Calendar.DATE);
        int posTemp = -1 * position;

//        Calendar todayCal=Calendar.getInstance();
//        if(todayCal.get(Calendar.DATE)<starDay){
//            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
//        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
//        }




        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30

        if(actualDay>starDay){ //30> 1

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);


        }else{//30,31
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);

        }


        return tempCal;



    }

    public static Calendar requestStartDate(int position, String dateStr) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        String startDateStr;
        Calendar tempCal = DateUtil.convertStringToCalendarYMD(dateStr);
        int starDay=tempCal.get(Calendar.DATE);
        int posTemp = -1 * position;

//        Calendar todayCal=Calendar.getInstance();

//        if(todayCal.get(Calendar.DATE)<starDay){
//            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
//        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
//        }


        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30

        if(actualDay>starDay){ //30> 1

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);


        }else{//30,31
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);

        }

        return tempCal;

    }


    public static Calendar requestStartDateAsInstallment(int position, String dateStr) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        String startDateStr;
        Calendar tempCal = DateUtil.convertStringToCalendarYMD(dateStr);
        int starDay=tempCal.get(Calendar.DATE);
        int posTemp = -1 * position;

        tempCal.add(Calendar.MONTH, posTemp); //2015 6 4


        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30

        if(actualDay>starDay){ //30> 1

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);

        }else{//30,31
            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);

        }


        return tempCal;



    }
    private static Calendar requestWeeklyEndCalendar(int pos) {
        int  endDateNum;

        Calendar endCal = Calendar.getInstance();
        Calendar todayCal = Calendar.getInstance();


        int dayOfWeek = todayCal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            endDateNum = -7 * pos ;

        } else {
            endDateNum = -7 * pos + 8 - dayOfWeek;
        }



        endCal.add(Calendar.DATE, endDateNum);//주 마지막


        return endCal;


    }
    public static Calendar requestStartDate(Context mContext, int position) {//시작일 구하기0
        //2015 -5 5 6 4   오늘은 6 4
        mContext = mContext.getApplicationContext();
        String startDateStr;
        Calendar tempCal = Calendar.getInstance(); //2015 6 4

        int starDay=PrefUtils.getInstance(mContext).loadIntValue(START_DAY, 1);
        int posTemp = -1 * position;


        Calendar todayCal= Calendar.getInstance();
        if(todayCal.get(Calendar.DATE)<starDay){
            tempCal.add(Calendar.MONTH, posTemp-1); //2015 6 4
        }else{
            tempCal.add(Calendar.MONTH, posTemp); //2015 6 4
        }



        int currentYear = tempCal.get(Calendar.YEAR); //2015
        int currentMonth = tempCal.get(Calendar.MONTH);//2015
        int actualDay=tempCal.getActualMaximum(Calendar.DATE);//30

        if(actualDay>starDay){ //30> 1

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(starDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);


        }else{//30,31

            startDateStr=currentYear+"-"+ DecimalFormatUtil.decimalFormat.format((currentMonth+1))+"-"+ DecimalFormatUtil.decimalFormat.format(actualDay);
            tempCal= DateUtil.convertStringToCalendarYMD(startDateStr);

        }


        return tempCal;



    }


    private static ArrayList<String> yearMonthDayStartWithCurrentMonth(int startDay, int pos){

        ArrayList<String> lastDate=requestDateBetweenStartAndEnd(startDay,pos+1);
        ArrayList<String> startDate=requestDateBetweenStartAndEnd(startDay,pos+12);


        ArrayList<String> date = new ArrayList<>();
        date.add(startDate.get(0));
        date.add(lastDate.get(1));
        date.add(lastDate.get(2));

        return  date;
    }
    private static ArrayList<String> threeMonthDayStartWithCurrentMonth(int startDay, int pos){

        ArrayList<String> lastDate=requestDateBetweenStartAndEnd(startDay,pos+1);
        ArrayList<String> startDate=requestDateBetweenStartAndEnd(startDay,pos+3);


        ArrayList<String> date = new ArrayList<>();
        date.add(startDate.get(0));
        date.add(lastDate.get(1));
        date.add(lastDate.get(2));

        return  date;
    }

    private static ArrayList<String> sixMonthDayStartWithCurrentMonth(int startDay, int pos){

        ArrayList<String> lastDate=requestDateBetweenStartAndEnd(startDay,pos+1);
        ArrayList<String> startDate=requestDateBetweenStartAndEnd(startDay,pos+6);


        ArrayList<String> date = new ArrayList<>();
        date.add(startDate.get(0));
        date.add(lastDate.get(1));
        date.add(lastDate.get(2));

        return  date;
    }


    public static ArrayList<String> getSamePeriodStartAndLastDate(int startDay, int pos, int pastDate){

        ArrayList<String> startDate=requestDateBetweenStartAndEnd(startDay,pos);

        Calendar tempCalendar= DateUtil.convertStringToCalendarYMD(startDate.get(0));

        ArrayList<String> date = new ArrayList<>();
        date.add(startDate.get(0));
        tempCalendar.add(Calendar.DATE,pastDate-1);
        date.add(DateUtil.getStringDateAsYYYYMMdd(tempCalendar));
        tempCalendar.add(Calendar.DATE,1);
        date.add(DateUtil.getStringDateAsYYYYMMdd(tempCalendar));

        return  date;
    }

    private static ArrayList<String> onDayStartAndLastDate(){
        Calendar tempCal= Calendar.getInstance();
        ArrayList<String> beDate=new ArrayList<>();
        beDate.add(DateUtil.getStringDateAsYYYYMMdd(tempCal));
        beDate.add(DateUtil.getStringDateAsYYYYMMdd(tempCal));
        tempCal.add(Calendar.DATE,1);
        beDate.add(DateUtil.getStringDateAsYYYYMMdd(tempCal));

        return  beDate;
    }

    private static ArrayList<String> requestWeeklyDateBetweenStartAndEnd(int pos) {
        int startDateNum, endDateNum;
        String startStr, endStr;
        ArrayList<String> resultItems = new ArrayList<>();

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Calendar todayCal = Calendar.getInstance();


        int dayOfWeek = todayCal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            startDateNum = -7 * pos - 6;
            endDateNum = -7 * pos ;

        } else {
            startDateNum = -7 * pos - (dayOfWeek - 2);
            endDateNum = -7 * pos + 8 - dayOfWeek;
        }


        startCal.add(Calendar.DATE, startDateNum);//주시작일

        startStr=startCal.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((startCal.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(startCal.get(Calendar.DATE));

        LOGI("WEEKLYTEST","startStr"+startStr);

        resultItems.add(startStr);

        endCal.add(Calendar.DATE, endDateNum);//주 마지막

        endStr=endCal.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((endCal.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(endCal.get(Calendar.DATE));
        resultItems.add(endStr);





        if(compareDate(todayCal,endCal)){


            todayCal.add(Calendar.DATE,1);
            endStr=todayCal.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((todayCal.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(todayCal.get(Calendar.DATE));

        }else{
            endCal.add(Calendar.DATE,1);

            endStr=endCal.get(Calendar.YEAR)+"-"+ DecimalFormatUtil.decimalFormat.format((endCal.get(Calendar.MONTH)+1))+"-"+ DecimalFormatUtil.decimalFormat.format(endCal.get(Calendar.DATE));

        }

        resultItems.add(endStr);

        return resultItems;


    }


    private static ArrayList<String> getTwelveWeeksStartAndEndDate(int pos){

        ArrayList<String> lastDate=requestWeeklyDateBetweenStartAndEnd(pos+1);
        ArrayList<String> startDate=requestWeeklyDateBetweenStartAndEnd(pos+12);


        ArrayList<String> date = new ArrayList<>();
        date.add(startDate.get(0));
        date.add(lastDate.get(1));
        date.add(lastDate.get(2));

        return  date;
    }



    public static int[] getWeekCnt(Calendar before, Calendar today){

        int[] weekCnt=new int[7];

        int beWeek=before.get(Calendar.DAY_OF_WEEK); //1~7 1일요일 월 화 수목 금 토
        int afterWeek=today.get(Calendar.DAY_OF_WEEK);


        // 5(일) 6 7  afterweek 3(월)

        int diffDate=getDiffDateAsCalendar(before,today);


        int tempCnt=diffDate/7;

        if(tempCnt==0){// 일주일이 다되지 않는경우
//  6   3    6 7 1 2 3
            for (int i = 0; i < weekCnt.length; i++) {
                weekCnt[i] = 1;
            }
        }else {

            for (int i = 0; i < weekCnt.length; i++) {

                if (beWeek <= (i + 1) || afterWeek >= (i + 1)) {

                    weekCnt[i] = tempCnt + 1;
                } else {
                    weekCnt[i] = tempCnt;
                }

            }

        }

        return  weekCnt;



    }
    public static int[] getWeekCntAsZero(Calendar before, Calendar today){

        int[] weekCnt=new int[7];

        int beWeek=before.get(Calendar.DAY_OF_WEEK); //1~7 1일요일 월 화 수목 금 토
        int afterWeek=today.get(Calendar.DAY_OF_WEEK);

        //일요일 시작   화요일 끝
        //1       3
        //diff 3
        //

        // 5(일) 6 7  afterweek 3(월)

        int diffDate=getDiffDateAsCalendar(before,today);

        LOGI("REPEATTEST","diffDate"+diffDate);
        LOGI("REPEATTEST","beWeek"+beWeek);//5
        LOGI("REPEATTEST","afterWeek"+afterWeek);//3

        int tempCnt=diffDate/7;

            for (int i = 0; i < weekCnt.length; i++) {
                if (beWeek <= (i + 1) || afterWeek >= (i + 1)) {
                    //6                     3
                    // 5,6
                    weekCnt[i] = tempCnt + 1;
                    LOGI("REPEATTEST","  weekCnt[i]+i"+i+""+(tempCnt +1));

                } else {
                    weekCnt[i] = tempCnt;
                    LOGI("REPEATTEST","  weekCnt[i]"+i+""+tempCnt);
                }
        }

        return  weekCnt;



    }
    public static ArrayList<String> requestStartAndLastDate(int startDay, int pos, int dateType, int categoryCode){

        switch (dateType){
            case Constants.DateType.JUST_YEAR:
                return requestDateBetweenStartAndEnd(startDay,pos);
            case Constants.DateType.YEAR:
                return yearMonthDayStartWithCurrentMonth(startDay,pos);
            case Constants.DateType.THREE_MONTH:
                return threeMonthDayStartWithCurrentMonth(startDay,pos);
            case Constants.DateType.SIX_MONTH:
                return sixMonthDayStartWithCurrentMonth(startDay,pos);
            case Constants.DateType.ONE_DAY:
                return onDayStartAndLastDate();
            case Constants.DateType.TWELVE_WEEKS:
                return getTwelveWeeksStartAndEndDate(pos);
            case Constants.DateType.EXPECTED:
                return requestDateBetweenStartAndEndAsExpected(startDay,pos);
            default:
                if(categoryCode==Constants.CategoryCode.WEEKLY){
                    return requestWeeklyDateBetweenStartAndEnd(pos);
                }else{
                    return requestDateBetweenStartAndEnd(startDay,pos);
                }
        }
    }


    public static String convertDayOfWeekStr(Context mContext, int dayOfWeek){
        mContext = mContext.getApplicationContext();
        switch (dayOfWeek){
            case Calendar.SUNDAY:
                return mContext.getString(R.string.sunday_short);
            case Calendar.MONDAY:
                return mContext.getString(R.string.monday_short);
            case Calendar.TUESDAY:
                return mContext.getString(R.string.tuesday_short);
            case Calendar.WEDNESDAY:
                return mContext.getString(R.string.wednesday_short);
            case Calendar.THURSDAY:
                return mContext.getString(R.string.thursday_short);
            case Calendar.FRIDAY:
                return mContext.getString(R.string.friday_short);
            case Calendar.SATURDAY:
                return mContext.getString(R.string.saturday_short);
        }

        return  "";

    }

    public static int getWeeklyPos(int monthlyPos,Context mContext){
        mContext = mContext.getApplicationContext();
        Calendar startCalendar=requestStartDate(mContext,monthlyPos);
        for(int i=monthlyPos*4 ;;i++){
            if(startCalendar.compareTo(requestWeeklyEndCalendar(i))>0){
                return  i;
            }
        }
    }

    public static int getMonthlyPos(int weeklyPos,Context mContext){
        mContext = mContext.getApplicationContext();
        Calendar endCalendar=requestWeeklyEndCalendar(weeklyPos);
        for(int i=weeklyPos/4 ;;i++){
            if(endCalendar.compareTo(requestStartDate(mContext,i))>0){
                return  i;
            }
        }
    }

    public static String calculateRepeatDate(String originDateStr, int repeatKind){

        Calendar originCalendar = DateUtil.convertStringToCalendarFULL(originDateStr);

        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, originCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, originCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, originCalendar.get(Calendar.SECOND));

        if(repeatKind== REPEAT_EVERY_WEEK.ordinal()) {
            calendar.set(Calendar.DAY_OF_WEEK, originCalendar.get(Calendar.DAY_OF_WEEK));
        }else if(repeatKind== REPEAT_EVERY_MONTH.ordinal()){
            calendar.set(Calendar.DATE, originCalendar.get(Calendar.DATE));
        }



        return DateUtil.getStringDateAsYYYYMMddHHmmss(calendar);
    }


    public static Calendar getFullMoonCalendar(){
        Calendar todayCal= Calendar.getInstance();

        for(String fullMoon:FULL_MOON_LIST){
            Calendar fullMoonCal=DateUtil.convertStringToCalendarFULL(fullMoon);
            if(compareDate(todayCal,fullMoonCal)){

                int todayDay=todayCal.get(Calendar.DATE);
                int todayMonth=todayCal.get(Calendar.MONTH);
                int fullMoonDay=fullMoonCal.get(Calendar.DATE);
                int fullMoonMonth=fullMoonCal.get(Calendar.MONTH);

                if(todayMonth==fullMoonMonth&&fullMoonDay-todayDay<4){
                    return  fullMoonCal;
                }
                break;
            }
        }
        return  null;
    }
}