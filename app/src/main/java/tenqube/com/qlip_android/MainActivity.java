package tenqube.com.qlip_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.db.query.CommonHelper;
import tenqube.com.qlip_android.ui_kj.calendarviewsample.CalViewActivity;
import tenqube.com.qlip_android.ui_kj.collapse_calendar.CollapseCalendarActivity;
import tenqube.com.qlip_android.ui_kj.lv0.Lv0Activity;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_kj).setOnClickListener(this);
        findViewById(R.id.btn_crud).setOnClickListener(this);
        findViewById(R.id.btn_tran).setOnClickListener(this);
//        findViewById(R.id.btn_reg).setOnClickListener(this);
//        findViewById(R.id.btn_calendar_sample).setOnClickListener(this);
        findViewById(R.id.btn_cal).setOnClickListener(this);

        new CommonHelper(getApplicationContext()).selectAll();

    }

    @Override
    public void onClick(View v) {
            Intent intent;
        switch (v.getId()){
            case R.id.btn_kj:
                intent = new Intent(MainActivity.this, Lv0Activity.class);
                startActivity(intent);
                break;
//
            case R.id.btn_crud:
                intent = new Intent(MainActivity.this, Lv0Activity.class);
                startActivity(intent);
                break;
//
            case R.id.btn_tran:
                intent = new Intent(MainActivity.this, CollapseCalendarActivity.class);
                startActivity(intent);
                break;
//
//            case R.id.btn_reg:
//                new RegHandler().regPractice();
//                break;
            case R.id.btn_cal:
                intent = new Intent(MainActivity.this, CalViewActivity.class);
                startActivity(intent);
                break;
//            case R.id.btn_calendar_sample:
//                intent = new Intent(MainActivity.this, CalSampleActivity.class);
//                startActivity(intent);
//                break;

        }


    }





}
