package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;

import tenqube.com.qlip_android.constans.Constants;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public abstract class AbstractTable {
    public static final String TEXT_TYPE = " TEXT ";
    public static final String REAL_TYPE = " REAL ";
    public static final String INTEGER_TYPE = " INTEGER ";
    public static final String COMMA_SEP = " , ";
    public static final String DATE_TYPE = " DATETIME ";
    public static final String DEFAULT = " DEFAULT ";
    public static final String NOT_NULL = " NOT NULL ";
    public static final String PRIMARY_KEY=" PRIMARY KEY ";
    public static final String UNIQUE=" UNIQUE ";
    public static final String AUTOINCREMENT=" AUTOINCREMENT ";
    public static final String CREATE_TABLE=" CREATE TABLE ";
    public static final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS ";


    public static final String DROP_TABLE_IF_EXISTS=" DROP TABLE IF EXISTS ";
    public static final String DEFAULT_TEXT=" 'none' ";
    public static final String DEFAULT_INT=" 0 ";
    public static final String DEFAULT_DATE=" current_timestamp ";


    public static ContentValues serverFail() {
        ContentValues values = new ContentValues();
        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, Constants.SERVER_FAIL);
        return values;
    }

    public static ContentValues serverSuccess() {
        ContentValues values = new ContentValues();
        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, Constants.SERVER_SUCCESS);
        return values;
    }



}
