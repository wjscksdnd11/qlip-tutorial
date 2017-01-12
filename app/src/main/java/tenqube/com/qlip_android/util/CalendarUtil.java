package tenqube.com.qlip_android.util;

import java.util.ArrayList;
import java.util.Calendar;

import static tenqube.com.qlip_android.util.DateUtil.compareDate;

/**
 * Created by tenqube on 2016. 10. 19..
 */

public class CalendarUtil {
    /**
     *
     * @param starDay 시작일
     * @param position 이번달 0 지난달 1 지지난달 2 ...
     * @return resultDateItems ['2016-10-01','2016-10-31','2016-10-20']
     * resultDateItems[0] = startDate ,
     * resultDateItems[1] = realEndDate, (예산 계산할때 필요해서 미리 뽑아놓음)
     * resultDateItems[2] = endDate (실제 쿼리 사용),(오늘날짜면 오늘날짜 + 1 , 과거면 마자막일 + 1
     */

    public static ArrayList<String> getStartEndDate(int starDay, int position) {//시작일 구하기0
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
}
