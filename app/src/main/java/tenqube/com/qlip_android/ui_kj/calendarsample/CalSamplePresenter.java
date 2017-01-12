package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 10. 17..
 */

public interface CalSamplePresenter {
    void settingToolbar(Toolbar toolbar);
    void onRefresh(int parentPos, int childPos);
    void initAdapter(ViewPager viewpager,int spinner_type,int sumType);
    void loadData();
    ArrayList<CalendarData>getCalendarList();
//    HashMap<String,UserTransactionData> spentHashMap(String start_date, String end_date, int spinner_type);
    ArrayList<UserTransactionData> spentArrayList(String start_date,String end_date,int spinner_type, int sumType);
    void changeSpinner(int dw_type,int sumType);




//    void setData(int position,int dw_type,int sumType);
}
