package tenqube.com.qlip_android.ui_kj.detail;


import android.content.Context;


import java.util.ArrayList;

import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.CategoryCodeTableData;
import tenqube.com.qlip_android.data.MediumCategoryData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.query.DetailTranHelper;

public class DetailTranModel {

    private Context mActivity;
    private DetailTranHelper mDetailTranHelper;

    public DetailTranModel(Context mActivity) {
        this.mActivity = mActivity;
        mDetailTranHelper = new DetailTranHelper(mActivity);
    }

    public MediumCategoryData getMediumCategoryList(String largeCategoryCode){
        return mDetailTranHelper.getMediumCategoryList(largeCategoryCode);
    }

    public ArrayList<TagTableData> loadTagList(long identifier){

        return  mDetailTranHelper.loadTagList(identifier);
    }

    public UserTransactionData insertUserAddTran(UserTransactionData model){

        return mDetailTranHelper.insertUserAddTran(model);

    }

    public UserTransactionData loadTransaction(long identifier){
        return  mDetailTranHelper.loadTransaction(identifier);
    }

    public CategoryCodeTableData loadCategoryData(String categoryCode){
        return  mDetailTranHelper.loadCategoryData(categoryCode);
    }

    public CardTableData loadCardData(long cardId){

        return  mDetailTranHelper.loadCardData(cardId);
    }

    public void deleteTransaction(long identifier){
        mDetailTranHelper.deleteTransaction(identifier);
    }

    public long getCategoryConfigId(String categoryCode){
        return mDetailTranHelper.getCategoryConfigId(categoryCode);
    }

    public void insertHashTag(long transactionId, ArrayList<TagTableData> tagList){
        mDetailTranHelper.insertHashTag(tagList,transactionId);
    }

    public int getMediumCategoryPos(String[] mList, String mediumStr){

        for(int i = 0 ;i<mList.length ;i++){
            if(mList[i].equals(mediumStr)){
                return  i;
            }
        }

        return  0;
    }

    public long insertHashTagName(String tagName){
        return  mDetailTranHelper.insertHashTagName(tagName);
    }

    public  ArrayList<TagTableData> getSelectedTagList(ArrayList<TagTableData> tagList){
        ArrayList<TagTableData> selectedTagList = new ArrayList<>();
        if(!tagList.isEmpty()) {
            for (TagTableData model : tagList) {
                if(model.isShown)
                    selectedTagList.add(model);
            }
        }
        return  selectedTagList;

    }

    public ArrayList<String> getHashTagNameList(ArrayList<TagTableData> selectedTagList){
        ArrayList<String> resultList = new ArrayList<>();

        if(!selectedTagList.isEmpty()) {
            for (TagTableData model : selectedTagList) {
                resultList.add(model.tagName);
            }
        }
        return resultList;
    }

}
