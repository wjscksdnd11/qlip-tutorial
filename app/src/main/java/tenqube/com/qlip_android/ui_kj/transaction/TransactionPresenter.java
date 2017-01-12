package tenqube.com.qlip_android.ui_kj.transaction;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 9. 28..
 */

public interface TransactionPresenter {

    void settingToolbar(Toolbar toolbar);
    public ArrayList<UserTransactionData> loadTransactionDataList();
    public ArrayList<UserTransactionData> monthTransactionDataList();

}
