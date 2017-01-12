package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;


import java.util.Calendar;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.RegExpressionTableData;
import tenqube.com.qlip_android.util.DateUtil;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class RegExpressionTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " Reg_Expression";
    public static final String ALIAS = " reg.";
    public static final String AS_ALIAS = " AS reg ";
    public static final String COLUMN_REG_ID="reg_id_pk";
    public static final String COLUMN_REG_EXPRESSION = "regular_expression";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_MAPPING_ORDER = "mapping_order";
    public static final String COLUMN_CREATE_DATE = "create_date";
    public static final String COLUMN_REG_TYPE = "reg_type";


    public static final String indexing = "CREATE INDEX qlip_reg_idx ON " + RegExpressionTable.TABLE_NAME + " ("+COLUMN_SENDER+")";


    //    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_SMS_TABLE =
                        CREATE_TABLE + TABLE_NAME + " (" +
                                COLUMN_REG_ID + INTEGER_TYPE+PRIMARY_KEY+COMMA_SEP+
                                COLUMN_REG_EXPRESSION + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                                COLUMN_SENDER + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                                COLUMN_MAPPING_ORDER +TEXT_TYPE+NOT_NULL+ DEFAULT +DEFAULT_TEXT+ COMMA_SEP +
                                COLUMN_REG_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+ COMMA_SEP +
                                COLUMN_CREATE_DATE +DATE_TYPE+NOT_NULL+ DEFAULT +DEFAULT_DATE+
                                " )";




    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static RegExpressionTableData populateModel(Cursor c) {
        RegExpressionTableData model = new RegExpressionTableData();
        model.regId=c.getLong(c.getColumnIndex(COLUMN_REG_ID));
        model.regExpression = c.getString(c.getColumnIndex(COLUMN_REG_EXPRESSION));
        model.sender = c.getString(c.getColumnIndex(COLUMN_SENDER));
        model.mappingOrder = c.getString(c.getColumnIndex(COLUMN_MAPPING_ORDER));
        model.regType = c.getInt(c.getColumnIndex(COLUMN_REG_TYPE));

        model.createDate = c.getString(c.getColumnIndex(COLUMN_CREATE_DATE));
        return model;
    }

    public static ContentValues populateContent(RegExpressionTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_REG_ID, model.regId);
        values.put(COLUMN_REG_EXPRESSION, model.regExpression==null||model.regExpression.length()==0? Constants.NONE:model.regExpression);
        values.put(COLUMN_SENDER, model.sender==null||model.sender.length()==0? Constants.NONE:model.sender);
        values.put(COLUMN_MAPPING_ORDER, model.mappingOrder==null||model.mappingOrder.length()==0?Constants.NONE:model.mappingOrder);
        values.put(COLUMN_REG_TYPE, model.regType);
        values.put(COLUMN_CREATE_DATE,  model.createDate==null||model.createDate.length()==0? DateUtil.getStringDateAsYYYYMMddHHmmss(Calendar.getInstance()):model.createDate);
        return values;
    }

}
