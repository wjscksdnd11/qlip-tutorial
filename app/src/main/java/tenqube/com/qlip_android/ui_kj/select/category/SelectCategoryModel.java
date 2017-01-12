package tenqube.com.qlip_android.ui_kj.select.category;


import java.util.ArrayList;

import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.db.query.SelectCategoryHelper;

public class SelectCategoryModel {

    private SelectCategoryActivity mActivity;
    private SelectCategoryHelper selectCategoryHelper;
        public SelectCategoryModel(SelectCategoryActivity mActivity) {
            this.mActivity = mActivity;
            selectCategoryHelper=new SelectCategoryHelper(mActivity);
        }

    public ArrayList<UserCategoryConfigTableData> loadCategoryList(int mDwType){
        return  selectCategoryHelper.loadCategoryList(mDwType);
    }


}
