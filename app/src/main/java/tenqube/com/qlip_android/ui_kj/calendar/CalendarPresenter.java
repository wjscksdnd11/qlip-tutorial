package tenqube.com.qlip_android.ui_kj.calendar;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 10. 10..
 */

public interface CalendarPresenter {
    void settingToolbar(Toolbar toolbar);
    ArrayList<String> loadCalendarDataList(int month);
    HashMap<String,UserTransactionData> loadTransactionDataMap(String date,int dw_type);
    String initData();
}
