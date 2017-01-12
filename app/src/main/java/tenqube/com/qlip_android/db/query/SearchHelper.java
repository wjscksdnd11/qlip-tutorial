package tenqube.com.qlip_android.db.query;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;


import java.util.ArrayList;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;

import static tenqube.com.qlip_android.constans.Constants.CardType.BANK_ACCOUNT;
import static tenqube.com.qlip_android.constans.Constants.CardType.CARD;
import static tenqube.com.qlip_android.constans.Constants.CardType.CASH;
import static tenqube.com.qlip_android.constans.Constants.CardType.CHECK;
import static tenqube.com.qlip_android.constans.Constants.DWType.DEPOSIT;
import static tenqube.com.qlip_android.util.LogUtil.LOGE;


public class SearchHelper extends QueryHelper {
    public SearchHelper(Context context) {
        super(context);
    }


    public ArrayList<CardTableData> loadCardList(boolean isCard){
        ArrayList<CardTableData> cardList=new ArrayList<>();
        String query =
                SELECT +"*"+
                FROM+ CardTable.TABLE_NAME;

        query+=isCard?
                WHERE+CardTable.COLUMN_CARD_NAME+"="+ CardTable.COLUMN_CARD_TOTAL_CARD_NAME+
                        AND+CardTable.COLUMN_CARD_TYPE+"="+CardTable.COLUMN_CARD_TOTAL_CARD_TYPE+
                        AND+CardTable.COLUMN_CARD_SUB_TYPE+"="+CardTable.COLUMN_CARD_TOTAL_CARD_SUB_TYPE+
                        AND+CardTable.COLUMN_CARD_CHANGE_TYPE+IN+"("+ CHECK.ordinal()+","+ CARD.ordinal()+")"+
                GROUP_BY+CardTable.COLUMN_CARD_CHANGE_NAME+","+CardTable.COLUMN_CARD_CHANGE_TYPE+","+CardTable.COLUMN_CARD_CHANGE_SUB_TYPE+
                ORDER_BY+CardTable.COLUMN_PRIORITY+ASC
                :
                WHERE+CardTable.COLUMN_CARD_NAME+"="+CardTable.COLUMN_CARD_TOTAL_CARD_NAME+
                        AND+CardTable.COLUMN_CARD_TYPE+"="+CardTable.COLUMN_CARD_TOTAL_CARD_TYPE+
                        AND+CardTable.COLUMN_CARD_SUB_TYPE+"="+CardTable.COLUMN_CARD_TOTAL_CARD_SUB_TYPE+
                        AND+CardTable.COLUMN_CARD_CHANGE_TYPE+IN+"("+ CASH.ordinal()+","+ BANK_ACCOUNT.ordinal()+")"+
                GROUP_BY+CardTable.COLUMN_CARD_CHANGE_NAME+","+CardTable.COLUMN_CARD_CHANGE_TYPE+","+CardTable.COLUMN_CARD_CHANGE_SUB_TYPE+
                ORDER_BY+CardTable.COLUMN_PRIORITY+ASC;


        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        cardList.add(CardTable.populateModel(c));
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        return  cardList;

    }



