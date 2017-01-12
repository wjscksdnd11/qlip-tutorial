package tenqube.com.qlip_android.ui_kj.calendar_kj;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


public interface CalendarPresenter {
    void settingToolbar(Toolbar toolbar);
    void initAdapter(ViewPager viewpager);
    void loadData();
    void onRefresh(int parentPos, int childPos);
    ArrayList<CalendarData> getList();
}
