package tenqube.com.qlip_android.ui_kj.calendarsample;

import java.io.Serializable;
import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 11. 1..
 */

public class CalendarSpentData implements Serializable {
    public boolean isChecked;
    public int day;
    public String date;
    public ArrayList<UserTransactionData> spentdata;

    public ArrayList<UserTransactionData> getSpentdata() {
        spentdata = new ArrayList<>();
        return spentdata;
    }


}
