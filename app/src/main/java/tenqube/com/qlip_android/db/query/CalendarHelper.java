package tenqube.com.qlip_android.db.query;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;
import tenqube.com.qlip_android.ui_kj.calendarviewsample.CalendarQueryUtil;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;

/**
 * Created by tenqube on 2016. 10. 10..
 */

public class CalendarHelper extends QueryHelper {

    public CalendarHelper(Context context) {
        super(context);
    }

    // 건별 내역
    public ArrayList<UserTransactionData> loadMonthUserTransactionDataList(String date, int dw_type) {
        ArrayList<UserTransactionData> userDataList = new ArrayList<>();


        String query = SELECT + "*," + "SUM(" + TransactionsTable.COLUMN_SPENT_MONEY + ") AS spent_money" +
                FROM + TransactionsTable.TABLE_NAME +
                JOIN + CardTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + CardTable.COLUMN_CARD_ID + " = " +
                CardTable.TABLE_NAME + "." + CardTable.COLUMN_CARD_ID +
                JOIN + CategoryCodeTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_CATEGORY_CODE + " = " +
                CategoryCodeTable.TABLE_NAME + "." + CategoryCodeTable.COLUMN_CATEGORY_CODE +
                JOIN + UserCategoryConfigTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID + " = " +
                UserCategoryConfigTable.TABLE_NAME + "." + UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID +
                WHERE + TransactionsTable.COLUMN_DW_TYPE + " = " + 1 +
                GROUP_BY + "substr(spent_date,1,11)" +
                HAVING + "substr(spent_date,1,7) = " + "'" + date + "'" +
                ORDER_BY + TransactionsTable.COLUMN_SPENT_DATE + DESC;

        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        userDataList.add(TransactionsTable.populateModel(c));
                        userDataList.get(c.getPosition()).cardName = (CardTable.populateModel(c).cardName);
                        userDataList.get(c.getPosition()).mediumCategory = (CategoryCodeTable.populateModel(c).mediumCategory);
                        userDataList.get(c.getPosition()).largeCategory = (CategoryCodeTable.populateModel(c).largeCategory);
//                        Log.i("spent", Double.parseDouble(c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_MONEY))) + "");
//                        Log.i("spent", c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)));
//                        Log.i("spent", c.getString(c.getColumnIndex(TransactionsTable.COLUMN_DW_TYPE)));
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


        return userDataList;


    }

    //    일별 합 수입 지출
    public ArrayList<UserTransactionData> loadMonthUserCalendarDataList(String startdate, String enddate, int dw_type, int sumType) {
        ArrayList<UserTransactionData> monthList = new ArrayList<>();
        CalendarQueryUtil calendarQueryUtil = new CalendarQueryUtil(mContext);
        String query = calendarQueryUtil
                .setSpinnerType(startdate, enddate, dw_type)
                .setSumType(sumType)
                .Build();
//        Log.i("spenttype",startdate+" , "+enddate+" , "+dw_type+" , "+sumType);
        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
//                        Log.i("query",""+monthList.size());
//                        Log.i("spent_datsdfsdf", "cusordate :" + TransactionsTable.populateModel(c).spentMoney + " , " + TransactionsTable.populateModel(c).spentDate);
                        UserTransactionData userTransactionData = TransactionsTable.populateModel(c);
                        userTransactionData.cardName = c.getString(c.getColumnIndex(CardTable.COLUMN_CARD_NAME));
                        userTransactionData.largeCategory = c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_LARGE_CATEGORY));
                        userTransactionData.mediumCategory = c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_MEDIUM_CATEGORY));
                        monthList.add(userTransactionData);
//                        Log.i("spenttype", "지출 날짜 : "+c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)));
//                        Log.i("spenttype"," 제외 타입 : "+ c.getString(c.getColumnIndex(CardTable.COLUMN_EXCEPT_TYPE)));
//                        Log.i("spenttype"," 지출 타입 : "+ c.getString(c.getColumnIndex(TransactionsTable.COLUMN_DW_TYPE)));
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
        Log.i("spenttype",monthList.size()+" 쿼리부분의 데이터 개수, ");


        return monthList;
    }

    public HashMap<String, ArrayList<UserTransactionData>> loadMonthUserCalendarDataMap(String startdate,String enddate,int dw_type, int sumType,ArrayList<SearchStringData> searchStringDataList) {
        HashMap<String, ArrayList<UserTransactionData>> monthDataMap = new HashMap<>();
        Log.i("queryStartDate",startdate);
        CalendarQueryUtil calendarQueryUtil = new CalendarQueryUtil(mContext);
        String query = calendarQueryUtil
                .setSpinnerType(startdate, enddate, dw_type)
                .setSumType(sumType)
                .setSearchData(searchStringDataList)
                .Build();
        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        Log.i("datedatas"," query, "+c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_CATEGORY_CODE))+" , "+c.getString(c.getColumnIndex(UserCategoryConfigTable.COLUMN_CATEGORY_CODE)));
                        String tempkey = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)).substring(0,10);
                        Log.i("datedatasCategoryCode"," query, "+(c.getString(c.getColumnIndex(UserCategoryConfigTable.COLUMN_CATEGORY_CODE))).substring(0,2));
                        UserTransactionData userTransactionData = TransactionsTable.populateModel(c);
                        userTransactionData.cardName = c.getString(c.getColumnIndex(CardTable.COLUMN_CARD_CHANGE_NAME));
                        userTransactionData.categoryCode = c.getString(c.getColumnIndex(UserCategoryConfigTable.COLUMN_CATEGORY_CODE));
                        userTransactionData.categoryConfigId = c.getLong(c.getColumnIndex(UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID));
                        userTransactionData.largeCategory = c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_LARGE_CATEGORY));
                        userTransactionData.mediumCategory = c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_MEDIUM_CATEGORY));

                        if (monthDataMap.containsKey(tempkey)){
                            monthDataMap.get(tempkey).add(userTransactionData);
                        }else{
                            ArrayList<UserTransactionData> spentData = new ArrayList<>();
                            spentData.add(userTransactionData);
                            monthDataMap.put(tempkey,spentData);

                        }
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
        return monthDataMap;
    }

    public String loadTabTitles() {
        String tabtitle = "";
        String query = SELECT + "MAX(" + TransactionsTable.COLUMN_SPENT_DATE + ") AS spent_date" +
                FROM + TransactionsTable.TABLE_NAME +
                GROUP_BY + "substr(spent_date,1,10)" +
                ORDER_BY + TransactionsTable.COLUMN_SPENT_DATE + DESC;
        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
//                        Log.i("spent_date", "최근날짜 : " + c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE)));
                        tabtitle = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE));
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
//        LOGI("lastday", tabtitle);
        return tabtitle;
    }

    public void removeIdentifireList(ArrayList<Long> selectedIdentifireList) {

        String identifiers = TextUtils.join(",",selectedIdentifireList);

        String query  =
                DELETE+FROM+TransactionsTable.TABLE_NAME+
                        WHERE+TransactionsTable.COLUMN_IDENTIFIER+IN+"("+identifiers+")";
        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

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

        query =DELETE+FROM+ TagMapTable.TABLE_NAME+
            WHERE+ TransactionsTable.COLUMN_IDENTIFIER+IN+"("+identifiers+")";

        c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

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


    }

}
