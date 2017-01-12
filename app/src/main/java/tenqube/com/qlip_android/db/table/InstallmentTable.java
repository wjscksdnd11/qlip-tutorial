package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import tenqube.com.qlip_android.data.InstallmentTableData;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class InstallmentTable extends AbstractTable implements BaseColumns {

    public static final String TABLE_NAME = " INSTALLMENTS";
    public static final String AS_ALIAS = " AS install ";
    public static final String ALIAS = " install.";

    public static final String COLUMN_INSTALLMENT_ID="installment_id_pk";
    public static final String COLUMN_INSTALLMENT_MONEY = "installment_money";
    public static final String COLUMN_INSTALLMENT_DATE = "installment_date";
    public static final String COLUMN_CURRENT_COUNT = "current_count";

    public static final String indexing = "CREATE INDEX qlip_idx_installment ON " + InstallmentTable.TABLE_NAME + " ("+COLUMN_INSTALLMENT_DATE+","+TransactionsTable.COLUMN_IDENTIFIER+")";

    public static final String SQL_CREATE_ENTRIES =
    CREATE_TABLE + TABLE_NAME + " (" +//25개
    COLUMN_INSTALLMENT_ID + INTEGER_TYPE+PRIMARY_KEY+AUTOINCREMENT+COMMA_SEP+
    TransactionsTable.COLUMN_IDENTIFIER + REAL_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
    COLUMN_INSTALLMENT_MONEY + REAL_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
    COLUMN_INSTALLMENT_DATE + DATE_TYPE + NOT_NULL+DEFAULT+DEFAULT_DATE+COMMA_SEP+
    UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
    COLUMN_CURRENT_COUNT + INTEGER_TYPE + NOT_NULL+DEFAULT+"1"+
    ")";


    public static final String SQL_DELETE_ENTRIES =
            DROP_TABLE_IF_EXISTS + TABLE_NAME;



    public static InstallmentTableData populateModel(Cursor c) {
        InstallmentTableData model = new InstallmentTableData();
        model.installmentId = c.getInt(c.getColumnIndex(COLUMN_INSTALLMENT_ID));
        model.identifier=c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
        model.installmentMoney = c.getDouble(c.getColumnIndex(COLUMN_INSTALLMENT_MONEY));
        model.installmentDate = c.getString(c.getColumnIndex(COLUMN_INSTALLMENT_DATE));
        model.currentCount=c.getInt(c.getColumnIndex(COLUMN_CURRENT_COUNT));

        return model;
    }

    public static ContentValues populateContent(Context context, InstallmentTableData model) {

        ContentValues values = new ContentValues();
        values.put(TransactionsTable.COLUMN_IDENTIFIER,model.identifier);
        values.put(COLUMN_INSTALLMENT_MONEY, model.installmentMoney);
        values.put(COLUMN_INSTALLMENT_DATE, model.installmentDate==null||"".equals(model.installmentDate)?DEFAULT_DATE:model.installmentDate);
        values.put(COLUMN_CURRENT_COUNT, model.currentCount);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        return values;
    }

    public static final String INSERT="INSERT INTO" + InstallmentTable.TABLE_NAME +
            "(" +
            TransactionsTable.COLUMN_IDENTIFIER + "," +
            InstallmentTable.COLUMN_INSTALLMENT_MONEY + ","+
            InstallmentTable.COLUMN_INSTALLMENT_DATE + ","+
            InstallmentTable.COLUMN_CURRENT_COUNT+
            ") VALUES ";

}
