package tenqube.com.qlip_android.ui_kj.card;

import android.content.Context;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.db.query.CardHelper;

/**
 * Created by tenqube on 2016. 9. 26..
 */

public class CardModel {

    private CardHelper cardHelper;
    private Context mContext;
    public CardModel(Context context) {
        this.mContext = context;
        cardHelper = new CardHelper(context);
    }

    //    cardName : String,
//    cardType: int,
//    changeCardName : String,
//    changeCardType: int,
//    exceptType,
//    billingDate,(추가 청구기준일),
//    totalSum
//    memo,(추가)

    public ArrayList<CardTableData> currentCardTableDataList() {
        return cardHelper.loadCardList();
    }

    public ArrayList<CardTableData> loadCardTableDataList() {
        ArrayList<CardTableData> openList = new ArrayList<>();
        CardTableData model = new CardTableData();

        model.cardName = "국민은행";
        model.cardSubType = 1;
        model.cardType = 0;

        openList.add(model);


        model = new CardTableData();

        model.cardName = "신한은행";
        model.cardSubType = 2;
        model.cardType = 2;

        openList.add(model);


        model = new CardTableData();

        model.cardName = "우리은행";
        model.cardSubType = 1;
        model.cardType = 0;

        openList.add(model);


        model = new CardTableData();

        model.cardName = "농협은행";
        model.cardSubType = 2;
        model.cardType = 4;

        openList.add(model);


        return openList;
    }


    public void insertCard() {

        ArrayList<CardTableData> array = loadCardTableDataList();
        for (CardTableData cardTableData: array) {

            CardTableData data = new CardTableData();
            data.cardName = cardTableData.cardName;
            data.cardType = cardTableData.cardType;
            data.cardSubType = cardTableData.cardSubType;
            cardHelper.insertCard(data);
        }

    }

    public void updateCard(CardTableData cardTableData) {


        cardHelper.updateCard(cardTableData);
    }

    public ArrayList<CardTableData> selectCardList() {


        return cardHelper.selectCardList();
    }

    public void delete(CardTableData cardTableData) {

        cardHelper.deleteCard(cardTableData);

    }


}
