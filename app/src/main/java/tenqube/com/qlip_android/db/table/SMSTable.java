package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;


import java.util.Calendar;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.SMSTableData;
import tenqube.com.qlip_android.util.DateUtil;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class SMSTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " SMS";
    public static final String ALIAS = " sms.";
    public static final String AS_ALIAS = " AS sms ";
    public static final String COLUMN_SMS_ID="sms_id_pk";
    public static final String COLUMN_ANDROID_ID = "android_id";
    public static final String COLUMN_SMS_DATE = "sms_date";
    public static final String COLUMN_SMS_MSG = "sms_msg";
    public static final String COLUMN_SMS_TEL = "sms_tel";
    public static final String COLUMN_SEND_SERVER = "send_server";



    //    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_SMS_TABLE =
                        CREATE_TABLE + TABLE_NAME + " (" +
                                COLUMN_SMS_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                                COLUMN_ANDROID_ID + INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT + COMMA_SEP +
                                COLUMN_SMS_DATE + DATE_TYPE+NOT_NULL+DEFAULT+DEFAULT_DATE + COMMA_SEP +
                                COLUMN_SMS_MSG +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT+ COMMA_SEP +
                                COLUMN_SMS_TEL +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT+COMMA_SEP+
                                COLUMN_SEND_SERVER +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+
                                " )";


    public static final String indexing = "CREATE INDEX qlip_sms_test_idx ON " + SMSTable.TABLE_NAME + " ("+SMSTable.COLUMN_SMS_MSG+")";



    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static SMSTableData populateModel(Cursor c) {
        SMSTableData model = new SMSTableData();
        model.smsId = c.getInt(c.getColumnIndex(COLUMN_SMS_ID));
        model.androidId = c.getInt(c.getColumnIndex(COLUMN_ANDROID_ID));
        model.smsDate = c.getString(c.getColumnIndex(COLUMN_SMS_DATE));
        model.smsMsg = c.getString(c.getColumnIndex(COLUMN_SMS_MSG));
        model.sender = c.getString(c.getColumnIndex(COLUMN_SMS_TEL));
        model.sendServer=c.getInt(c.getColumnIndex(COLUMN_SEND_SERVER));

        return model;
    }

    public static ContentValues populateContent(SMSTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANDROID_ID, model.androidId);
        values.put(COLUMN_SMS_DATE, model.smsDate==null||model.smsDate.length()==0? DateUtil.getStringDateAsYYYYMMddHHmmss(Calendar.getInstance()):model.smsDate);
        values.put(COLUMN_SMS_MSG, model.smsMsg==null||model.smsMsg.length()==0? Constants.NONE:model.smsMsg);
        values.put(COLUMN_SMS_TEL, model.sender==null||model.sender.length()==0?Constants.NONE:model.sender);
        values.put(COLUMN_SEND_SERVER, model.sendServer);

        return values;
    }

}
