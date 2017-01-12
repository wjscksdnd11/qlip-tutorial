package tenqube.com.qlip_android.ui_kj.search;


import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;


public class SearchPresenterImpl implements SearchPresenter{
    private SearchActivity mActivity;
    private SearchModel searchModel;

    public SearchPresenterImpl(SearchActivity mActivity) {
        this.mActivity = mActivity;
        searchModel=new SearchModel(mActivity);

    }


    @Override
    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int dwType) {
       return searchModel.loadCategoryList(dwType);
    }

    @Override
    public ArrayList<CardTableData> loadCardList(boolean isCard ) {
       return searchModel.loadCardList(isCard);
    }

    @Override
    public ArrayList<TagTableData> loadHashTagList() {
        return searchModel.loadTagList();
    }

    @Override
    public ArrayList<UserCategoryConfigTableData> makeCategoryList(ArrayList<UserCategoryConfigTableData> selectedList) {
        ArrayList<UserCategoryConfigTableData> resultList=new ArrayList<>();
        for(UserCategoryConfigTableData model:selectedList)
            if(model.check)resultList.add(model);
        return resultList;
    }

    @Override
    public ArrayList<CardTableData> makeCardList(ArrayList<CardTableData> selectedList) {
        ArrayList<CardTableData> resultList=new ArrayList<>();
        for(CardTableData model:selectedList)
            if(model.flag)resultList.add(model);
        return resultList;
    }

    @Override
    public ArrayList<TagTableData> makeHashTagList(ArrayList<TagTableData> selectedList) {
        ArrayList<TagTableData> resultList=new ArrayList<>();
        for(TagTableData model:selectedList)
            if(model.check)resultList.add(model);
        return resultList;
    }


}
