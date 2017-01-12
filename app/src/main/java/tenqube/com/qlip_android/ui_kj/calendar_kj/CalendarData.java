package tenqube.com.qlip_android.ui_kj.calendar_kj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tenqube on 16. 2. 25..
 */
public class CalendarData implements Serializable {

    public String titleDate;
    public Calendar calendar;
    public ArrayList<CalendarSubData> calendarSubList = new ArrayList<>();
    @Override
    public String toString() {
        return "CalendarData{" +
                "titleDate='" + titleDate + '\'' +
                ", calendar=" + calendar +
                ", calendarSubList=" + calendarSubList +
                '}';
    }
}
