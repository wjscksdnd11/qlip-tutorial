package tenqube.com.qlip_android.ui_kj.select.category;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserCategoryConfigTableData;

public interface SelectCategoryPresenter {

    void receiveIntentValue();
    void settingToolbar(Toolbar toolbar);
    ArrayList<UserCategoryConfigTableData> loadCategoryList();
    void goReturnActivity(String categoryCode, String largeCategoryName);


}
