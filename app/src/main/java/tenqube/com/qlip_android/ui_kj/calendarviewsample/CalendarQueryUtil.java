package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.TagMapTableData;
import tenqube.com.qlip_android.db.query.QueryHelper;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;


public class CalendarQueryUtil extends QueryHelper {

    private static final int SUM_TYPE = 0; //합산
    private static final int ITEM_TYPE = 1;// 건별
    private static final int DEFALUT = 2; // 내역
    private static final int INCOME = 0; //수입내역
    private static final int SPENT = 1;  //지출
    private static final int INCOMESPENT = 2;//수입,지출
    private static final int EXPECT_TYPE = 3;// 제외 내역
    private String select;
    private String where;

    private int spinnerType;


    public CalendarQueryUtil(Context context) {
        super(context);

    }

    /**
     *
     * @param
     * flag 제외 타입 플래그,기본 쿼리는 1, search에서는  spinnerType에 따른 0 or 1
     *@return ArrayList
     */
    private ArrayList<TagMapTableData> loadIdentifierList(int flag) {
        ArrayList<TagMapTableData> identifierList = new ArrayList<>();
        String query = SELECT + "*" +
                FROM + TagMapTable.TABLE_NAME +
                JOIN + TagTable.TABLE_NAME +
                ON + TagMapTable.TABLE_NAME + "." + TagTable.COLUMN_HASH_TAG_ID + " = " +
                TagTable.TABLE_NAME + "." + TagTable.COLUMN_HASH_TAG_ID +
                WHERE + TagTable.TABLE_NAME + "." + TagTable.COLUMN_EXCEPT_TYPE + "=" + flag;
        Cursor c = null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        TagMapTableData tagMapTableData = new TagMapTableData();
                        tagMapTableData.identifier = c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
                        tagMapTableData.tagId = c.getLong(c.getColumnIndex(TagTable.COLUMN_HASH_TAG_ID));
                        identifierList.add(tagMapTableData);
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

        return identifierList;

    }


    //합산보기 (지출,수입,지출/수입)
    public CalendarQueryUtil setSumType(int sum_type) {
        switch (sum_type) {
            case SUM_TYPE:

                select = SELECT + "* ";
                break;
            case ITEM_TYPE:
                select = SELECT + "* ";
                break;

            case DEFALUT:
                select = SELECT + "* ";
                break;
        }

        return this;
    }

