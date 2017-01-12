package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

public class CalSampleActivity extends AppCompatActivity implements  CalendarFragment.Callback {
    private final static String[] spinner_menu = {"수입", "지출", "수입/지출", "제외내역"};
    public ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private Spinner mSpinner;
    private CalSamplePresenter calendarPresenter;
    public final static String DW_TYPE = "dw_type";
    private SharedPreferenceUtil sharedPreferenceUtil;
    private SpentRecyclerFragment spentRecyclerFragment;
    public final static String OPTION = "option";
    private int spinner_type;
    private int mParentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_sample);
        calendarPresenter = new CalSamplePresenterImpl(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calendarPresenter.settingToolbar(toolbar);
//스피너

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");

        mSpinner = (Spinner) toolbar.findViewById(R.id.lv2_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner_menu);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(arrayAdapter);
//뷰페이져

        sharedPreferenceUtil = new SharedPreferenceUtil(this);
        int defalut_value = sharedPreferenceUtil.getValue(DW_TYPE, 1);
        spinner_type = defalut_value;
        mSpinner.setSelection(defalut_value);


        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setPageMargin(pageMargin);

        mViewPager.setOffscreenPageLimit(3);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabLayout);

        calendarPresenter.initAdapter(mViewPager,defalut_value,getOptionIndex());
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        calendarPresenter.loadData();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mParentPos = position;
//                calendarPresenter.setData(position,spinner_type,getOptionIndex());



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_type =position;
                sharedPreferenceUtil.put(DW_TYPE, position);

                calendarPresenter.changeSpinner(position,getOptionIndex());




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        spentRecyclerFragment = (SpentRecyclerFragment) fragmentManager.findFragmentById(R.id.spent_frgment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(spentRecyclerFragment);
        fragmentTransaction.commit();

    }
    private int current_selected_menu;
    private Menu main_menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spinner_tabs, menu);
        int default_option = sharedPreferenceUtil.getValue(OPTION,R.id.day_sum);
        current_selected_menu = default_option;
        main_menu = menu;

       updateMenuState();
        return true;

    }
    public void updateMenuState(){

        if (main_menu==null){
            return;
        }
        int sz = main_menu.size();
        for(int i=0;i<sz;i++){
            if(main_menu.getItem(i).getItemId()==current_selected_menu){
                main_menu.getItem(i).setEnabled(false);
            }
            else{
                main_menu.getItem(i).setEnabled(true);

            }
        }

        calendarPresenter.changeSpinner(spinner_type,getOptionIndex());
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            Log.i("option",item.getItemId()+"");
        int id = item.getItemId();
        switch (id) {
            case R.id.search_button:
                Toast.makeText(getApplicationContext(), "search_intent",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:

                return true;
            case R.id.day_sum:
                Toast.makeText(this, "합산보기", Toast.LENGTH_SHORT).show();
                current_selected_menu = item.getItemId();
                updateMenuState();
                sharedPreferenceUtil.put(OPTION,R.id.day_sum);
                return  true;
            case R.id.day_item :
                Toast.makeText(this, "건별보기", Toast.LENGTH_SHORT).show();
                sharedPreferenceUtil.put(OPTION,R.id.day_item);
                current_selected_menu = item.getItemId();
                updateMenuState();
                return true;
            case R.id.day_item_title:
                Toast.makeText(this, "내역보기", Toast.LENGTH_SHORT).show();
                sharedPreferenceUtil.put(OPTION,R.id.day_item_title);
                current_selected_menu = item.getItemId();
                updateMenuState();
                return true;


        }
        return super.onOptionsItemSelected(item);

    }

    public int getOptionIndex() {
        int optionDefalutIndex=0;
        int id = sharedPreferenceUtil.getValue(OPTION,R.id.day_sum);
        switch (id){
            case R.id.day_sum:
                return 0;
            case R.id.day_item:
                return  1;
            case R.id.day_item_title:
                return 2;
        }
        return optionDefalutIndex;
    }


    public interface SpinnerChangeListener {
        void onReceivedSpinnerPosition(int position, int sumType);
    }

    private SpinnerChangeListener mSpinnerChangeListener;

    public void setOnSpinnerListner(SpinnerChangeListener listner) {
        mSpinnerChangeListener = listner;
    }

    private  int mChildPos;

    @Override
    public void onRefresh(int parentPos, int childPos, ArrayList<UserTransactionData> dayList,String date) {
          FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.show(spentRecyclerFragment).commit();
            if(dayList ==null){
                dayList = new ArrayList<>();
            }

        Log.i("parentpos",parentPos+"");
        mChildPos = childPos;
            spentRecyclerFragment.setAdapter(dayList, date);
        calendarPresenter.onRefresh(parentPos, childPos);
//        mViewPager.setCurrentItem(parentPos+1);


    }

}
