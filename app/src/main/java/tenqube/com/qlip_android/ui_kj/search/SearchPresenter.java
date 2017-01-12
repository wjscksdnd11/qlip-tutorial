package tenqube.com.qlip_android.ui_kj.search;


import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;

public interface SearchPresenter {

    ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwTyp);
    ArrayList<CardTableData> loadCardList(boolean isCard);
    ArrayList<TagTableData> loadHashTagList();

    ArrayList<UserCategoryConfigTableData> makeCategoryList(ArrayList<UserCategoryConfigTableData> selectedList);
    ArrayList<CardTableData> makeCardList(ArrayList<CardTableData> selectedList);
    ArrayList<TagTableData> makeHashTagList(ArrayList<TagTableData> selectedList);



}