    public CalendarQueryUtil setSpinnerType(String startdate, String enddate, int dw_type) {
        this.spinnerType = dw_type;
        ArrayList<TagMapTableData> identifierTempList = loadIdentifierList(1);
        ArrayList<String> identifierList = new ArrayList<>();
        for (TagMapTableData data : identifierTempList) {
            identifierList.add(data.identifier + "");
        }
        String identifiers = TextUtils.join(",", identifierList);
        switch (dw_type) {
            case INCOME:
                where = WHERE + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))>= strftime('%Y-%m-%d', '" + startdate + "')" +
                        AND + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))<= strftime('%Y-%m-%d', '" + enddate + "')" +
                        AND + TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_DW_TYPE + " = " + dw_type +
                        AND + "(" + UserCategoryConfigTable.COLUMN_EXCEPT_CATEGORY_TYPE + "=0" +
                        AND + CardTable.COLUMN_EXCEPT_TYPE + "=0" +
                        AND + TransactionsTable.COLUMN_IDENTIFIER +
                        NOT_IN + "(" + identifiers + "))";

                break;
            case SPENT:
                where = WHERE + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))>= strftime('%Y-%m-%d', '" + startdate + "')" +
                        AND + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))<= strftime('%Y-%m-%d', '" + enddate + "')" +
                        AND + TransactionsTable.COLUMN_DW_TYPE + " = " + dw_type +
                        AND + "(" + UserCategoryConfigTable.COLUMN_EXCEPT_CATEGORY_TYPE + "=0" +
                        AND + CardTable.COLUMN_EXCEPT_TYPE + "=0" +
                        AND + TransactionsTable.COLUMN_IDENTIFIER +
                        NOT_IN + "(" + identifiers + "))";

                break;
            case INCOMESPENT:
                where = WHERE + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))>= strftime('%Y-%m-%d', '" + startdate + "')" +
                        AND + "strfTime('%Y-%m-%d', substr(spent_date, 1, 10))<= strftime('%Y-%m-%d', '" + enddate + "')" +
                        AND + "("+ UserCategoryConfigTable.COLUMN_EXCEPT_CATEGORY_TYPE + "=0" +
                        AND + CardTable.COLUMN_EXCEPT_TYPE + "=0" +
                        AND + TransactionsTable.COLUMN_IDENTIFIER +
                        NOT_IN + "(" + identifiers + "))";
                break;
            case EXPECT_TYPE:
                where = WHERE + "strfTime('%Y-%m-%d')>= strftime('%Y-%m-%d', '" + startdate + "')" +
                        AND + "strfTime('%Y-%m-%d')<= strftime('%Y-%m-%d', '" + enddate + "')" +
                        AND + "(" + UserCategoryConfigTable.COLUMN_EXCEPT_CATEGORY_TYPE + "=1" +
                        OR + CardTable.COLUMN_EXCEPT_TYPE + "=1"+
                        OR+ TransactionsTable.COLUMN_IDENTIFIER +
                        IN + "(" + identifiers + "))";
        }

        return this;
    }

    public CalendarQueryUtil setSearchData(ArrayList<SearchStringData> searchStringDataList) {
        String searchWhere = "";
        ArrayList<String> identifierList = new ArrayList<>();
        ArrayList<String> cardList = new ArrayList<>();
        ArrayList<String> categoryList = new ArrayList<>();
        ArrayList<String> hashList = new ArrayList<>();
            ArrayList<TagMapTableData> identifierTempList;
        if (spinnerType == 3) {
            identifierTempList = loadIdentifierList(1);
        } else {
            identifierTempList = loadIdentifierList(0);
        }
        if (searchStringDataList != null) {
            for (SearchStringData searchStringData : searchStringDataList) {
                switch (searchStringData.searchType) {

                    case COUNTRY:
                        searchWhere += searchStringData.searchQueryStr;
                        break;

                    case KEYWORD:
                        searchWhere += searchStringData.searchQueryStr;
                        break;

                    case CARD:
                        cardList.add(searchStringData.searchQueryStr);
                        break;

                    case CATEGORY:
                        categoryList.add(searchStringData.searchQueryStr);
                        break;

                    case HASHTAG:

                        hashList.add(searchStringData.searchQueryStr);
                        if (identifierTempList != null && identifierTempList.size() > 0) {
                            for (int i = 0; i < identifierTempList.size(); i++) {
                                if ((identifierTempList.get(i).tagId + "").equals(searchStringData.searchQueryStr)) {
                                    identifierList.add(identifierTempList.get(i).identifier + "");
                                }
                            }
                        }
                        break;

                    case INSTALLMENT:
                        searchWhere += searchStringData.searchQueryStr;
                        break;

                    case REPEAT:
                        searchWhere += searchStringData.searchQueryStr;
                        break;

                }
            }
            if (categoryList.size() > 0) {
                String largeCategoryCodes = TextUtils.join(", ", categoryList);
                searchWhere += AND + UserCategoryConfigTable.TABLE_NAME + "." + UserCategoryConfigTable.COLUMN_CATEGORY_CODE + IN + " (" + largeCategoryCodes + ")";
            }
            if (hashList.size() > 0) {
                String identifiers = TextUtils.join(", ", identifierList);
                String temp = AND + TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_IDENTIFIER + IN + "(" + identifiers + ")";
                searchWhere += temp;
            }
            if (cardList.size() > 0) {
                String cardNames = TextUtils.join(OR, cardList);
                searchWhere += AND + "("+cardNames+")";
            }


            where += searchWhere;
           LOGI("whereQueryUtil", searchWhere);
        }
        return this;
    }

    public String Build() {
       String query = select +
                FROM + TransactionsTable.TABLE_NAME +
                JOIN + CardTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + CardTable.COLUMN_CARD_ID + " = " +
                CardTable.TABLE_NAME + "." + CardTable.COLUMN_CARD_ID +
                JOIN + UserCategoryConfigTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID + " = " +
                UserCategoryConfigTable.TABLE_NAME + "." + UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID +
                JOIN + CategoryCodeTable.TABLE_NAME +
                ON + TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_CATEGORY_CODE + " = " +
                CategoryCodeTable.TABLE_NAME + "." + CategoryCodeTable.COLUMN_CATEGORY_CODE +
                where +
                ORDER_BY + TransactionsTable.COLUMN_SPENT_DATE + ASC;
        LOGI("query", query);
        return query;
    }
}
