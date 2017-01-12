package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.OilNearByCompanyTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class OilNearByCompanyTable extends AbstractTable implements BaseColumns {


    public static final String TABLE_NAME = " OIL_NEAR_BY_COMPANIES ";
    public static final String ALIAS = " near.";
    public static final String AS_ALIAS = " AS near ";
    public static final String COLUMN_NEAR_ID = "near_id_pk";
    public static final String COLUMN_NEAR_COMPANY_PRICE = "near_company_price";
    public static final String COLUMN_NEAR_COMPANY_NAME = "near_company_name";



    public static final String SQL_CREATE_ENTRIES =
                    CREATE_TABLE + TABLE_NAME + " (" +
                    COLUMN_NEAR_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                    COLUMN_NEAR_COMPANY_PRICE + REAL_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+ COMMA_SEP +
                    COLUMN_NEAR_COMPANY_NAME+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT +COMMA_SEP +
                    UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                    TransactionsTable.COLUMN_IDENTIFIER + REAL_TYPE + NOT_NULL+
                    ")";
    public static final String indexing = "CREATE INDEX qlip_oil_near_idx ON " + OilNearByCompanyTable.TABLE_NAME + " ("+TransactionsTable.COLUMN_IDENTIFIER+")";

    public static final String SQL_DELETE_ENTRIES =
          DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static OilNearByCompanyTableData populateModel(Cursor c) {
        OilNearByCompanyTableData model = new OilNearByCompanyTableData();
        model.nearId = c.getInt(c.getColumnIndex(COLUMN_NEAR_ID));
        model.price = c.getDouble(c.getColumnIndex(COLUMN_NEAR_COMPANY_PRICE));
        model.title = c.getString(c.getColumnIndex(COLUMN_NEAR_COMPANY_NAME));
        model.identifier = c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
        return model;
    }

    public static ContentValues populateContent(Context context, OilNearByCompanyTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NEAR_COMPANY_PRICE, model.price);
        values.put(COLUMN_NEAR_COMPANY_NAME, model.title==null||model.title.length()==0? Constants.NONE:model.title);
        values.put(TransactionsTable.COLUMN_IDENTIFIER, model.identifier);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        return values;
    }


}
