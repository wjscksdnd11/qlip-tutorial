package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class UserCategoryConfigTable extends AbstractTable implements BaseColumns {


    public static final String TABLE_NAME = " User_Category_configs ";
    public static final String ALIAS = " userCategoryConfigs.";
    public static final String AS_ALIAS = " AS userCategoryConfigs ";

    public static final String COLUMN_CATEGORY_CONFIG_ID="category_config_id";
    public static final String COLUMN_CATEGORY_CODE = "category_code";
    public static final String COLUMN_EXCEPT_CATEGORY_TYPE = "except_category_type";
    public static final String COLUMN_MAIN_TYPE = "main_type";
    public static final String COLUMN_PRIORITY = "priority";

    public static final String indexing = "CREATE INDEX user_category_configs_idx ON " + UserCategoryConfigTable.TABLE_NAME + " ("+COLUMN_CATEGORY_CONFIG_ID+","+COLUMN_CATEGORY_CODE+")";


    public static final String SQL_CREATE_MANAGE_CATEGORY_ENTRIES =
                                CREATE_TABLE + TABLE_NAME + " (" +
                                COLUMN_CATEGORY_CONFIG_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                                COLUMN_CATEGORY_CODE+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP +
                                COLUMN_EXCEPT_CATEGORY_TYPE+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP+
                                COLUMN_MAIN_TYPE+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP+
                                TransactionsTable.COLUMN_SEVER_SUCCESS + INTEGER_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                COLUMN_PRIORITY+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +
                                ")";


    public static final String SQL_DELETE_ENTRIES =
            DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static UserCategoryConfigTableData populateModel(Cursor c) {
        UserCategoryConfigTableData model = new UserCategoryConfigTableData();
		model.categoryConfigId = c.getInt(c.getColumnIndex(COLUMN_CATEGORY_CONFIG_ID));
		model.categoryCode = c.getString(c.getColumnIndex(COLUMN_CATEGORY_CODE));
        model.exceptType=c.getInt(c.getColumnIndex(COLUMN_EXCEPT_CATEGORY_TYPE));
        model.priority=c.getInt(c.getColumnIndex(COLUMN_PRIORITY));
        model.mainType=c.getInt(c.getColumnIndex(COLUMN_MAIN_TYPE));
        return model;
	}

    public static ContentValues populateContent(Context context, UserCategoryConfigTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_CODE, model.categoryCode==null||"".equals(model.categoryCode)?"10":model.categoryCode);
        values.put(COLUMN_EXCEPT_CATEGORY_TYPE, model.exceptType);
        values.put(COLUMN_PRIORITY, model.priority);
        values.put(COLUMN_MAIN_TYPE, model.mainType);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        return values;
	}
    public static ContentValues populateContentAsSync(Context context, UserCategoryConfigTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_CODE, model.categoryCode==null||"".equals(model.categoryCode)?"10":model.categoryCode);
        values.put(COLUMN_EXCEPT_CATEGORY_TYPE, model.exceptType);
        values.put(COLUMN_MAIN_TYPE, model.mainType);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        return values;
    }
}
