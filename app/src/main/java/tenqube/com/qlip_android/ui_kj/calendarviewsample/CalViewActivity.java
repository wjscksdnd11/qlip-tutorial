package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.SearchData;
import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.ui_kj.calendarsample.SharedPreferenceUtil;
import tenqube.com.qlip_android.ui_kj.dialog.DeleteDialogFragment;
import tenqube.com.qlip_android.ui_kj.search.SearchActivity;

public class CalViewActivity extends AppCompatActivity implements DeleteDialogFragment.Callback {

    public static final int COUNT_PAGE = 36;
    private final static String[] spinner_menu = {"수입", "지출", "수입/지출", "제외내역"};
    public  int ACTIONBAR_MODE = 0;
    ViewPager pager;
    //    private AdapterFrgCalendar adapter;
    private CalViewPresenter mCalViewPresenter;
    private FilterAdapter mFilterAdapter;
    private Spinner mSpinner;
    private int mSpinnerType;
    private SharedPreferenceUtil sharedPreferenceUtil;
    public final static String OPTION = "option";
    public final static String DW_TYPE = "dw_type";
    private RecyclerView recyclerView;
    private ArrayList<SearchStringData> searchStringDataArrayList;
    private int current_selected_menu;
    private Menu main_menu;
    private  DeleteDialogFragment deleteDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_view);

        mCalViewPresenter = new CalViewPresenterimpl(this);
        pager = (ViewPager) findViewById(R.id.pager);
        recyclerView = (RecyclerView) findViewById(R.id.filter_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mFilterAdapter = new FilterAdapter();
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mFilterAdapter);
        recyclerView.setVisibility(View.GONE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() !=null)
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sharedPreferenceUtil = new SharedPreferenceUtil(this);
        int defalut_value = sharedPreferenceUtil.getValue(DW_TYPE, 1);
        mCalViewPresenter.initControl(pager, defalut_value, sharedPreferenceUtil.getValue(OPTION, 0));  //action init
        mCalViewPresenter.settingToolbar(toolbar);
        //spinner setting
        mSpinner = (Spinner) toolbar.findViewById(R.id.lv2_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, spinner_menu);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);
        mSpinnerType = defalut_value;
        mSpinner.setSelection(defalut_value);
        //spinner

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerType = position;
                sharedPreferenceUtil.put(DW_TYPE, position);
                mCalViewPresenter.changeOption(position, getOptionIndex());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ACTIONBAR_MODE == 1) {
                    clearActionBar();

                } else {
                    finish();
                }
            }
        });
    }
    public CalViewPresenter getPresenter(){
        if (mCalViewPresenter !=null){
        return mCalViewPresenter;
        }
        return null;
    }
    private void clearActionBar() {
        mCalViewPresenter.setTag(false);
        invalidateOptionsMenu();
        ACTIONBAR_MODE = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spinner_tabs, menu);
        int default_option = sharedPreferenceUtil.getValue(OPTION, R.id.day_sum);
        current_selected_menu = default_option;
        main_menu = menu;

        updateMenuState(default_option);
        return true;

    }

    public void updateMenuState(int id) {
        sharedPreferenceUtil.put(OPTION, id);
        if (main_menu == null) {
            return;
        }
        int sz = main_menu.size();
        for (int i = 0; i < sz; i++) {
            if (main_menu.getItem(i).getItemId() == current_selected_menu) {
                main_menu.getItem(i).setEnabled(false);
            } else {
                main_menu.getItem(i).setEnabled(true);
            }
        }

        mCalViewPresenter.changeOption(mSpinnerType, getOptionIndex());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (mCalViewPresenter.refreshTag()) {
            mSpinner.setVisibility(View.GONE);
//            spinnerTextView.setVisibility(View.GONE);
            menu.setGroupVisible(0, false);
            getMenuInflater().inflate(R.menu.menu_lv2_detail, menu);
            if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.green)));
            }
        } else {
            mSpinner.setVisibility(View.VISIBLE);
//            spinnerTextView.setVisibility(View.VISIBLE);
            menu.setGroupVisible(0, true);
            if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.qlip_main_dark_3)));
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ACTIONBAR_MODE == 0) {
            int id = item.getItemId();
            switch (id) {
                case R.id.search_button:
                    Toast.makeText(getApplicationContext(), "search_intent",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, SearchActivity.class);
                    startActivityForResult(intent, 1);
                    return true;

                case R.id.action_settings:
                    return true;

                case R.id.day_sum:
                    Toast.makeText(this, "합산보기", Toast.LENGTH_SHORT).show();
                    current_selected_menu = item.getItemId();
                    updateMenuState(R.id.day_sum);
                    return true;

                case R.id.day_item:
                    Toast.makeText(this, "건별보기", Toast.LENGTH_SHORT).show();
                    current_selected_menu = item.getItemId();
                    updateMenuState(R.id.day_item);
                    return true;

                case R.id.day_item_title:
                    Toast.makeText(this, "내역보기", Toast.LENGTH_SHORT).show();
                    current_selected_menu = item.getItemId();
                    updateMenuState(R.id.day_item_title);
                    return true;
            }
        } else {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_delete:
                    deleteDialogFragment = DeleteDialogFragment.newInstance();
                    deleteDialogFragment.show(getSupportFragmentManager(),"dialog");

                    return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (ACTIONBAR_MODE == 1) {
            clearActionBar();
        } else {
            super.onBackPressed();
        }
    }

    public int getOptionIndex() {
        int optionDefalutIndex = 0;
        int id = sharedPreferenceUtil.getValue(OPTION, R.id.day_sum);
        switch (id) {
            case R.id.day_sum:
                return 0;
            case R.id.day_item:
                return 1;
            case R.id.day_item_title:
                return 2;
        }

        return optionDefalutIndex;
    }

    public void onRemoveFilter( int position) {
        if (searchStringDataArrayList != null) {
            searchStringDataArrayList.remove(position);
            mCalViewPresenter.setSearchData(searchStringDataArrayList);
            mFilterAdapter.addAll(searchStringDataArrayList);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            SearchData searchData = (SearchData) data.getSerializableExtra(Constants.IntentParameter.SEARCH_DATA);
            this.searchStringDataArrayList = mCalViewPresenter.changeSearchData(searchData);
            mCalViewPresenter.setSearchData(searchStringDataArrayList);// 데이터 쿼리 부분
            if (recyclerView != null) {
                recyclerView.setVisibility(View.VISIBLE);
                mFilterAdapter.addAll(searchStringDataArrayList);// 필터 데이터 부분
            }
        }
    }

    @Override
    public void onEnterAsDelete() {
        if (deleteDialogFragment!=null) {
            mCalViewPresenter.removeIdentifier();
            clearActionBar();
            deleteDialogFragment.dismiss();
        }

    }

    public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        ArrayList<SearchStringData> keywordList = new ArrayList<>();

        public void addAll(ArrayList<SearchStringData> keywordList) {
            this.keywordList = keywordList;
            if (keywordList.size() == 0) {
                recyclerView.setVisibility(View.GONE);
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search_result_item, parent, false);
            return new FilterViewHolder(vFirst);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final FilterViewHolder filterViewHolder = (FilterViewHolder) holder;
            filterViewHolder.textView.setText(searchStringDataArrayList.get(position).searchName);
        }

        @Override
        public int getItemCount() {
            return keywordList.size();
        }
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

         FilterViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRemoveFilter(getAdapterPosition());
                }
            });
            textView = (TextView) itemView.findViewById(R.id.search_name);
        }
    }
}