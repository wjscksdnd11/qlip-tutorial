package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class TagTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " HASH_TAGS";
    public static final String ALIAS = " hashtag.";
    public static final String AS_ALIAS = " AS hashtag ";

    public static final String COLUMN_HASH_TAG_ID="hashtag_Id_pk";
    public static final String COLUMN_HASH_TAG_NAME = "hashtagName";
    public static final String COLUMN_CREATE_DATE="create_date";

    public static final String COLUMN_EXCEPT_TYPE = "exceptType";
    public static final String COLUMN_MAIN_TYPE = "main_type";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String indexing = "CREATE INDEX qlip_tag_idx ON " + TagTable.TABLE_NAME + " ("+COLUMN_HASH_TAG_NAME+")";
    public static final String indexing2 = "CREATE INDEX qlip_tag_idx2 ON " + TagTable.TABLE_NAME + " ("+COLUMN_HASH_TAG_ID+")";

    //    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_LABEL_TABLE =
                                CREATE_TABLE + TABLE_NAME + " (" +
                                        COLUMN_HASH_TAG_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                                        COLUMN_HASH_TAG_NAME + TEXT_TYPE+NOT_NULL+UNIQUE+DEFAULT+DEFAULT_TEXT + COMMA_SEP +
                                        COLUMN_CREATE_DATE +DATE_TYPE+NOT_NULL+ DEFAULT +DEFAULT_DATE +COMMA_SEP+
                                        COLUMN_EXCEPT_TYPE +INTEGER_TYPE+NOT_NULL+ DEFAULT + Constants.EXCEPT_NO +COMMA_SEP+
                                        COLUMN_PRIORITY+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT +COMMA_SEP+
                                        COLUMN_MAIN_TYPE+INTEGER_TYPE+NOT_NULL+DEFAULT+"1" +COMMA_SEP+
                                        UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                        TransactionsTable.COLUMN_SEVER_SUCCESS + INTEGER_TYPE +NOT_NULL+DEFAULT+ DEFAULT_INT +
                                " )";




    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;

    public static TagTableData populateModel(Cursor c) {
        TagTableData model = new TagTableData();
        model.tagId = c.getLong(c.getColumnIndex(COLUMN_HASH_TAG_ID));
        model.tagName = c.getString(c.getColumnIndex(COLUMN_HASH_TAG_NAME)).trim();
        model.serverSuccess = c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_SEVER_SUCCESS));
        model.exceptType=c.getInt(c.getColumnIndex(COLUMN_EXCEPT_TYPE));
        model.mainType=c.getInt(c.getColumnIndex(COLUMN_MAIN_TYPE));
        model.priority=c.getInt(c.getColumnIndex(COLUMN_PRIORITY));
        return model;
    }

    public static ContentValues populateContent(Context context, TagTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_HASH_TAG_NAME, model.tagName==null||"".equals(model.tagName.replace("'","").trim())?"none":model.tagName.replace("'","").trim());
        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, model.serverSuccess);
        values.put(COLUMN_EXCEPT_TYPE, model.exceptType);
        values.put(COLUMN_MAIN_TYPE, model.mainType);
        values.put(COLUMN_PRIORITY, model.priority);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        return values;
    }
    public static ContentValues populateContentAsSync(Context context, TagTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_HASH_TAG_NAME, model.tagName==null||"".equals(model.tagName.trim())?"none":model.tagName.replace("'","").trim());
        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, model.serverSuccess);
        values.put(COLUMN_EXCEPT_TYPE, model.exceptType);
        values.put(COLUMN_MAIN_TYPE, model.mainType);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        return values;
    }

}
