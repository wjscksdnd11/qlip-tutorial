package tenqube.com.qlip_android.db.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.CategoryCodeTableData;
import tenqube.com.qlip_android.data.TagMapTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.InstallmentTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;
import tenqube.com.qlip_android.util.DateUtil;

import static tenqube.com.qlip_android.util.LogUtil.LOGE;


public class DetailTranHelper extends QueryHelper {
    public DetailTranHelper(Context context) {
        super(context);
    }




    public ArrayList<TagTableData> loadTagList(long identifier){

        ArrayList<TagTableData> resultList = new ArrayList<>();

        Cursor c=null;
        String query =
                SELECT + "*"+
                FROM+ TagTable.TABLE_NAME+
                ORDER_BY+TagTable.COLUMN_PRIORITY+ASC;

        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

                        TagTableData hashTagTableData = TagTable.populateModel(c);
                        if(isExistTag(hashTagTableData.tagId, identifier)){
                            hashTagTableData.isShown = true;
                        }

                        resultList.add(hashTagTableData);
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

        return  resultList;
    }


    public UserTransactionData insertUserAddTran(UserTransactionData model){

        model.companyAddress="none";
        model.fran="none";

        return  insertTransaction(model);

    }
    public UserTransactionData insertTransaction(UserTransactionData transaction){
        insert(TransactionsTable.TABLE_NAME, TransactionsTable.populateContent(mContext,transaction));
        applyInstallment(transaction.identifier,transaction.spentDate,transaction.spentMoney,transaction.installmentCount ,transaction.isOffset);
        return  transaction;
    }

    public void applyInstallment(long identifier,String spentDate,double spentMoney,int installmentCount, int isOffset) {

        ArrayList<String> insertList=new ArrayList<>();
        try {

            delete(InstallmentTable.TABLE_NAME,TransactionsTable.COLUMN_IDENTIFIER+"="+identifier,null);
            Calendar cal= DateUtil.convertStringToCalendarFULL(spentDate);//할부내역에 해당하는 날짜 셋팅
            String timeStr= DateUtil.getStringDateAsHHmmss(cal);
            //11월 29일
            if(installmentCount==0 || isOffset == 1)installmentCount=1;
            long dividerMoney=(long)(spentMoney / installmentCount);
            for (int i = 0; i < installmentCount; i++) {
                insertList.add("("+identifier+","+dividerMoney+",'"+DateUtil.getStringDateAsYYYYMMdd(cal)+" "+timeStr+"',"+(i+1)+")");
                cal= DateUtil.requestStartDateAsInstallment(-(i+1),spentDate);
            }
            String insertQuery = InstallmentTable.INSERT+ TextUtils.join(",", insertList);
            wdb.execSQL(insertQuery);
            insertList.clear();

        } catch (SQLException e) {
            LOGE(TAG, "selec error" + e);
        }


    }


    public CategoryCodeTableData loadCategoryData(String categoryCode){
        CategoryCodeTableData categoryCodeTableData=null;
        Cursor c=null;
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("*");
        query.append(FROM);
        query.append(CategoryCodeTable.TABLE_NAME);
        query.append(WHERE);
        query.append(CategoryCodeTable.COLUMN_CATEGORY_CODE);
        query.append("='");
        query.append(categoryCode);
        query.append("'");
        try{
            c=runQuery(query.toString());
            if(c!=null){
                if(c.moveToFirst()){
                    categoryCodeTableData=CategoryCodeTable.populateModel(c);
                }
            }
        }catch (SQLException e){
            LOGE(TAG,e.toString());
        }finally {
            if(c!=null){
                c.close();
            }
        }
        return  categoryCodeTableData;

    }

    public CardTableData loadCardData(long cardId){
        CardTableData cardTableData=null;
        Cursor c=null;
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("*");
        query.append(FROM);
        query.append(CardTable.TABLE_NAME);
        query.append(WHERE);
        query.append(CardTable.COLUMN_CARD_ID);
        query.append("=");
        query.append(cardId);
        try{
            c=runQuery(query.toString());
            if(c!=null){
                if(c.moveToFirst()){
                    cardTableData=CardTable.populateModel(c);
                }
            }
        }catch (SQLException e){
            LOGE(TAG,e.toString());
        }finally {
            if(c!=null){
                c.close();
            }
        }

        return  cardTableData;

    }

