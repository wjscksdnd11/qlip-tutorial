package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.FullSmsTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class FullSmsTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " FULL_SMS";
    public static final String AS_ALIAS = " AS full ";
    public static final String ALIAS = " full.";
    public static final String COLUMN_FULL_ID="full_id_pk";
    public static final String COLUMN_FULL_SMS = "full_sms";
    public static final String COLUMN_SMS_TEL = "sms_tel";
    public static final String COLUMN_ORIGIN_SENDER = "origin_sender";
    public static final String COLUMN_SMS_TYPE = "sms_type";


    public static final String indexing = "CREATE INDEX qlip_full_sms_idx ON " + FullSmsTable.TABLE_NAME + " ("+TransactionsTable.COLUMN_IDENTIFIER+")";

    //    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_TABLE =
                    CREATE_TABLE + TABLE_NAME + " (" +
                            COLUMN_FULL_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                            COLUMN_FULL_SMS + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT +COMMA_SEP+
                            COLUMN_SMS_TEL + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT +COMMA_SEP+
                            COLUMN_ORIGIN_SENDER + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT +COMMA_SEP+

                            COLUMN_SMS_TYPE + INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP+
                            UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                            TransactionsTable.COLUMN_IDENTIFIER+REAL_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+
                            " )";

    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;

    public static FullSmsTableData populateModel(Cursor c) {
        FullSmsTableData model = new FullSmsTableData();
        model.fullId = c.getInt(c.getColumnIndex(COLUMN_FULL_ID));
        model.fullSms = c.getString(c.getColumnIndex(COLUMN_FULL_SMS));
        model.sender = c.getString(c.getColumnIndex(COLUMN_SMS_TEL));
        model.originSender = c.getString(c.getColumnIndex(COLUMN_ORIGIN_SENDER));
        model.smsType=c.getInt(c.getColumnIndex(COLUMN_SMS_TYPE));
        model.identifier=c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
        return model;
    }

    public static ContentValues populateContent(Context context, FullSmsTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_SMS, model.fullSms==null||"".equals(model.fullSms)? Constants.NONE:model.fullSms.replace("'","").trim());
        values.put(COLUMN_SMS_TEL, model.sender==null||"".equals(model.sender)?Constants.NONE:model.sender.replace("'","").trim());
        values.put(COLUMN_ORIGIN_SENDER, model.originSender==null||"".equals(model.originSender)?Constants.NONE:model.originSender.replace("'","").trim());

        values.put(TransactionsTable.COLUMN_IDENTIFIER, model.identifier);
        values.put(COLUMN_SMS_TYPE, model.smsType);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        return values;
    }

}
