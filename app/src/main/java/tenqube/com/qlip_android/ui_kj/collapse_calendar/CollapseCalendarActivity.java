package tenqube.com.qlip_android.ui_kj.collapse_calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.joda.time.LocalDate;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.ui_kj.collapse_calendar.manager.CalendarManager;
import tenqube.com.qlip_android.ui_kj.collapse_calendar.manager.ResizeManager;

public class CollapseCalendarActivity extends AppCompatActivity {

    private CollapseCalendarView mCalendarView;
    private TextView textView;
    private ResizeManager mResizeManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_calendar);
        CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, LocalDate.now(), LocalDate.now().plusYears(1));
        mCalendarView = (CollapseCalendarView) findViewById(R.id.calendar);
        mCalendarView.init(manager);

    }
}
