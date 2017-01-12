package tenqube.com.qlip_android.ui_kj.transaction;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.query.TransactionHelper;

/**
 * Created by tenqube on 2016. 9. 28..
 */

public class TransactionModel {

    private TransactionHelper transactionHelper;
    private Context mContext;


    public TransactionModel(Context mContext) {
        this.mContext = mContext;
        transactionHelper = new TransactionHelper(mContext);

    }

    public ArrayList<UserTransactionData> loadUserTransactionDataList(){

        ArrayList<UserTransactionData> userTransactionDataArrayList;


        userTransactionDataArrayList = transactionHelper.loadUserTransactionDataList();

        Log.i("transaction",userTransactionDataArrayList.size()+"");

        return userTransactionDataArrayList;



    }
    public ArrayList<UserTransactionData> loadExecptTypeUserTransactionDataList(){

        ArrayList<UserTransactionData> userTransactionDataArrayList;


        userTransactionDataArrayList = transactionHelper.loadExpectTypeUserTransactionDataList();

        Log.i("transaction",userTransactionDataArrayList.size()+"");

        return userTransactionDataArrayList;



    }

    public ArrayList<UserTransactionData> monthLoadUserTransactionDataList(){
        ArrayList<UserTransactionData> monthLoadUserTransactionDataList;

       monthLoadUserTransactionDataList = transactionHelper.loadYearUserTransactionDataList();

        return monthLoadUserTransactionDataList;
    }
}
