package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.data.BankMapTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class BankMapTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " BANK_MAP";
    public static final String ALIAS = " mBankMap.";
    public static final String AS_ALIAS = " AS mBankMap ";
    public static final String COLUMN_BANK_ID="bank_id_pk";

    public static final String COLUMN_BANK_REP_ID = "rep_id";
    public static final String COLUMN_BANK_SUB_ID = "sub_id";

public static final String SQL_CREATE_TABLE =
        CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " (" +
                COLUMN_BANK_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                COLUMN_BANK_REP_ID +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+ COMMA_SEP +
                COLUMN_BANK_SUB_ID+INTEGER_TYPE+NOT_NULL + DEFAULT +DEFAULT_INT+ COMMA_SEP +
                UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                TransactionsTable.COLUMN_SEVER_SUCCESS + INTEGER_TYPE +NOT_NULL+DEFAULT+ DEFAULT_INT +
            " )";


    public static final String indexing = "CREATE INDEX bank_map_idx ON " + TABLE_NAME + " ("+ COLUMN_BANK_REP_ID+","+COLUMN_BANK_SUB_ID+")";

    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;

    public static BankMapTableData populateModel(Cursor c) {
        BankMapTableData model = new BankMapTableData();
        model._id = c.getInt(c.getColumnIndex(COLUMN_BANK_ID));
        model.repId = c.getInt(c.getColumnIndex(COLUMN_BANK_REP_ID));
        model.subId = c.getInt(c.getColumnIndex(COLUMN_BANK_SUB_ID));
        return model;
    }

    public static ContentValues populateContent(Context context, BankMapTableData model) {
        if(model.deleteType == 0) model.deleteType = 1;
        ContentValues values = new ContentValues();
        values.put(COLUMN_BANK_REP_ID, model.repId);
        values.put(COLUMN_BANK_SUB_ID, model.subId);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        return values;
    }


}
