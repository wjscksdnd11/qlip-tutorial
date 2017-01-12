package tenqube.com.qlip_android.db.query;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;

/**
 * Created by tenqube on 2016. 9. 28..
 */

public class TransactionHelper extends QueryHelper{
    public TransactionHelper(Context context) {
        super(context);


    }



    public ArrayList<UserTransactionData> loadExpectTypeUserTransactionDataList(){
        ArrayList<UserTransactionData> userDataList = new ArrayList<>();

        String query  =
                SELECT+"*"+
                FROM+ TransactionsTable.TABLE_NAME+
                JOIN+ CardTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+" = " +
                CardTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+
                JOIN+ CategoryCodeTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+TransactionsTable.COLUMN_CATEGORY_CODE+" = "+
                CategoryCodeTable.TABLE_NAME+"."+ CategoryCodeTable.COLUMN_CATEGORY_CODE+
                JOIN+ UserCategoryConfigTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+" = "+
                UserCategoryConfigTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+
                WHERE+ UserCategoryConfigTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_EXCEPT_CATEGORY_TYPE+" = 1 ";


        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        userDataList.add(TransactionsTable.populateModel(c));
                        userDataList.get(c.getPosition()).cardName=(CardTable.populateModel(c).cardName);
                        userDataList.get(c.getPosition()).mediumCategory = (CategoryCodeTable.populateModel(c).mediumCategory);
                        userDataList.get(c.getPosition()).largeCategory = (CategoryCodeTable.populateModel(c).largeCategory);


                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        LOGI("cardname",userDataList.get(0).toString());


        return  userDataList;



    }

    public ArrayList<UserTransactionData> loadUserTransactionDataList(){
        ArrayList<UserTransactionData> userDataList = new ArrayList<>();
//        select count(idx) as count_idx,
//                sum(spent_money)
//        from transactiontable
//        group by substr(datetime,1,7)


        String query  = SELECT+"*"+
                FROM+ TransactionsTable.TABLE_NAME+ORDER_BY+ASC+
                JOIN+ CardTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+" = " +
                CardTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+
                JOIN+ CategoryCodeTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+TransactionsTable.COLUMN_CATEGORY_CODE+" = "+
                CategoryCodeTable.TABLE_NAME+"."+ CategoryCodeTable.COLUMN_CATEGORY_CODE+
                JOIN+ UserCategoryConfigTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+" = "+
                UserCategoryConfigTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID;



        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

                        Log.i("spent",Double.parseDouble(c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_MONEY)))+"");
                        Log.i("spent",c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)));
                        Log.i("spent",c.getString(c.getColumnIndex(TransactionsTable.COLUMN_DW_TYPE)));

                        userDataList.add(TransactionsTable.populateModel(c));
                        userDataList.add(TransactionsTable.populateModel(c));
                        userDataList.get(c.getPosition()).cardName=(CardTable.populateModel(c).cardName);
                        userDataList.get(c.getPosition()).mediumCategory = (CategoryCodeTable.populateModel(c).mediumCategory);
                        userDataList.get(c.getPosition()).largeCategory = (CategoryCodeTable.populateModel(c).largeCategory);

                        Log.i("spent",c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_LARGE_CATEGORY)));

                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

//           LOGI("cardname",userDataList.get(0).toString());


        return  userDataList;



    }
    public ArrayList<UserTransactionData> loadYearUserTransactionDataList(){
        ArrayList<UserTransactionData> userDataList = new ArrayList<>();


        String query  =
                SELECT+"*,"+"SUM("+TransactionsTable.COLUMN_SPENT_MONEY+") AS spent_money"+
                FROM+ TransactionsTable.TABLE_NAME+
                JOIN+ CardTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+" = " +
                CardTable.TABLE_NAME+"."+CardTable.COLUMN_CARD_ID+
                JOIN+ CategoryCodeTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+TransactionsTable.COLUMN_CATEGORY_CODE+" = "+
                CategoryCodeTable.TABLE_NAME+"."+ CategoryCodeTable.COLUMN_CATEGORY_CODE+
                JOIN+ UserCategoryConfigTable.TABLE_NAME+
                ON+TransactionsTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+" = "+
                UserCategoryConfigTable.TABLE_NAME+"."+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+
                GROUP_BY+"substr(spent_date,1,10)"+

                ORDER_BY+TransactionsTable.COLUMN_SPENT_DATE+DESC;

        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        userDataList.add(TransactionsTable.populateModel(c));
                        userDataList.get(c.getPosition()).cardName=(CardTable.populateModel(c).cardName);
                        userDataList.get(c.getPosition()).mediumCategory = (CategoryCodeTable.populateModel(c).mediumCategory);
                        userDataList.get(c.getPosition()).largeCategory = (CategoryCodeTable.populateModel(c).largeCategory);


                        Log.i("spent",Double.parseDouble(c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_MONEY)))+"");
                        Log.i("spent",c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)));
                        Log.i("spent",c.getString(c.getColumnIndex(TransactionsTable.COLUMN_DW_TYPE)));
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        LOGI("month",userDataList.get(0).toString());


        return  userDataList;


    }
}
