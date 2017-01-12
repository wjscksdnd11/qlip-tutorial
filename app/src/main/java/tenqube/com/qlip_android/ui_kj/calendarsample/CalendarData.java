package tenqube.com.qlip_android.ui_kj.calendarsample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tenqube on 2016. 10. 18..
 */

public class CalendarData implements Serializable{
// 달에 해당 하는 데이터
    public String month;
    public String year;
    public String yearMonth;
    public Calendar calendar;
    public ArrayList<CalendarSpentData> dayDataList;


}
