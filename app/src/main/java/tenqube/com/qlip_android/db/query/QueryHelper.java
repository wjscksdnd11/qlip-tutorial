package tenqube.com.qlip_android.db.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import tenqube.com.qlip_android.data.MediumCategoryData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.DatabaseHelper;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;

import static tenqube.com.qlip_android.util.LogUtil.LOGE;
import static tenqube.com.qlip_android.util.LogUtil.LOGI;
import static tenqube.com.qlip_android.util.LogUtil.makeLogTag;


public abstract class QueryHelper {
    public static final String TAG= makeLogTag(QueryHelper.class);
    public static final String SELECT = " SELECT ";
    public static final String DELETE = " DELETE ";
    public static final String UPDATE = " UPDATE ";
    public static final String INSERT_INTO = " INSERT INTO ";
    public static final String VALUES = " VALUES ";

    public static final String LIMIT = " LIMIT ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String HAVING = " HAVING ";
    public static final String SET = " SET ";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";
    public static final String AND = " AND ";
    public static final String IN = " IN ";
    public static final String NOT_IN = " NOT IN ";
    public static final String UNION_ALL=" UNION ALL ";

    public static final String OR = " OR ";
    public static final String DESC = " DESC ";
    public static final String ASC = " ASC ";
    public static final String YYYY_MM_DD_H_M="'%Y-%m-%d %H:%M'";
    public static final String YYYY_MM_DD="'%Y-%m-%d'";
    public static final String MM_DD="'%m-%d'";
    public static final String H_M_S="'%H:%M:%S'";



    public Context mContext;
    public DatabaseHelper helper;
    public SQLiteDatabase db;
    public SQLiteDatabase wdb;
    public SQLiteDatabase dbExcel;
    public SQLiteDatabase wdbExcel;

    protected static final String[] TABLES={
            CardTable.TABLE_NAME, TagTable.TABLE_NAME, TransactionsTable.TABLE_NAME, TagMapTable.TABLE_NAME
    };

    public QueryHelper(Context context){
        this.mContext=context;
        helper=DatabaseHelper.getInstance(context);
        db = helper.getReadableDatabase();
        wdb = helper.getWritableDatabase();
    }


    /**
     * Called to insert an item.
     *
     * @param tableName
     *           삽입 되는 테이블명
     * @param values
     *            삽입 되는 값
     * @return return 삽입된 행수
     */
    public long insert(String tableName, ContentValues values) {
       try{
            return  wdb.insert(tableName, null, values);
        }catch (SQLException e){
            return  0;
        }
    }


    /**
     * Called to update an item.
     *
     * @param tableName
     *           업데이트 되는 테이블명
     * @param values
     *            업데이트 되는 값
     * @param selection
     *          where 조건 파라미터
     * @param  selectionArgs
     *          where 조건 값
     * @return return 업데이트된 행수
     */
    public long update(String tableName, ContentValues values, String selection, String[] selectionArgs) {

        try{
            return wdb.update(tableName, values, selection, selectionArgs);
        }catch (SQLException e){
            return  0;
        }
    }
//
//    /**
//     * Called to delete an item.
//     *
//     * @param tableName
//     *           삭제 되는 테이블명
//
//     * @param selection
//     *          where 조건 파라미터
//     * @param  selectionArgs
//     *          where 조건 값
//     * @return return 삭제된 행수
//     */
    public long delete(String tableName, String selection, String[] selectionArgs) {
        try {
            return wdb.delete(tableName, selection, selectionArgs);
        }catch (SQLException e){
            return  0;
        }
    }
/*
    public void delete(String tableName, String selection, String ids){
        db.execSQL("DELETE FROM " + table + " WHERE " + id + " IN " + "("+ids+")");

}*/


    public Cursor runQuery(final String query) {
        Cursor rows;
        rows = db.rawQuery(query, null);


        if (rows == null) {
            return null;
        }

        try {
            final int rowCount = rows.getCount();   //커서가 참조 할 수 있는 해당 테이블의 행(row)의 갯수를 얻어 옵니다.
            LOGI(TAG, "Query : " + query + " rowCount: "+rowCount );

            if (rowCount == 0 || !rows.moveToLast()) {
                rows.close();
                return null;
            }
        } catch (RuntimeException ex) {
            rows.close();
            return null;
        }


        return  rows;
    }


    public void deleteAll(){
        for (String table: TABLES){
            delete(table,null,null);
        }
    }

    public boolean isExistTag(long tagId, long identifier){


        Cursor c=null;
        String query =
                SELECT + "*"+
                        FROM+ TagMapTable.TABLE_NAME+
                        WHERE+TagTable.COLUMN_HASH_TAG_ID+"="+tagId+
                        AND+TransactionsTable.COLUMN_IDENTIFIER+"="+identifier;

        try {
            c = runQuery(query);
            if (c != null) {

                if (c.moveToFirst()) {
                    return true;
                }

            }
        } catch (SQLException e) {
            LOGE("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        return false;
    }

    public MediumCategoryData getMediumCategoryList(String largeCategoryCode){

        MediumCategoryData mediumCategoryData=null;
        int i=0;
        String[] mediumCategoryList=null;
        String[] mediumCategoryCodeList=null;

        String query=
                SELECT + CategoryCodeTable.COLUMN_MEDIUM_CATEGORY+","+CategoryCodeTable.COLUMN_CATEGORY_CODE+
                        FROM+ CategoryCodeTable.TABLE_NAME+
                        WHERE+"substr("+CategoryCodeTable.COLUMN_CATEGORY_CODE+",1,2) ='"+ largeCategoryCode+"'"+
                        GROUP_BY+CategoryCodeTable.COLUMN_MEDIUM_CATEGORY +
                        ORDER_BY + CategoryCodeTable.COLUMN_CATEGORY_CODE + ASC;

        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                mediumCategoryList=new String[c.getCount()];
                mediumCategoryCodeList=new String[c.getCount()];
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        mediumCategoryList[i]= c.getString(0);
                        mediumCategoryCodeList[i]= c.getString(1);
                        i++;
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGE("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        if(mediumCategoryCodeList!=null){
            mediumCategoryData=new MediumCategoryData();
            mediumCategoryData.mediumCategoryList=new String[mediumCategoryList.length];
            mediumCategoryData.categoryCodeList=new String[mediumCategoryList.length];
            String firstMid="",firstCode="";
            for (int j = 0 ; j<mediumCategoryCodeList.length ;j++){
                if(j == 0){
                    firstMid = mediumCategoryList[j];
                    firstCode = mediumCategoryCodeList[j];
                }else{
                    mediumCategoryData.mediumCategoryList[j-1] = mediumCategoryList[j];
                    mediumCategoryData.categoryCodeList[j-1] = mediumCategoryCodeList[j];
                }
            }
            mediumCategoryData.mediumCategoryList[mediumCategoryList.length-1] = firstMid;
            mediumCategoryData.categoryCodeList[mediumCategoryList.length-1] = firstCode;
        }

        return  mediumCategoryData;

    }

    public UserTransactionData loadTransaction(long identifier){
        UserTransactionData userTransactionData =null;
        Cursor c=null;
        String query=SELECT+"*"+FROM+TransactionsTable.TABLE_NAME+WHERE+TransactionsTable.COLUMN_IDENTIFIER+"="+identifier;

        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        userTransactionData=TransactionsTable.populateModel(c);
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGE("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }


        return userTransactionData;
    }

}
