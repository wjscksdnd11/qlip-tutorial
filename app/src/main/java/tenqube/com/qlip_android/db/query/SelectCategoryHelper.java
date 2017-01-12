package tenqube.com.qlip_android.db.query;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;

import static tenqube.com.qlip_android.constans.Constants.DWType.DEPOSIT;
import static tenqube.com.qlip_android.util.LogUtil.LOGE;

public class SelectCategoryHelper extends QueryHelper {
    public SelectCategoryHelper(Context context) {
        super(context);
    }

    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwType){
        ArrayList<UserCategoryConfigTableData> categoryList=new ArrayList<>();
        String query;

        if(dwType== DEPOSIT.ordinal()){
            query=SELECT +"*"+
                    FROM+ UserCategoryConfigTable.TABLE_NAME+
                    WHERE+ UserCategoryConfigTable.COLUMN_CATEGORY_CODE+IN+"('"+ Constants.CategoryCode.INCOME_MAIN+"','"+Constants.CategoryCode.INCOME_SUB+"','"+Constants.CategoryCode.DEPOSIT_LOAN+"','"+Constants.CategoryCode.DEPOSIT_ETC+"','"+Constants.CategoryCode.DEPOSIT_MOVING_ASSET+"')"+
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
