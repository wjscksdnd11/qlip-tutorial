package tenqube.com.qlip_android.db.table;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.BudgetTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class BudgetTable extends AbstractTable implements BaseColumns {
    public static final String TABLE_NAME = " BUDGETS";
    public static final String ALIAS = " budget.";
    public static final String AS_ALIAS = " AS budget ";


    public static final String COLUMN_BUDGET_ID="budget_id_pk";
    public static final String COLUMN_BUDGET = "budget";
    public static final String COLUMN_CATEGORY_CODE = "category_code";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
//    public static final String DEFAULT = "INTEGER not null DEFAULT 0";

    public static final String indexing = "CREATE INDEX qlip_budget_idx ON " + BudgetTable.TABLE_NAME + " ("+COLUMN_YEAR+","+COLUMN_CATEGORY_CODE+","+COLUMN_MONTH+")";

    public static final String SQL_CREATE_BUDGET_TABLE =
                                CREATE_TABLE + TABLE_NAME + " (" +
                                        COLUMN_BUDGET_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
                                        COLUMN_BUDGET + REAL_TYPE+NOT_NULL+DEFAULT+"1000000" + COMMA_SEP +
                                        COLUMN_CATEGORY_CODE + TEXT_TYPE+NOT_NULL+DEFAULT+"'90'" + COMMA_SEP +
                                        COLUMN_YEAR +INTEGER_TYPE+NOT_NULL+ DEFAULT +"1900"+ COMMA_SEP +
                                        COLUMN_MONTH+INTEGER_TYPE+NOT_NULL + DEFAULT +"1"+ COMMA_SEP +
                                        UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+

                                        TransactionsTable.COLUMN_SEVER_SUCCESS + INTEGER_TYPE +NOT_NULL+DEFAULT+ DEFAULT_INT +
                                " )";





    public static final String SQL_DELETE_ENTRIES =
                           DROP_TABLE_IF_EXISTS + TABLE_NAME;

    public static BudgetTableData populateModel(Cursor c) {
        BudgetTableData model = new BudgetTableData();
        model.budgetId = c.getInt(c.getColumnIndex(COLUMN_BUDGET_ID));
        model.budget = c.getDouble(c.getColumnIndex(COLUMN_BUDGET));
        model.categoryCode = c.getString(c.getColumnIndex(COLUMN_CATEGORY_CODE));
        model.budgetYear = c.getInt(c.getColumnIndex(COLUMN_YEAR));
        model.budgetMonth = c.getInt(c.getColumnIndex(COLUMN_MONTH));
        model.serverSuccess = c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_SEVER_SUCCESS));

        return model;
    }

    public static ContentValues populateContent(Context context, BudgetTableData model) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_BUDGET, model.budget);
        values.put(COLUMN_CATEGORY_CODE, model.categoryCode==null||"".equals(model.categoryCode)? Constants.CategoryCode.MONTHLY+"":model.categoryCode.trim());
        values.put(COLUMN_YEAR, model.budgetYear);
        values.put(COLUMN_MONTH, model.budgetMonth);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));

        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, model.serverSuccess);

        return values;
    }


}
