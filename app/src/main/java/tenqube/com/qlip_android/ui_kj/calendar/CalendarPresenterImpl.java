package tenqube.com.qlip_android.ui_kj.calendar;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 10. 10..
 */

public class CalendarPresenterImpl implements CalendarPresenter {
    CalendarActivity mActivity;
    CalendarModel mModel;



    public CalendarPresenterImpl(CalendarActivity activity) {


        this.mActivity = activity;
        this.mModel = new CalendarModel(activity);

    }

    @Override
    public void settingToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.qlip_main_black_lv0));
    }


    public String initData() {


        return mModel.initData();
    }


    public ArrayList<String> loadCalendarDataList(int month){


        return mModel.loadCalendarDataList(month);
    }



    @Override
    public HashMap<String,UserTransactionData> loadTransactionDataMap(String date,int dw_type) {


        return mModel.loadMonthUserTransactionData(date,dw_type);
    }
}
