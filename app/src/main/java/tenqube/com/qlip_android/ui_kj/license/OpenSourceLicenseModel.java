package tenqube.com.qlip_android.ui_kj.license;



import java.util.ArrayList;

import tenqube.com.qlip_android.data.OpenSourceData;


public class OpenSourceLicenseModel {


    public OpenSourceLicenseModel() {

    }

    public ArrayList<OpenSourceData> loadLicenseDataList(){
        ArrayList<OpenSourceData> openList=new ArrayList<>();
        OpenSourceData model = new OpenSourceData();
        model.title = "MpAndroidChart";
        model.url = "https://github.com/PhilJay/MPAndroidChart";
        model.copyright = "Copyright 2016 Philipp Jahoda";
        model.license = "Apache License 2.0";
        openList.add(model);

        model = new OpenSourceData();
        model.title = "MaterialViewPager";
        model.url = "https://github.com/florent37/MaterialViewPager";
        model.copyright = "Copyright 2015 florent37";
        model.license = "Apache License 2.0";
        openList.add(model);


        model = new OpenSourceData();
        model.title = "ScrimInsetsScrollView";
        model.url = "https://github.com/google/iosched";
        model.copyright = "Copyright 2014 Google Inc";
        model.license = "Apache License 2.0";
        openList.add(model);

        model = new OpenSourceData();
        model.title = "ViewPagerIndicator";
        model.url = "https://github.com/JakeWharton/ViewPagerIndicator";
        model.copyright = "Copyright 2012 Jake Wharton\n" +
                "Copyright 2011 Patrik Åkerfeldt\n" +
                "Copyright 2011 Francisco Figueiredo Jr.";
        model.license = "Apache License 2.0";
        openList.add(model);

        model = new OpenSourceData();
        model.title = "Android SwipeRefreshLayoutBasic Sample";
        model.url = "https://developer.android.com/intl/ko/samples/SwipeRefreshLayoutBasic/";
        model.copyright = "Copyright 2014 The Android Open Source Project";
        model.license = "Apache License 2.0";
        openList.add(model);

        model = new OpenSourceData();
        model.title = "Google Cloud Messaging(gcm)";
        model.url = "https://developer.android.com/google/gcm/";
        model.copyright = "Copyright 2012-2014 The Android Open Source Project";
        model.license = "Apache License 2.0";
        openList.add(model);


        model = new OpenSourceData();
        model.title = "Google Calendar";
        model.url = "https://developers.google.com/google-apps/calendar/";
        model.copyright = "Copyright The Android Open Source Project";
        model.license = "Apache License 2.0";
        openList.add(model);


        model = new OpenSourceData();
        model.title = "opencsv";
        model.url = "http://opencsv.sourceforge.net/license.html";
        model.copyright = "opencsv was developed in a couple of hours by Glen Smith";
        model.license = "Apache License 2.0";
        openList.add(model);

        return  openList;
    }

}
