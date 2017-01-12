package tenqube.com.qlip_android.ui_kj.calendar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.data.UserTransactionData;

public class CalendarActivity extends BaseActivity {


    private TextView tvDate;
    private ViewPager pager;
    private CalendarAdapter mAdapter;
    public CalendarPresenter calendarPresenter;
    public HashMap<String, UserTransactionData> dayMap;
    public HashMap<String, UserTransactionData> spentMap;
    private int month;
    public final static String userData = "userTransactionData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) {
            return;
        }
        setContentView(R.layout.activity_calendar);

        calendarPresenter = new CalendarPresenterImpl(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        calendarPresenter.settingToolbar(getActionBarToolbar(toolbar));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(CalendarActivity.this, 7);

            recyclerView.setLayoutManager(gridLayoutManager);
            mAdapter = new CalendarAdapter(this);
            dayMap = new HashMap<>();
            spentMap = new HashMap<>();
            recyclerView.setAdapter(mAdapter);
            mAdapter.addAll(calendarPresenter.loadCalendarDataList(month));
            mAdapter.notifyDataSetChanged();
            mAdapter.addIncomeMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 0));
            mAdapter.notifyDataSetChanged();
            mAdapter.addSpentMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 1));
            mAdapter.notifyDataSetChanged();





            mAdapter.setOnAdapterItemClickListener(new CalendarAdapter.OnAdapterItemClickLIstener() {
                @Override
                public void onAdapterItemClick(CalendarAdapter adapter, View view, UserTransactionData userTransactionData, int position) {
                    Toast.makeText(CalendarActivity.this, position+"", Toast.LENGTH_SHORT).show();

                }
            });
        }


        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setText(calendarPresenter.initData());
        Button btn_next = (Button) findViewById(R.id.next);
        Button btn_prev = (Button) findViewById(R.id.prev);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                month = month + 1;
                mAdapter.addAll(calendarPresenter.loadCalendarDataList(month));
                mAdapter.notifyDataSetChanged();

                tvDate.setText(calendarPresenter.initData());
                mAdapter.addIncomeMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 0));
                mAdapter.notifyDataSetChanged();

                mAdapter.addSpentMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 1));
                mAdapter.notifyDataSetChanged();
                Log.i("hashmap", spentMap.size() + "");
            }
        });


        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = month - 1;
                mAdapter.addAll(calendarPresenter.loadCalendarDataList(month));
                mAdapter.notifyDataSetChanged();

                tvDate.setText(calendarPresenter.initData());
                mAdapter.addIncomeMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 0));
                mAdapter.notifyDataSetChanged();

                mAdapter.addSpentMap(calendarPresenter.loadTransactionDataMap(calendarPresenter.initData(), 1));
                mAdapter.notifyDataSetChanged();

            }
        });


    }

}