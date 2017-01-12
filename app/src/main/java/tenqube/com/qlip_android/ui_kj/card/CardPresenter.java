package tenqube.com.qlip_android.ui_kj.card;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;

/**
 * Created by tenqube on 2016. 9. 26..
 */

public interface CardPresenter {

    void settingToolbar(Toolbar toolbar);
    void insertCard();
    void updateCard(CardTableData cardTableData);
    ArrayList<CardTableData> selectCard();
    void delete(CardTableData cardTableData);
    ArrayList<CardTableData> loadCardDataList();
}
