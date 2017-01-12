package tenqube.com.qlip_android.ui_kj.license;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.OpenSourceData;

public class OpenSourceLicensePresenterImpl implements OpenSourceLicensePresenter {

    private OpenSourceLicenseActivity mActivity;
    private OpenSourceLicenseModel mModel;

    /**
     * 생성자 model class 초기화
     * @param mActivity
     */
    public OpenSourceLicensePresenterImpl(OpenSourceLicenseActivity mActivity) {
        this.mActivity = mActivity;
        this.mModel = new OpenSourceLicenseModel();
    }


    /**
     * toolbar 설정 함수
     * @param toolbar toolbar view
     */
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

    /**
     * 라이센스 데이트 가져오는 함수
     * @return
     */
    @Override
    public ArrayList<OpenSourceData> loadLicenseDataList() {
        return mModel.loadLicenseDataList();
    }



}
