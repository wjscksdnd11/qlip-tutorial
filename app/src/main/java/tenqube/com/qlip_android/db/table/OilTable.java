package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.OilTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class OilTable extends AbstractTable implements BaseColumns {


    public static final String TABLE_NAME = " OILS";
    public static final String COLUMN_OIL_ID = "oil_id_pk";
    public static final String ALIAS = " oil.";
    public static final String AS_ALIAS = " AS oil ";
    public static final String COLUMN_COMPANY_PRICE = "company_price";//oilPrice isMatched
    public static final String COLUMN_CITY ="city";//해당 지역
    public static final String COLUMN_CITY_AVG="city_avg";//시평균
    public static final String COLUMN_TOTAL_AVG ="total_avg";//전체평균
    public static final String COLUMN_IS_COMPANY_MATCHED ="is_matched";//전체평균

    public static final String SQL_CREATE_ENTRIES =
            CREATE_TABLE + TABLE_NAME + " (" +
                    COLUMN_OIL_ID +INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                    COLUMN_COMPANY_PRICE+REAL_TYPE+ NOT_NULL+DEFAULT+" -1" +COMMA_SEP +
                    COLUMN_CITY + TEXT_TYPE +NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                    COLUMN_CITY_AVG+REAL_TYPE+ NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP +
                    COLUMN_TOTAL_AVG+REAL_TYPE+ NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP +
                    UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                    COLUMN_IS_COMPANY_MATCHED+INTEGER_TYPE+ NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP + //매칭되면 1 안되면 0
                    TransactionsTable.COLUMN_IDENTIFIER + REAL_TYPE + NOT_NULL+
                ")";
    public static final String indexing = "CREATE INDEX qlip_oil_idx ON " + OilTable.TABLE_NAME + " ("+COLUMN_IS_COMPANY_MATCHED+","+TransactionsTable.COLUMN_IDENTIFIER+")";


    public static final String SQL_DELETE_ENTRIES =
            DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static OilTableData populateModel(Cursor c) {
        OilTableData model = new OilTableData();
        model.oilId = c.getInt(c.getColumnIndex(COLUMN_OIL_ID));
        model.oilPrice = c.getDouble(c.getColumnIndex(COLUMN_COMPANY_PRICE));
        model.companyName = c.getString(c.getColumnIndex(COLUMN_CITY));
        model.cityAvg = c.getDouble(c.getColumnIndex(COLUMN_CITY_AVG));
        model.totalAvg = c.getDouble(c.getColumnIndex(COLUMN_TOTAL_AVG));
        model.isMatched = c.getInt(c.getColumnIndex(COLUMN_IS_COMPANY_MATCHED));
        model.identifier = c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));



        return model;
    }

    public static ContentValues populateContent(Context context, OilTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_PRICE, model.oilPrice);
        values.put(COLUMN_CITY, model.companyName==null||model.companyName.length()==0? Constants.NONE:model.companyName);
        values.put(COLUMN_CITY_AVG, model.cityAvg);
        values.put(COLUMN_TOTAL_AVG, model.totalAvg);
        values.put(COLUMN_IS_COMPANY_MATCHED, model.isMatched);
        values.put(TransactionsTable.COLUMN_IDENTIFIER, model.identifier);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        return values;
    }


}
