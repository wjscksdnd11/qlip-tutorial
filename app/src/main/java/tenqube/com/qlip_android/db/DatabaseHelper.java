package tenqube.com.qlip_android.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.CategoryCodeTableData;
import tenqube.com.qlip_android.data.TagMapTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.table.BankMapTable;
import tenqube.com.qlip_android.db.table.BudgetTable;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.FullSmsTable;
import tenqube.com.qlip_android.db.table.GoogleCalendarTable;
import tenqube.com.qlip_android.db.table.InstallmentTable;
import tenqube.com.qlip_android.db.table.OilNearByCompanyTable;
import tenqube.com.qlip_android.db.table.OilTable;
import tenqube.com.qlip_android.db.table.RegExpressionTable;
import tenqube.com.qlip_android.db.table.SMSTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;
import tenqube.com.qlip_android.db.table.UserTable;

import static tenqube.com.qlip_android.constans.Constants.CATEGORY_LIST;
import static tenqube.com.qlip_android.constans.Constants.DEPOSIT_CATEGORY_LIST;
import static tenqube.com.qlip_android.db.query.QueryHelper.FROM;
import static tenqube.com.qlip_android.db.query.QueryHelper.SELECT;
import static tenqube.com.qlip_android.db.query.QueryHelper.WHERE;
import static tenqube.com.qlip_android.util.LogUtil.LOGI;
import static tenqube.com.qlip_android.util.LogUtil.makeLogTag;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 4;
    protected static final String TAG = makeLogTag(DatabaseHelper.class);


    private Context mContext;

    public static DatabaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }

        return mInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(OilTable.SQL_CREATE_ENTRIES);
            db.execSQL(BudgetTable.SQL_CREATE_BUDGET_TABLE);
            db.execSQL(CardTable.SQL_CREATE_CARD_TABLE);
//            db.insert(CardTable.TABLE_NAME,null,CardTable.populateContent(null,getCashCard()));
            db.execSQL(CategoryCodeTable.SQL_CREATE_CATEGORY_ENTRIES);
            db.execSQL(InstallmentTable.SQL_CREATE_ENTRIES);
            db.execSQL(OilNearByCompanyTable.SQL_CREATE_ENTRIES);
            db.execSQL(SMSTable.SQL_CREATE_SMS_TABLE);
            db.execSQL(TransactionsTable.SQL_CREATE_ENTRIES);
            db.execSQL(FullSmsTable.SQL_CREATE_TABLE);
            db.execSQL(UserTable.SQL_CREATE_USER_TABLE);
            db.execSQL(TagTable.SQL_CREATE_LABEL_TABLE);
            db.execSQL(GoogleCalendarTable.SQL_CREATE_TABLE);
            db.execSQL(TagMapTable.SQL_CREATE_TABLE);
            db.execSQL(UserCategoryConfigTable.SQL_CREATE_MANAGE_CATEGORY_ENTRIES);
            db.execSQL(RegExpressionTable.SQL_CREATE_SMS_TABLE);
            db.execSQL(BankMapTable.SQL_CREATE_TABLE);
            db.execSQL(BankMapTable.indexing);
            db.execSQL(TransactionsTable.indexing);
            db.execSQL(RegExpressionTable.indexing);
            db.execSQL(BudgetTable.indexing);
            db.execSQL(CardTable.indexing4);
            db.execSQL(CategoryCodeTable.indexing);
            db.execSQL(FullSmsTable.indexing);
            db.execSQL(GoogleCalendarTable.indexing);
            db.execSQL(TagTable.indexing);
            db.execSQL(InstallmentTable.indexing);
            db.execSQL(UserCategoryConfigTable.indexing);
            db.execSQL(OilNearByCompanyTable.indexing);
            db.execSQL(OilTable.indexing);
            db.execSQL(SMSTable.indexing);
            db.execSQL(UserTable.indexing);


            //test case
            LOGI("KJ","start");

            insertCategory(db);
            insertCard(db);
            insertTag(db);
            insertUserCate(db);
            insertTransaction(db);

        }catch(SQLiteException e){
            LOGI("KJ", e.toString());
        }

    }