    public long getCategoryConfigId(String categoryCode){
        long manageCategoryId=-1;
        Cursor c=null;
        StringBuilder appendQuery=new StringBuilder();
        appendQuery.append(SELECT);
        appendQuery.append(UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID);
        appendQuery.append(",");
        appendQuery.append(UserCategoryConfigTable.COLUMN_CATEGORY_CONFIG_ID);

        appendQuery.append(FROM);
        appendQuery.append(UserCategoryConfigTable.TABLE_NAME);
        appendQuery.append(WHERE);
        appendQuery.append(UserCategoryConfigTable.COLUMN_CATEGORY_CODE);
        appendQuery.append("='");
        appendQuery.append(categoryCode.substring(0,2));
        appendQuery.append("'");

        try {
            c = runQuery(appendQuery.toString());
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        manageCategoryId=c.getLong(0);
                        c.moveToNext();
                    }
                }
            }else{
                UserCategoryConfigTableData mData=new UserCategoryConfigTableData();
                mData.exceptType=0;
                mData.mainType=0;
                mData.priority=0;
                mData.categoryCode=categoryCode.substring(0,2);
                manageCategoryId=insertManageCategory(mData);
            }
        }catch (SQLException e) {
            LOGE("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        return  manageCategoryId;
    }
    public long insertManageCategory(UserCategoryConfigTableData mData){
        return insert(UserCategoryConfigTable.TABLE_NAME, UserCategoryConfigTable.populateContent(mContext,mData));
    }


    public void deleteTransaction(long identifier){

        delete(TransactionsTable.TABLE_NAME, TransactionsTable.COLUMN_IDENTIFIER + "=?", new String[]{identifier + ""});
        delete(InstallmentTable.TABLE_NAME, TransactionsTable.COLUMN_IDENTIFIER + "=?", new String[]{identifier + ""});
//        delete(FullSmsTable.TABLE_NAME, TransactionsTable.COLUMN_IDENTIFIER + "=?", new String[]{identifier + ""});
        delete(TagMapTable.TABLE_NAME,TransactionsTable.COLUMN_IDENTIFIER+"=?",new String[]{identifier+""});

    }

    public void updateCategoryCode(String keyword, String categoryCode, int dwType){
        ContentValues values=new ContentValues();
        values.put(TransactionsTable.COLUMN_USER_UPDATE,1);
        values.put(TransactionsTable.COLUMN_CATEGORY_CODE,categoryCode);
        update(TransactionsTable.TABLE_NAME, values, TransactionsTable.COLUMN_KEYWORD + "=? AND "+TransactionsTable.COLUMN_DW_TYPE + "=?", new String[]{keyword,dwType+""});

    }

    public void insertHashTag(ArrayList<TagTableData> tagList, long identifier){
        deleteManageHash(identifier);
        insertManageTable(identifier,tagList);
    }

    public void insertManageTable(long identifier,ArrayList<TagTableData> tagList){
        TagMapTableData model2 = new TagMapTableData();
        model2.identifier = identifier;
        for(TagTableData hash:tagList){
            model2.tagId = hash.tagId;
            insert(TagMapTable.TABLE_NAME, TagMapTable.populateContent(mContext,model2));
        }
    }


    public void deleteManageHash(long identifier){
        delete(TagMapTable.TABLE_NAME,TransactionsTable.COLUMN_IDENTIFIER+IN+"("+identifier+")" ,null);
    }

    public long insertHashTagName(String tagName) {
        ContentValues values=new ContentValues();
        values.put(TagTable.COLUMN_HASH_TAG_NAME, tagName);
        values.put(TagTable.COLUMN_PRIORITY,Integer.MAX_VALUE);
        return insert(TagTable.TABLE_NAME,values);
    }


}
