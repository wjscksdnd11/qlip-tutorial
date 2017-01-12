package tenqube.com.qlip_android.ui_kj.license;


import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import tenqube.com.qlip_android.data.OpenSourceData;


public interface OpenSourceLicensePresenter {

    void settingToolbar(Toolbar toolbar);
    ArrayList<OpenSourceData> loadLicenseDataList();

}
