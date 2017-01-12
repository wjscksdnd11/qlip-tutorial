package tenqube.com.qlip_android.ui_kj.detail;


import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.TagTableData;

public interface DetailTranPresenter {
    void setView(DetailTranPresenter.DetailTranView view);
    void loadSpendData();
    void loadCardData();
    void loadCategoryData();
    void loadSpentDate();
    void initView();
    void receiveIntentValue();
    void settingToolbar(Toolbar toolbar);
    void goSelectCategoryActivity();
    void showMediumCategoryDialog();
    void showInstallmentDialog();
    void showRepeatDialog();
    void confirm();
    void goDatePicker();
    void goTimePicker();
    void onRepeatCancel();
    void onCalendar(Calendar calendar);
    void onTimeCalendar(Calendar calendar);
    void receiverSpentMoneyData(double spentMoney);
    void receiveCategoryData(String categoryCode, String largeCategoryName);
    void showDeleteDialog();
    void deleteTransaction();
    ArrayList<TagTableData> loadTagList();
    long insertHashTagName(String tagName);

    interface DetailTranView {
        void setLargeCategoryView(String categoryCode, String largeCategoryName);
        void setMediumCategoryTextView(String mediumCategory);
        void setFranchiseTextView(String franchise);
        void setSpentMoneyTextView(String spentMoney);
        void setCardNameTextView(String cardName);
        void setInstallmentTextView(String installment);
        void setSpentDateTextView(String spentDate);
        void setSpentTimeTextView(String spentTime);
        void setRepeatTextView(String repeat, int color);
        void setMemoEditText(String memo);
        void setKeywordEditText(String keyword);
        void setVisibleFranchise(int visible);
        void setVisibleMediumCategory(int visible);
        void setVisibleInstallment(int visible);
        void setVisibleInstallmentArrow(int visible);
    }
}
