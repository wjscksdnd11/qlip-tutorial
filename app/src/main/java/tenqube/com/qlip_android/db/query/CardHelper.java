package tenqube.com.qlip_android.db.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.db.table.CardTable;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;

/**
 * Created by tenqube on 2016. 9. 26..
 */

public class CardHelper extends QueryHelper {

    public CardHelper(Context context) {
        super(context);
    }


    /**
     * example) load card list all;
     * @return
     */
    public ArrayList<CardTableData> loadCardList(){
        ArrayList<CardTableData> cardList = new ArrayList<>();

        String  query = SELECT +"*"+
                        FROM+ CardTable.TABLE_NAME+
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
            LOGI("CANCELTEST", e.toString());
        } finally {
            if (c != null)
                c.close();
        }

        return  cardList;

    }


    /**
     * cardTyp  = 0 인거 뽑아보기
     * @return
     */
    public ArrayList<CardTableData> selectCardList(){
        ArrayList<CardTableData> cardList = new ArrayList<>();


        String query = SELECT+"*"+
                FROM+CardTable.TABLE_NAME+
                WHERE+CardTable.COLUMN_CARD_TYPE+" = 0";


        Cursor c = null;
        try{

            c = runQuery(query);
            if(c!=null){
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        cardList.add(CardTable.populateModel(c));
                        c.moveToNext();


                    }
                }

            }

        } catch (SQLException e){

            LOGI("CANCELTEST",e.toString());
        }finally{
            if(c!=null)
                c.close();

        }


        return  cardList;

    }


    /**
     * card정보 인서트 해보기
     * @param cardTableData
     */
    public void insertCard(CardTableData cardTableData){

        insert(CardTable.TABLE_NAME, CardTable.populateContent(mContext,cardTableData));
    }

    /**
     * 인서트한 카드 이름 변경해보기 card_name
     * @param cardTableData
     */
    public void updateCard(CardTableData cardTableData){
        String selection = CardTable.COLUMN_CARD_NAME+" =? "+QueryHelper.AND+ CardTable.COLUMN_CARD_TYPE+" =? "
                +QueryHelper.AND+CardTable.COLUMN_CARD_SUB_TYPE+" =? ";
        String [] args  = {cardTableData.cardName,cardTableData.cardType+"",cardTableData.cardSubType+""};

        int random = (int) (Math.random() * 3);
        String[] card_name = {"농협", "우리", "신한", "한국"};
        ContentValues values = new ContentValues();
        values.put(CardTable.COLUMN_CARD_NAME, card_name[random]);

        update(CardTable.TABLE_NAME,values,selection,args);




    }

    /**
     * card정보 삭제 해보기
     * @param cardTableData
     */
    public void deleteCard(CardTableData cardTableData){
        String selection = CardTable.COLUMN_CARD_NAME+" =? "+ QueryHelper.AND+CardTable.COLUMN_CARD_SUB_TYPE+"=?"
                +QueryHelper.AND+CardTable.COLUMN_CARD_TYPE+"=?";
        String []args = {cardTableData.cardName,cardTableData.cardSubType+"",cardTableData.cardType+""};

        delete(CardTable.TABLE_NAME,selection,args);

    }




}