//    public CardTableData getCashCard(){
//        CardTableData item = new CardTableData();
//        item.changeCardName=mContext.getString(R.string.card_type_cash);
//        item.cardName=mContext.getString(R.string.card_type_cash);
//        item.totalCardName=mContext.getString(R.string.card_type_cash);
//        item.cardType= CASH.ordinal();
//        item.changeCardType= CASH.ordinal();
//        item.totalCardType= CASH.ordinal();
//        return  item;
//    }

    public  void insertCard(SQLiteDatabase db){
        AssetManager am = mContext.getAssets();
        InputStream inStream;

        try {
            inStream = am.open("card.csv");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line ;

            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                CardTableData card = new CardTableData();
                card.cardName = colums[3];
                card.cardType = Integer.parseInt(colums[4]);
                card.cardSubType = Integer.parseInt(colums[5]);

                card.totalCardName = colums[3];
                card.totalCardType = Integer.parseInt(colums[4]);
                card.totalCardSubType = Integer.parseInt(colums[5]);

                card.changeCardName = colums[3];
                card.changeCardType = Integer.parseInt(colums[4]);
                card.changeCardSubType = Integer.parseInt(colums[5]);


                db.insert(CardTable.TABLE_NAME,null,CardTable.populateContent(mContext, card));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){

        }

    }

    public  void insertCategory(SQLiteDatabase db){
        AssetManager am = mContext.getAssets();
        InputStream inStream;

        try {
            inStream = am.open("category.csv");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line ;

            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                CategoryCodeTableData category = new CategoryCodeTableData();
                category.categoryCode = colums[1];
                category.largeCategory = colums[2];
                category.mediumCategory = colums[3];
                category.smallCategory = colums[4];
                db.insert(CategoryCodeTable.TABLE_NAME,null,CategoryCodeTable.populateContent(category));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){

        }

    }


    public  void insertTag(SQLiteDatabase db){
        AssetManager am = mContext.getAssets();
        InputStream inStream;

        try {
            inStream = am.open("tag.csv");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line ;
            int i = 0;

            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                TagTableData tag = new TagTableData();
                tag.tagName = colums[2];
                if (i == 0){
                    tag.exceptType = 1;
                Log.i("tagData",tag.tagName+" , "+tag.exceptType);
                }
                db.insert(TagTable.TABLE_NAME,null,TagTable.populateContent(mContext, tag));
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){

        }

    }

    public String getCategoryCode(SQLiteDatabase db, long id){
        Cursor c=null;

        try {
            c = db.rawQuery(SELECT + " * " + FROM + UserCategoryConfigTable.TABLE_NAME+WHERE+UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID+"="+id, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    return UserCategoryConfigTable.populateModel(c).categoryCode+"1010";
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        return "101010";
    }
    public  void insertTransaction(SQLiteDatabase db){
        AssetManager am = mContext.getAssets();
        InputStream inStream;

        int i  = 0;

        try {
            inStream = am.open("transactions.csv");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line ;
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                UserTransactionData transaction = new UserTransactionData();

                transaction.identifier = Long.parseLong(colums[3]);
                transaction.cardId = getRandomNumber(11);
                transaction.dwType = Integer.parseInt(colums[19]);

                int id = getRandomNumber(transaction.dwType == Constants.DWType.DEPOSIT.ordinal()?DEPOSIT_CATEGORY_LIST.length:CATEGORY_LIST.length);
                transaction.categoryConfigId = id;
                transaction.categoryCode = getCategoryCode(db, id);
                transaction.spentDate = "2016-12-15 14:31:48";
                transaction.spentMoney = Double.parseDouble(colums[11]);
                transaction.installmentCount = Integer.parseInt(colums[12]);
                transaction.keyword = colums[13];
                transaction.repeatType = Integer.parseInt(colums[15]);
                transaction.currency = colums[16];

                transaction.memo = colums[26];
                LOGI("TRAN",TransactionsTable.populateContent(mContext, transaction).toString());
                db.insert(TransactionsTable.TABLE_NAME,null,TransactionsTable.populateContent(mContext, transaction));

                i++;

                if(i%5 == 0){
                    TagMapTableData map = new TagMapTableData();
                    map.identifier = transaction.identifier;
                    map.tagId = 1;
                    db.insert(TagMapTable.TABLE_NAME,null,TagMapTable.populateContent(mContext, map));

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){

        }

    }



    public  void insertUserCate(SQLiteDatabase db){
        AssetManager am = mContext.getAssets();
        InputStream inStream;

        try {
            inStream = am.open("user_cate_config.csv");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line ;

            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                UserCategoryConfigTableData cateConfig = new UserCategoryConfigTableData();
                cateConfig.categoryCode = colums[2];
                cateConfig.exceptType = Integer.parseInt(colums[4]);

                db.insert(UserCategoryConfigTable.TABLE_NAME,null,UserCategoryConfigTable.populateContent(mContext, cateConfig));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

        db.beginTransaction();
        db.execSQL(OilTable.SQL_DELETE_ENTRIES);
        db.execSQL(BudgetTable.SQL_DELETE_ENTRIES);
        db.execSQL(CardTable.SQL_DELETE_ENTRIES);
        db.execSQL(CategoryCodeTable.SQL_DELETE_ENTRIES);
        db.execSQL(InstallmentTable.SQL_DELETE_ENTRIES);
        db.execSQL(OilNearByCompanyTable.SQL_DELETE_ENTRIES);
        db.execSQL(SMSTable.SQL_DELETE_ENTRIES);
        db.execSQL(TransactionsTable.SQL_DELETE_ENTRIES);
        db.execSQL(UserTable.SQL_DELETE_ENTRIES);
        db.execSQL(TagTable.SQL_DELETE_ENTRIES);
        db.execSQL(GoogleCalendarTable.SQL_DELETE_ENTRIES);
        db.execSQL(TagMapTable.SQL_DELETE_ENTRIES);
        db.execSQL(UserCategoryConfigTable.SQL_DELETE_ENTRIES);
        db.execSQL(RegExpressionTable.SQL_DELETE_ENTRIES);
        db.execSQL(BankMapTable.SQL_DELETE_ENTRIES);

        LOGI("KJ","start");


        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int getRandomNumber(int length) {
        return 1 + (int) (Math.random() * length);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}