    public ArrayList<TagTableData> loadTagList(){
        ArrayList<TagTableData> tagList=new ArrayList<>();
        String query;
        query =
                SELECT +"*"+
                FROM+ TagTable.TABLE_NAME;

        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        tagList.add(TagTable.populateModel(c));
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            Log.e("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }
        return  tagList;

    }


//    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwType){
//
//        Cursor c=null;
//        ArrayList<UserCategoryConfigTableData> categoryList=new ArrayList<>();
//        String query=
//                SELECT +"*"+
//                FROM+ UserCategoryConfigTable.TABLE_NAME+UserCategoryConfigTable.AS_ALIAS+
//                JOIN + CategoryCodeTable.TABLE_NAME+CategoryCodeTable.AS_ALIAS+
//                ON + CategoryCodeTable.ALIAS+CategoryCodeTable.COLUMN_CATEGORY_CODE+"="+UserCategoryConfigTable.ALIAS+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE;
//
//        query+= DEPOSIT.ordinal() == dwType?
//                WHERE+ "substr("+UserCategoryConfigTable.ALIAS+UserCategoryConfigTable.COLUMN_CATEGORY_CODE+",1,2)"+IN+"('"+
//                        Constants.CategoryCode.INCOME_MAIN+"','"+
//                        Constants.CategoryCode.INCOME_SUB+"','"+
//                        Constants.CategoryCode.DEPOSIT_LOAN+"','"+
//                        Constants.CategoryCode.DEPOSIT_ETC+"','"+
//                        Constants.CategoryCode.DEPOSIT_MOVING_ASSET+"')"+
//                ORDER_BY+ UserCategoryConfigTable.COLUMN_PRIORITY+ASC+","+ UserCategoryConfigTable.ALIAS+UserCategoryConfigTable.COLUMN_CATEGORY_CODE+ASC
//                :
//                WHERE+ "substr("+UserCategoryConfigTable.ALIAS+UserCategoryConfigTable.COLUMN_CATEGORY_CODE+",1,2)"+NOT_IN+"('"+
//                        Constants.CategoryCode.INCOME_MAIN+"','"+
//                        Constants.CategoryCode.INCOME_SUB+"','"+
//                        Constants.CategoryCode.DEPOSIT_LOAN+"','"+
//                        Constants.CategoryCode.DEPOSIT_ETC+"','"+
//                        Constants.CategoryCode.DEPOSIT_MOVING_ASSET+"','11')"+
//                ORDER_BY+ UserCategoryConfigTable.COLUMN_PRIORITY+ASC+","+ UserCategoryConfigTable.ALIAS+UserCategoryConfigTable.COLUMN_CATEGORY_CODE+ASC;
//
//
//
//        try {
//            c = runQuery(query);
//            if (c != null) {
//                if (c.moveToFirst()) {
//                    while (!c.isAfterLast()) {
//                        UserCategoryConfigTableData data = UserCategoryConfigTable.populateModel(c);
//                        data.largeCategory = c.getString(c.getColumnIndex(CategoryCodeTable.COLUMN_LARGE_CATEGORY));
//                        Log.i("category", data.toString());
//                        categoryList.add(data);
//                        c.moveToNext();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            Log.e("CANCELTEST", e.toString());
//        } finally {
//            if (c != null)
//                c.close();
//        }
//        return  categoryList;
//
//    }

    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwType){
        ArrayList<UserCategoryConfigTableData> categoryList=new ArrayList<>();
        String query;

        if(dwType== DEPOSIT.ordinal()){
            query=SELECT +"*"+
                    FROM+ UserCategoryConfigTable.TABLE_NAME+
                    WHERE+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE+IN+"('"+Constants.CategoryCode.INCOME_MAIN+"','"+Constants.CategoryCode.INCOME_SUB+"','"+Constants.CategoryCode.DEPOSIT_LOAN+"','"+Constants.CategoryCode.DEPOSIT_ETC+"','"+Constants.CategoryCode.DEPOSIT_MOVING_ASSET+"')"+
                    ORDER_BY+ UserCategoryConfigTable.COLUMN_PRIORITY+ASC+","+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE+ASC;

        }else{
            query=SELECT +"*"+
                    FROM+ UserCategoryConfigTable.TABLE_NAME+
                    WHERE+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE+NOT_IN+"('"+Constants.CategoryCode.INCOME_MAIN+"','"+Constants.CategoryCode.INCOME_SUB+"','"+Constants.CategoryCode.DEPOSIT_LOAN+"','"+Constants.CategoryCode.DEPOSIT_ETC+"','"+Constants.CategoryCode.DEPOSIT_MOVING_ASSET+"','11')"+
                    ORDER_BY+ UserCategoryConfigTable.COLUMN_PRIORITY+ASC+","+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE+ASC;
        }

        Cursor c=null;
        try {
            c = runQuery(query);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        categoryList.add(UserCategoryConfigTable.populateModel(c));
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

        categoryList=getCategoryName(categoryList);
        return  categoryList;

    }

    public ArrayList<UserCategoryConfigTableData> getCategoryName(ArrayList<UserCategoryConfigTableData> categoryList){

        for (int i=0 ;i<categoryList.size();i++){
            String query;
            query=SELECT + CategoryCodeTable.COLUMN_LARGE_CATEGORY+
                    FROM+ CategoryCodeTable.TABLE_NAME+
                    WHERE+"substr("+CategoryCodeTable.COLUMN_CATEGORY_CODE+",1,2)='"+categoryList.get(i).categoryCode+"'";

            Cursor c=null;
            try {
                c = runQuery(query);
                if (c != null) {
                    if (c.moveToFirst()) {
                        categoryList.get(i).mediumCategoryList="";
                        while (!c.isAfterLast()) {
//                            categoryList.get(i).largeCategory= AES256Cipher.getInstance(mContext).decrypt(c.getString(0));
                            categoryList.get(i).largeCategory= c.getString(0);
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
        }


        return  categoryList;

    }

}
