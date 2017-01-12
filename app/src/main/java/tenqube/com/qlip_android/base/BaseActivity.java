package tenqube.com.qlip_android.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static tenqube.com.qlip_android.util.LogUtil.makeLogTag;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = makeLogTag(BaseActivity.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected Toolbar getActionBarToolbar(Toolbar toolbar) {

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }
        return  toolbar;
    }


    protected Toolbar getActionBarToolbarLv2(Toolbar toolbar) {

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);

            }
        }

        return  toolbar;
    }


    protected void setOnClickListener(View v, View.OnClickListener listener){
        if(v!=null)
            v.setOnClickListener(listener);
    }

}
