package tenqube.com.qlip_android.ui_kj.card;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.CardTableData;

/**
 * Created by tenqube on 2016. 9. 26..
 */

public class CardPresenterImpl implements CardPresenter {

    private CardActivity mActivity;
    private CardModel mModel;


    public CardPresenterImpl(CardActivity activity) {

        this.mActivity = activity;
        this.mModel = new CardModel(mActivity);


    }


    @Override
    public void settingToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.qlip_main_black_lv0));
    }

    @Override
    public void insertCard() {


        mModel.insertCard();

    }

    @Override
    public void updateCard(CardTableData cardTableData) {

        mModel.updateCard(cardTableData);

    }

    @Override
    public ArrayList<CardTableData> selectCard() {

        return mModel.selectCardList();

    }

    @Override
    public void delete(CardTableData cardTableData) {


        mModel.delete(cardTableData);
    }

    @Override
    public ArrayList<CardTableData> loadCardDataList() {
        return mModel.currentCardTableDataList();
    }
}
