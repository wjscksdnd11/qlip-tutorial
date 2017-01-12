package tenqube.com.qlip_android.db.query;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.CategoryCodeTable;
import tenqube.com.qlip_android.db.table.TagMapTable;
import tenqube.com.qlip_android.db.table.TagTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.db.table.UserCategoryConfigTable;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;


public class CommonHelper extends QueryHelper {
    public CommonHelper(Context context) {
        super(context);
    }




    public void selectAll() {
        Cursor c=null;

        try {
             c = runQuery(SELECT + " * " + FROM + TransactionsTable.TABLE_NAME);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
                        LOGI("SELECTALL","TRAN"+ TransactionsTable.populateModel( c).toString());
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }



        try{
            c = runQuery(SELECT + " * " + FROM + CardTable.TABLE_NAME);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {


                        LOGI("SELECTALL","CARD"+ CardTable.populateModel(c).toString());
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        try{
            c = runQuery(SELECT + " * " + FROM + UserCategoryConfigTable.TABLE_NAME);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

                        LOGI("SELECTALL", "MANAGE_CATEGORY"+ UserCategoryConfigTable.populateModel(c).toString());
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {

            if (c != null)
                c.close();
        }

        try{
            c = runQuery(SELECT + " * " + FROM + TagMapTable.TABLE_NAME);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

                        LOGI("SELECTALL", "ManageHash"+ TagMapTable.populateModel(c).toString());
                        c.moveToNext();
                    }
                }
            }
        } catch (SQLException e) {
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        try{

            c = runQuery(SELECT + " * " + FROM + TagTable.TABLE_NAME);
            if (c != null) {
                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {

                        LOGI("SELECTALL", "HashTag"+ TagTable.populateModel(c).toString());
                        c.moveToNext();
                    }
                }
            }

        } catch (SQLException e) {

            LOGI("CANCELTEST", e.toString());

        } finally {
            if (c != null)
                c.close();
        }







    }




}
