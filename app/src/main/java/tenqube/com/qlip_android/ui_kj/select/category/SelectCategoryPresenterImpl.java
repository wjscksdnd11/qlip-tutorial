package tenqube.com.qlip_android.ui_kj.select.category;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;


public class SelectCategoryPresenterImpl implements SelectCategoryPresenter {

    private SelectCategoryActivity mActivity;
    private SelectCategoryModel selectCategoryModel;
    private double mSpentMoney;
    private int mDwType;
    public SelectCategoryPresenterImpl(SelectCategoryActivity mActivity) {
        this.mActivity = mActivity;
        selectCategoryModel = new SelectCategoryModel(mActivity);
    }

    @Override
    public void receiveIntentValue() {
        mSpentMoney=mActivity.getIntent().getDoubleExtra(Constants.IntentParameter.SPENT_MONEY,0);
        mDwType=mActivity.getIntent().getIntExtra(Constants.IntentParameter.DW_TYPE_CODE,-1);
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
    public ArrayList<UserCategoryConfigTableData> loadCategoryList() {
        return selectCategoryModel.loadCategoryList(mDwType);
    }



    @Override
    public void goReturnActivity(String categoryCode, String largeCategoryName) {

        Intent intent = new Intent();
        intent.putExtra(Constants.IntentParameter.CATEGORY_CODE, categoryCode);
        intent.putExtra(Constants.IntentParameter.CATEGORY, largeCategoryName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        mActivity.setResult(Activity.RESULT_OK,intent);
        mActivity.finish();

    }
}
