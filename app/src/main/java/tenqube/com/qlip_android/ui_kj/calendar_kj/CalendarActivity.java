package tenqube.com.qlip_android.ui_kj.calendar_kj;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.astuetz.PagerSlidingTabStrip;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;


public class CalendarActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,CalendarFragment.Callback
{

    private CalendarPresenter calendarPresenter;
    public ViewPager viewPager;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isFinishing()) {
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_kj);

        calendarPresenter = new CalendarPresenterImpl(CalendarActivity.this);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar = getActionBarToolbarLv2(toolbar);
        calendarPresenter.settingToolbar(toolbar);
        Spinner spinner = (Spinner) toolbar.findViewById(R.id.lv2_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lv2_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setVisibility(View.VISIBLE);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.tabLayout);
        calendarPresenter.initAdapter(viewPager);
        pagerSlidingTabStrip.setViewPager(viewPager);
        calendarPresenter.loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRefresh(final int parentPos, int childPos) {
        calendarPresenter.onRefresh(parentPos, childPos);
    }

}
