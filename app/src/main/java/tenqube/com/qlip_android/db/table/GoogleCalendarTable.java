package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.GoogleCalendarTableData;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class GoogleCalendarTable extends AbstractTable implements BaseColumns {

    public static final String TABLE_NAME = " GOOGLE_CALENDARS";
    public static final String COLUMN_GOOGLE_CALENDAR_ID= "google_id_pk";
    public static final String COLUMN_GOOGLE_CODE= "google_code";
    public static final String COLUMN_GOOGLE_DATE= "google_date";
    public static final String COLUMN_GOOGLE_EMAIL= "google_email";//새로생김
    public static final String COLUMN_GOOGLE_TYPE= "google_type";//새로생김
    public static final String COLUMN_GOOGLE_DW_TYPE= "google_dw_type";//새로생김
    public static final String COLUMN_GOOGLE_CALENDAR_ID_NAME= "google_id_name";//새로생김


    public static final String indexing = "CREATE INDEX qlip_google_calendar_idx ON " + GoogleCalendarTable.TABLE_NAME + " ("+COLUMN_GOOGLE_DW_TYPE+","+COLUMN_GOOGLE_CALENDAR_ID_NAME+","+COLUMN_GOOGLE_EMAIL+")";



    public static final String SQL_CREATE_TABLE =
            CREATE_TABLE + TABLE_NAME + " (" +
                    COLUMN_GOOGLE_CALENDAR_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                    COLUMN_GOOGLE_CODE + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                    COLUMN_GOOGLE_DATE + DATE_TYPE+NOT_NULL+DEFAULT+DEFAULT_DATE + COMMA_SEP +
                    COLUMN_GOOGLE_TYPE + INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT + COMMA_SEP +
                    COLUMN_GOOGLE_DW_TYPE + INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT + COMMA_SEP +
                    COLUMN_GOOGLE_CALENDAR_ID_NAME + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                    UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                    COLUMN_GOOGLE_EMAIL +TEXT_TYPE+NOT_NULL+ DEFAULT + DEFAULT_TEXT +
                    " )";




    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static GoogleCalendarTableData populateModel(Cursor c) {
        GoogleCalendarTableData model = new GoogleCalendarTableData();
        model.GID = c.getInt(c.getColumnIndex(COLUMN_GOOGLE_CALENDAR_ID));
        model.googleCodeStr = c.getString(c.getColumnIndex(COLUMN_GOOGLE_CODE));
        model.googleDateStr = c.getString(c.getColumnIndex(COLUMN_GOOGLE_DATE));
        model.googleEmailStr = c.getString(c.getColumnIndex(COLUMN_GOOGLE_EMAIL));
        model.googleType = c.getInt(c.getColumnIndex(COLUMN_GOOGLE_TYPE));
        model.calendarIdStr=c.getString(c.getColumnIndex(COLUMN_GOOGLE_CALENDAR_ID_NAME));
        model.dwType=c.getInt(c.getColumnIndex(COLUMN_GOOGLE_DW_TYPE));

        return model;
    }

    public static ContentValues populateContent(GoogleCalendarTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_GOOGLE_CODE, model.googleCodeStr==null||"".equals(model.googleCodeStr)? Constants.NONE:model.googleCodeStr);
        values.put(COLUMN_GOOGLE_DATE, model.googleDateStr==null||"".equals(model.googleDateStr)?Constants.NONE:model.googleDateStr);
        values.put(COLUMN_GOOGLE_EMAIL, model.googleEmailStr==null||"".equals(model.googleEmailStr)?Constants.NONE:model.googleEmailStr);
        values.put(COLUMN_GOOGLE_TYPE, model.googleType);
        values.put(COLUMN_GOOGLE_CALENDAR_ID_NAME,  model.calendarIdStr==null||"".equals(model.calendarIdStr)? Constants.NONE:model.calendarIdStr);
        values.put(COLUMN_GOOGLE_DW_TYPE, model.dwType);

        return values;
    }


}
