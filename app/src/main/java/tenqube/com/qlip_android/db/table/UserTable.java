package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.UserTableData;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class UserTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " USERS";
    public static final String ALIAS = " user.";
    public static final String AS_ALIAS = " AS user ";

    public static final String COLUMN_USER_ID = "user_id_pk";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_SEX = "user_sex";
    public static final String COLUMN_USER_BIRTH = "user_birth";


    public static final String COLUMN_INSTALLMENT = "installment";
    public static final String COLUMN_GOOGLE_CALENDAR = "google_calendar";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_ALARM_BUDGET = "alarm_budget";
    public static final String COLUMN_ALARM_ONDAY = "alarm_oneday";
    public static final String COLUMN_ALARM_SPENDING = "alarm_spending";
    public static final String COLUMN_WEB_COMIC = "web_comic";


    public static final String COLUMN_CREATE_DATE="createDate";

    public static final String SQL_CREATE_USER_TABLE =
                    CREATE_TABLE + TABLE_NAME + " (" +
                            COLUMN_USER_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                            COLUMN_USER_EMAIL + TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                            COLUMN_USER_SEX + INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT + COMMA_SEP +
                            COLUMN_USER_BIRTH +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+ COMMA_SEP +

                            COLUMN_INSTALLMENT +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+ COMMA_SEP +
                            COLUMN_GOOGLE_CALENDAR +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT+ COMMA_SEP +
                            COLUMN_START_DATE +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1"+ COMMA_SEP +
                            COLUMN_ALARM_BUDGET +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1"+ COMMA_SEP +
                            COLUMN_ALARM_ONDAY +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1"+ COMMA_SEP +
                            COLUMN_ALARM_SPENDING +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1"+ COMMA_SEP +
                            COLUMN_WEB_COMIC +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1"+ COMMA_SEP +
                            COLUMN_CREATE_DATE +DATE_TYPE+NOT_NULL+ DEFAULT +DEFAULT_DATE +
                            " )";


    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static final String indexing = "CREATE INDEX qlip_user_idx ON " + UserTable.TABLE_NAME + " ("+UserTable.COLUMN_USER_ID+")";


    public static UserTableData populateModel(Cursor c) {
        UserTableData model = new UserTableData();
        model.userId = c.getInt(c.getColumnIndex(COLUMN_USER_ID));
        model.userEmail = c.getString(c.getColumnIndex(COLUMN_USER_EMAIL));
        model.userSex = c.getInt(c.getColumnIndex(COLUMN_USER_SEX));
        model.userBirth = c.getInt(c.getColumnIndex(COLUMN_USER_BIRTH));

        model.installment = c.getInt(c.getColumnIndex(COLUMN_INSTALLMENT));
        model.googleCalendar = c.getInt(c.getColumnIndex(COLUMN_GOOGLE_CALENDAR));
        model.startDate = c.getInt(c.getColumnIndex(COLUMN_START_DATE));
        model.alarmBudget = c.getInt(c.getColumnIndex(COLUMN_ALARM_BUDGET));
        model.alarmOneDay = c.getInt(c.getColumnIndex(COLUMN_ALARM_ONDAY));
        model.alarmSpending = c.getInt(c.getColumnIndex(COLUMN_ALARM_SPENDING));
        model.webComic = c.getInt(c.getColumnIndex(COLUMN_WEB_COMIC));

        return model;

    }

    public static ContentValues populateContent(UserTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, model.userEmail==null||model.userEmail.length()==0? Constants.NONE:model.userEmail.replace("'","").trim());
        values.put(COLUMN_USER_SEX, model.userSex);
        values.put(COLUMN_USER_BIRTH, model.userBirth);

        values.put(COLUMN_INSTALLMENT, model.installment);
        values.put(COLUMN_GOOGLE_CALENDAR, model.googleCalendar);
        values.put(COLUMN_START_DATE, model.startDate==0?1:model.startDate);
        values.put(COLUMN_ALARM_BUDGET, model.alarmBudget);
        values.put(COLUMN_ALARM_ONDAY, model.alarmOneDay);
        values.put(COLUMN_ALARM_SPENDING, model.alarmSpending);
        values.put(COLUMN_WEB_COMIC, model.webComic);

        return values;

    }
}

