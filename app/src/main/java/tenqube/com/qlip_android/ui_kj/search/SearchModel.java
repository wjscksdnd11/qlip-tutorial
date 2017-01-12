package tenqube.com.qlip_android.ui_kj.search;


import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.db.query.SearchHelper;

public class SearchModel {

    private SearchActivity mActivity;
    private SearchHelper searchHelper;
    public SearchModel(SearchActivity mActivity) {
        this.mActivity = mActivity;
        searchHelper=new SearchHelper(mActivity);
    }

    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwType){
        return searchHelper.loadCategoryList(dwType);
    }
    public ArrayList<CardTableData> loadCardList(boolean isCard){
        return searchHelper.loadCardList(isCard);
    }
    public ArrayList<TagTableData> loadTagList(){
        return searchHelper.loadTagList();
    }

}
