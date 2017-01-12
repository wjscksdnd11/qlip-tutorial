package tenqube.com.qlip_android.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.Calendar;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.util.DateUtil;
import tenqube.com.qlip_android.util.KeywordFilterUtil;
import tenqube.com.qlip_android.util.PrefUtils;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;
import static tenqube.com.qlip_android.util.PrefUtils.USER_EMAIL_PK;


/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class TransactionsTable extends AbstractTable implements BaseColumns {

    public static final String TABLE_NAME = " TRANSACTIONS";
    public static final String ALIAS = " tran.";
    public static final String AS_ALIAS = " AS tran ";

    public static final String COLUMN_IDENTIFIER = "identifier";
    public static final String COLUMN_SPENT_DATE = "spent_date";
    public static final String COLUMN_SPENT_MONEY = "spent_money";
    public static final String COLUMN_KEYWORD = "keyword";
    public static final String COLUMN_REPEAT_TYPE = "repeat_type";
    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_INSTALLMENT_COUNT = "installment_count";
    public static final String COLUMN_LAT="lat";
    public static final String COLUMN_LNG="lng";
    public static final String COLUMN_TRAN_TYPE = "tran_type";
    public static final String COLUMN_COMPANY_ADDRESS = "company_address";
    public static final String COLUMN_CATEGORY_CODE="category_code";
    public static final String COLUMN_FRANCHISE="franchise";
    public static final String COLUMN_SEVER_SUCCESS = "server_success";

    public static final String COLUMN_MEMO = "memo";
    public static final String COLUMN_APPLY_ALL = "apply_all";
    public static final String COLUMN_USER_UPDATE = "user_update";
    public static final String COLUMN_DW_TYPE = "dw_type";
    public static final String COLUMN_IS_OFFSET = "is_offset";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_IS_CUSTOM = "is_custom";


    public static final String indexing = "CREATE INDEX qlip_idx ON " + TransactionsTable.TABLE_NAME + " ("+COLUMN_SPENT_DATE+","+COLUMN_IDENTIFIER+")";


    //_id,identifier,amount,company,location,memo,status,success,date,except_card,year,month,day,long,lat,tag,repeat,repeat_date,repeat_kind,franchise,category,category_med,category_sma,time,usd,installment,installment_count,start_time
    //status 삭제:-1 제외 :0: 원래 1
    public static final String SQL_CREATE_ENTRIES =
                            CREATE_TABLE + TABLE_NAME + " (" +//25개
                                    COLUMN_IDENTIFIER + REAL_TYPE + NOT_NULL+UNIQUE+COMMA_SEP+
                                    COLUMN_SPENT_DATE + DATE_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_SPENT_MONEY + REAL_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_KEYWORD + TEXT_TYPE + NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_REPEAT_TYPE + INTEGER_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_CURRENCY + TEXT_TYPE + NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_INSTALLMENT_COUNT +  INTEGER_TYPE + NOT_NULL+DEFAULT+"1"+COMMA_SEP+
                                    COLUMN_LAT + REAL_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_LNG + REAL_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_TRAN_TYPE + INTEGER_TYPE + NOT_NULL+DEFAULT+ Constants.TranType.NORMAL+COMMA_SEP+
                                    COLUMN_COMPANY_ADDRESS + TEXT_TYPE + NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_CATEGORY_CODE + TEXT_TYPE + NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_FRANCHISE + TEXT_TYPE + NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_SEVER_SUCCESS + INTEGER_TYPE + NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    UserTable.COLUMN_USER_ID+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    CardTable.COLUMN_CARD_ID + INTEGER_TYPE + NOT_NULL+COMMA_SEP+
                                    UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID + INTEGER_TYPE + NOT_NULL+COMMA_SEP+
                                    COLUMN_MEMO+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_APPLY_ALL+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_USER_UPDATE+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_IS_OFFSET+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_SENDER+TEXT_TYPE+NOT_NULL+DEFAULT+DEFAULT_TEXT+COMMA_SEP+
                                    COLUMN_IS_CUSTOM+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+COMMA_SEP+
                                    COLUMN_DW_TYPE+INTEGER_TYPE+NOT_NULL+DEFAULT+DEFAULT_INT+
                                    ")";


    public static final String SQL_DELETE_ENTRIES =
            DROP_TABLE_IF_EXISTS + TABLE_NAME;



    public static UserTransactionData populateModel(Cursor c) {

        UserTransactionData model = new UserTransactionData();
        model.identifier = c.getLong(c.getColumnIndex(TransactionsTable.COLUMN_IDENTIFIER));
        model.spentDate = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE));
        model.spentDate = model.spentDate==null? DateUtil.getStringDateAsYYYYMMddHHmmss(Calendar.getInstance()):c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_DATE));
        model.spentMoney = (long)c.getDouble(c.getColumnIndex(TransactionsTable.COLUMN_SPENT_MONEY));
        model.keyword = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_KEYWORD));
        model.repeatType = c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_REPEAT_TYPE));
        model.currency = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_CURRENCY));
        model.installmentCount=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_INSTALLMENT_COUNT));
        model.spentLatitude = c.getDouble(c.getColumnIndex(TransactionsTable.COLUMN_LAT));
        model.spentLongitude = c.getDouble(c.getColumnIndex(TransactionsTable.COLUMN_LNG));
        model.tranType = c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_TRAN_TYPE));
        model.companyAddress = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_COMPANY_ADDRESS));
        model.categoryCode = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_CATEGORY_CODE));
        model.fran = c.getString(c.getColumnIndex(TransactionsTable.COLUMN_FRANCHISE));
        model.serverSuccess = c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_SEVER_SUCCESS));
        model.cardId = c.getInt(c.getColumnIndex(CardTable.COLUMN_CARD_ID));
        model.categoryConfigId = c.getInt(c.getColumnIndex(UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID));
        model.userId=c.getInt(c.getColumnIndex(UserTable.COLUMN_USER_ID));
        model.memo=c.getString(c.getColumnIndex(TransactionsTable.COLUMN_MEMO));
        model.isUpdateAll=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_APPLY_ALL));
        model.isUserUpdate=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_USER_UPDATE));
        model.dwType=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_DW_TYPE));
        model.isOffset=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_IS_OFFSET));
        model.sender=c.getString(c.getColumnIndex(TransactionsTable.COLUMN_SENDER));
        model.isCustom=c.getInt(c.getColumnIndex(TransactionsTable.COLUMN_IS_CUSTOM));

        return model;
    }


    //sms파싱후 인서트
    public static ContentValues populateContent(Context context, UserTransactionData model) {

        ContentValues values = new ContentValues();
        values.put(TransactionsTable.COLUMN_IDENTIFIER, model.identifier);
        values.put(TransactionsTable.COLUMN_SPENT_DATE, model.spentDate==null||"".equals(model.spentDate)? DateUtil.getStringDateAsYYYYMMddHHmmss(Calendar.getInstance()):model.spentDate);
        values.put(TransactionsTable.COLUMN_SPENT_MONEY, (long)model.spentMoney);
        values.put(TransactionsTable.COLUMN_KEYWORD, model.keyword==null||model.keyword.length()==0?Constants.NONE: KeywordFilterUtil.replaceKeyword(model.keyword));
        values.put(TransactionsTable.COLUMN_REPEAT_TYPE, model.repeatType);
        values.put(TransactionsTable.COLUMN_CURRENCY, model.currency==null||model.currency.length()==0?Constants.NONE:model.currency);
        values.put(TransactionsTable.COLUMN_INSTALLMENT_COUNT,model.installmentCount==0?Constants.INSTALLMENT_DEFAULT:model.installmentCount);
        values.put(TransactionsTable.COLUMN_LAT, model.spentLatitude);
        values.put(TransactionsTable.COLUMN_LNG, model.spentLongitude);
        values.put(TransactionsTable.COLUMN_TRAN_TYPE, model.tranType==0?1:model.tranType);
        values.put(TransactionsTable.COLUMN_COMPANY_ADDRESS, model.companyAddress==null||model.companyAddress.length()==0?Constants.NONE:model.companyAddress);
        values.put(TransactionsTable.COLUMN_CATEGORY_CODE, model.categoryCode==null||model.categoryCode.length()==0?Constants.CategoryCode.UNCATEGORY_STR:model.categoryCode);
        values.put(TransactionsTable.COLUMN_FRANCHISE, model.fran==null||model.fran.length()==0? Constants.NONE:model.fran);
        values.put(TransactionsTable.COLUMN_SEVER_SUCCESS, model.serverSuccess);
        values.put(CardTable.COLUMN_CARD_ID, model.cardId);
        values.put(UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID, model.categoryConfigId);
        values.put(TransactionsTable.COLUMN_MEMO,model.memo==null||model.memo.length()==0?Constants.NONE:model.memo.replace("'","").trim());
        values.put(TransactionsTable.COLUMN_APPLY_ALL,model.isUpdateAll);
        values.put(TransactionsTable.COLUMN_USER_UPDATE,model.isUserUpdate);
        values.put(TransactionsTable.COLUMN_DW_TYPE,model.dwType);
        values.put(UserTable.COLUMN_USER_ID, PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0));
        values.put(TransactionsTable.COLUMN_IS_OFFSET,model.isOffset);
        values.put(TransactionsTable.COLUMN_IS_CUSTOM,model.isCustom);

        values.put(TransactionsTable.COLUMN_SENDER,model.sender==null||model.sender.length()==0?Constants.NONE:model.sender.replace("'","").trim());

        LOGI("TRANSACTION",values.toString());
        return values;
    }


    public static final String INSERT="INSERT INTO" + TransactionsTable.TABLE_NAME +
            "(" +
            TransactionsTable.COLUMN_IDENTIFIER + "," +
            TransactionsTable.COLUMN_SPENT_DATE + ","+
            TransactionsTable.COLUMN_SPENT_MONEY + ","+
            TransactionsTable.COLUMN_KEYWORD + ","+
            TransactionsTable.COLUMN_REPEAT_TYPE + ","+
            TransactionsTable.COLUMN_CURRENCY + ","+
            TransactionsTable.COLUMN_INSTALLMENT_COUNT + ","+
            TransactionsTable.COLUMN_TRAN_TYPE + ","+
            TransactionsTable.COLUMN_COMPANY_ADDRESS + ","+
            TransactionsTable.COLUMN_CATEGORY_CODE + ","+
            TransactionsTable.COLUMN_FRANCHISE + ","+
            CardTable.COLUMN_CARD_ID + ","+
            UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+ ","+
            TransactionsTable.COLUMN_MEMO + ","+
            TransactionsTable.COLUMN_APPLY_ALL + ","+
            TransactionsTable.COLUMN_USER_UPDATE + ","+
            TransactionsTable.COLUMN_DW_TYPE + ","+
            UserTable.COLUMN_USER_ID + ","+
            TransactionsTable.COLUMN_IS_OFFSET +","+
            TransactionsTable.COLUMN_IS_CUSTOM +","+
            TransactionsTable.COLUMN_SENDER +
            ") VALUES ";


    public static String getInsertValue(UserTransactionData model, Context context){

          return "("+
                    model.identifier+","+
                    "'"+(model.spentDate==null||"".equals(model.spentDate)? DateUtil.getStringDateAsYYYYMMddHHmmss(Calendar.getInstance()):model.spentDate.replace("'","").replace("`",""))+"',"+
                    (long)model.spentMoney+","+
                    "'"+ (model.keyword==null||model.keyword.length()==0?Constants.NONE: KeywordFilterUtil.replaceKeyword(model.keyword))+"',"+
                    model.repeatType+","+
                    "'"+(model.currency==null||model.currency.length()==0?Constants.NONE:model.currency.replace("'","").replace("`",""))+"',"+
                    (model.installmentCount==0?Constants.INSTALLMENT_DEFAULT:model.installmentCount)+","+
                    (model.tranType==0?1:model.tranType)+","+
                    "'"+(model.companyAddress==null||model.companyAddress.length()==0?Constants.NONE:model.companyAddress.replace("'","").replace("`",""))+"',"+
                    (model.categoryCode==null||model.categoryCode.length()==0?Constants.CategoryCode.UNCATEGORY_STR:model.categoryCode)+","+
                    "'"+ (model.fran==null||model.fran.length()==0? Constants.NONE:model.fran.replace("'","").replace("`",""))+"',"+
                    model.cardId+","+
                    model.categoryConfigId+","+
                    "'"+(model.memo==null||model.memo.length()==0?Constants.NONE:model.memo.replace("'","").replace("`","").trim())+"',"+
                    model.isUpdateAll+","+
                    model.isUserUpdate+","+
                    model.dwType+","+
                    "'"+ PrefUtils.getInstance(context).loadIntValue(USER_EMAIL_PK, 0)+"',"+
                    model.isOffset+","+
                    model.isCustom+","+

                  "'"+(model.sender==null||model.sender.length()==0?Constants.NONE:model.sender.replace("'","").replace("`","").trim())+"'"+
                  ")";
    }


}
