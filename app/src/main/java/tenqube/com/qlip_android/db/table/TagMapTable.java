package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.data.TagMapTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class TagMapTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " TAG_MAPS";
    public static final String ALIAS = " mTagMap.";
    public static final String AS_ALIAS = " AS mTagMap ";

//    public static final String DEFAULT = "INTEGER not null DEFAULT 0";
    public static final String SQL_CREATE_TABLE =
                                CREATE_TABLE + TABLE_NAME + " (" +
                                        TransactionsTable.COLUMN_IDENTIFIER + REAL_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT + COMMA_SEP +
                                        TransactionsTable.COLUMN_SEVER_SUCCESS + INTEGER_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                        UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                        TagTable.COLUMN_HASH_TAG_ID +INTEGER_TYPE+NOT_NULL+ DEFAULT +DEFAULT_INT +COMMA_SEP+
                                        "PRIMARY KEY("+  TransactionsTable.COLUMN_IDENTIFIER+","+ TagTable.COLUMN_HASH_TAG_ID+")"+
                                " )";


    public static final String indexing = "CREATE INDEX tag_map_idx ON " + TagMapTable.TABLE_NAME + " ("+ TagTable.COLUMN_HASH_TAG_ID+","+TransactionsTable.COLUMN_IDENTIFIER+")";

    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;

    public static TagMapTableData populateModel(Cursor c) {
        TagMapTableData model = new TagMapTableData();
        model.identifier =c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
        model.tagId=c.getInt(c.getColumnIndex(TagTable.COLUMN_HASH_TAG_ID));
        return model;
    }

    public static ContentValues populateContent(Context context, TagMapTableData model) {
        ContentValues values = new ContentValues();
        values.put(TransactionsTable.COLUMN_IDENTIFIER,model.identifier);
        values.put(TagTable.COLUMN_HASH_TAG_ID, model.tagId);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        return values;
    }


}
