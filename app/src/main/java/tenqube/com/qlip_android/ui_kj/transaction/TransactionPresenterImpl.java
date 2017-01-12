package tenqube.com.qlip_android.ui_kj.transaction;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 9. 28..
 */

public class TransactionPresenterImpl implements TransactionPresenter{


    private TransactionActivity mActivity;
    private TransactionModel mModel;


    public TransactionPresenterImpl(TransactionActivity activity) {

        this.mActivity = activity;
        this.mModel = new TransactionModel(mActivity);


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


    public ArrayList<UserTransactionData> loadTransactionDataList(){


        return mModel.loadUserTransactionDataList();
    }

    @Override
    public ArrayList<UserTransactionData> monthTransactionDataList() {
        return mModel.monthLoadUserTransactionDataList();
    }
}
