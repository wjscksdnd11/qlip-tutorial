package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CategoryCodeTableData;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class CategoryCodeTable extends AbstractTable implements BaseColumns {


    public static final String TABLE_NAME = " CATEGORY_CODES";
    public static final String ALIAS = " category.";
    public static final String AS_ALIAS = " AS category ";

    public static final String COLUMN_CATEGORY_ID="category_id_pk";
    public static final String COLUMN_CATEGORY_CODE = "category_code";
    public static final String COLUMN_LARGE_CATEGORY = "large_category";
    public static final String COLUMN_MEDIUM_CATEGORY = "medium_category";
    public static final String COLUMN_SMALL_CATEGORY = "small_category";

    public static final String indexing = "CREATE INDEX qlip_category_idx ON " + CategoryCodeTable.TABLE_NAME + " ("+COLUMN_CATEGORY_CODE+")";

    public static final String SQL_CREATE_CATEGORY_ENTRIES =
                                CREATE_TABLE + TABLE_NAME + " (" +
                                        COLUMN_CATEGORY_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                                        COLUMN_CATEGORY_CODE+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP +
                                        COLUMN_LARGE_CATEGORY+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP +
                                        COLUMN_MEDIUM_CATEGORY+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP +
                                        COLUMN_SMALL_CATEGORY+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT +
                                ")";


    public static final String SQL_DELETE_ENTRIES =
            DROP_TABLE_IF_EXISTS + TABLE_NAME;


    public static CategoryCodeTableData populateModel(Cursor c) {
        CategoryCodeTableData model = new CategoryCodeTableData();
		model.category_id = c.getInt(c.getColumnIndex(COLUMN_CATEGORY_ID));
		model.categoryCode = c.getString(c.getColumnIndex(COLUMN_CATEGORY_CODE));
		model.largeCategory = c.getString(c.getColumnIndex(COLUMN_LARGE_CATEGORY));
		model.mediumCategory = c.getString(c.getColumnIndex(COLUMN_MEDIUM_CATEGORY));
        model.smallCategory = c.getString(c.getColumnIndex(COLUMN_SMALL_CATEGORY));
        return model;
	}

    public static ContentValues populateContent(CategoryCodeTableData model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_CODE, model.categoryCode==null||"".equals(model.categoryCode)? Constants.CategoryCode.UNCATEGORY_STR:model.categoryCode);
        values.put(COLUMN_LARGE_CATEGORY,model.largeCategory==null||"".equals(model.largeCategory)?Constants.NONE:model.largeCategory);
        values.put(COLUMN_MEDIUM_CATEGORY,model.mediumCategory==null||"".equals(model.mediumCategory)?Constants.NONE:model.mediumCategory);
        values.put(COLUMN_SMALL_CATEGORY,model.smallCategory==null||"".equals(model.smallCategory)?Constants.NONE:model.smallCategory);
        return values;
	}

}
