package tenqube.com.qlip_android.ui_kj.detail;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.ui_kj.dialog.DatePickerFragment;
import tenqube.com.qlip_android.ui_kj.dialog.DeleteDialogFragment;
import tenqube.com.qlip_android.ui_kj.dialog.TimePickerFragment;
import tenqube.com.qlip_android.util.CategoryUtil;
import tenqube.com.qlip_android.util.ValidationUtil;


public class DetailTransactionActivity extends BaseActivity implements View.OnClickListener,

        DetailTranPresenter.DetailTranView,
        DatePickerFragment.Callback,
        TimePickerFragment.Callback,
        DeleteDialogFragment.Callback{

    private static final String SCREEN_VIEW="Detail_Transaction_Activity";

    private DetailTranPresenter mDetailTranPresenter;
    private ImageView mLargeCategoryImageView;
    private ImageView mInstallmentArrowImageView;
    private TextView mLargeCategoryTextView;
    private TextView mMediumCategoryTextView;
    private TextView mFranchiseTextView;
    private TextView mSpentMoneyTextView;
    private TextView mCardNameTextView;
    private TextView mInstallmentTextView;
    private TextView mSpentDateTextView;
    private TextView mSpentTimeTextView;
    private TextView mRepeatTextView;
    public EditText mKeywordEditText;
    public EditText mMemoEditText;

    private LinearLayout mMediumCategoryLinear;
    private FrameLayout mFranchiseFrame;
    private FrameLayout mInstallmentFrame;
    private InputMethodManager imm;

    public TagAdapter mTagAdapter;
    private RecyclerView mTagRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isFinishing()) {
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_transaction);

        int inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED;
        int inputMode2 = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
        getWindow().setSoftInputMode(inputMode | inputMode2);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        mDetailTranPresenter = new DetailTranPresenterImpl(DetailTransactionActivity.this);
        mDetailTranPresenter.setView(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar=getActionBarToolbar(toolbar);
        mDetailTranPresenter.settingToolbar(toolbar);

        mLargeCategoryImageView = (ImageView)findViewById(R.id.largeCategoryImg);
        mLargeCategoryTextView = (TextView)findViewById(R.id.largeCategoryText);
        mMediumCategoryTextView = (TextView)findViewById(R.id.mediumCategoryText);
        mMediumCategoryLinear = (LinearLayout)findViewById(R.id.mediumCategoryLinear);
        mFranchiseFrame = (FrameLayout)findViewById(R.id.franchiseFrame);
        mInstallmentFrame = (FrameLayout)findViewById(R.id.installmentFrame);
        mKeywordEditText = (EditText)findViewById(R.id.keywordEdit);
        mMemoEditText = (EditText)findViewById(R.id.memoEdit);
        mFranchiseTextView = (TextView)findViewById(R.id.franchiseText);
        mSpentMoneyTextView = (TextView)findViewById(R.id.spentMoneyText);
        mCardNameTextView = (TextView)findViewById(R.id.cardNameText);
        mInstallmentTextView = (TextView)findViewById(R.id.installmentText);
        mSpentDateTextView = (TextView)findViewById(R.id.spentDateTextView);
        mSpentTimeTextView = (TextView)findViewById(R.id.spentTimeTextView);
        mRepeatTextView = (TextView)findViewById(R.id.repeatText);
        mInstallmentArrowImageView = (ImageView)findViewById(R.id.installment_arrow);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailTransactionActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mTagRecyclerView = (RecyclerView) findViewById(R.id.tag_recycler_view);
        mTagRecyclerView.setLayoutManager(linearLayoutManager);
        mTagRecyclerView.setHasFixedSize(true);
        mTagAdapter = new TagAdapter(DetailTransactionActivity.this, mDetailTranPresenter);
        mTagRecyclerView.setAdapter(mTagAdapter);


        initKeywordEditTextView();
        initMemoEditTextView();

        //
        setOnClickListener(mSpentDateTextView,this);
        setOnClickListener(mSpentTimeTextView,this);
        setOnClickListener(mMediumCategoryLinear,this);
        setOnClickListener(findViewById(R.id.largeCategoryLinear),this);
        setOnClickListener(findViewById(R.id.webSearchTextView),this);
        setOnClickListener(findViewById(R.id.spentMoneyLinear),this);
        setOnClickListener(findViewById(R.id.cardNameLinear),this);
        setOnClickListener(findViewById(R.id.installmentFrame),this);
        setOnClickListener(findViewById(R.id.repeatFrame),this);
        setOnClickListener(findViewById(R.id.tagLinear),this);
        setOnClickListener(findViewById(R.id.confirm),this);

        mDetailTranPresenter.receiveIntentValue();
        mDetailTranPresenter.loadSpendData();
        mDetailTranPresenter.loadCardData();
        mDetailTranPresenter.loadCategoryData();
        mDetailTranPresenter.loadSpentDate();
        mDetailTranPresenter.initView();

        new LoadTagTask().execute();

    }



    private void initKeywordEditTextView(){
        if(mKeywordEditText!=null)mKeywordEditText.setFilters(new InputFilter[]{ValidationUtil.filter,new InputFilter.LengthFilter(50)});

        mKeywordEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeywordEditText.setFocusable(true);
                mKeywordEditText.setCursorVisible(true);
                mKeywordEditText.requestFocus();
                return false;
            }
        });

        mKeywordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 6) {
                    mKeywordEditText.setCursorVisible(false);
                    return false;
                }
                return false;
            }
        });

        mKeywordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    mKeywordEditText.setHint("");

            }
        });
    }

    private void initMemoEditTextView(){
        if(mMemoEditText!=null)mMemoEditText.setFilters(new InputFilter[]{ValidationUtil.filter,new InputFilter.LengthFilter(100)});

        mMemoEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mMemoEditText.setCursorVisible(false);
                    return false;
                }
                return false;
            }
        });

        mMemoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    mMemoEditText.setHint("");

            }
        });

        mMemoEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mMemoEditText.setFocusable(true);
                mMemoEditText.setCursorVisible(true);
                mMemoEditText.requestFocus();
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {
        imm.hideSoftInputFromWindow(mKeywordEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mMemoEditText.getWindowToken(), 0);

        switch (view.getId()){
            case R.id.spentTimeTextView:
                mDetailTranPresenter.goTimePicker();
                break;

            case R.id.spentDateTextView:
                mDetailTranPresenter.goDatePicker();
                break;
            case R.id.largeCategoryLinear:
                mDetailTranPresenter.goSelectCategoryActivity();
                break;
            case R.id.mediumCategoryLinear:
                mDetailTranPresenter.showMediumCategoryDialog();
                break;
            case R.id.installmentFrame:
                mDetailTranPresenter.showInstallmentDialog();
                break;
            case R.id.repeatFrame:
                mDetailTranPresenter.showRepeatDialog();
                break;
            case R.id.confirm:
                mDetailTranPresenter.confirm();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lv2_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_delete:
                mDetailTranPresenter.showDeleteDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLargeCategoryView(String categoryCode, String largeCategoryName) {
        if(categoryCode != null && largeCategoryName != null){
            mLargeCategoryImageView.setImageResource(CategoryUtil.getIconImage(Integer.parseInt(categoryCode.substring(0, 2))));
            mLargeCategoryTextView.setText(largeCategoryName);
        }

    }

    @Override
    public void setMediumCategoryTextView(String mediumCategory) {
        if(mediumCategory != null)
            mMediumCategoryTextView.setText(mediumCategory);
    }

    @Override
    public void setFranchiseTextView(String franchise) {
        if(franchise != null)
            mFranchiseTextView.setText(franchise);
    }

    @Override
    public void setSpentMoneyTextView(String spentMoney) {

        if(spentMoney != null)
            mSpentMoneyTextView.setText(spentMoney);
    }

    @Override
    public void setCardNameTextView(String cardName) {

        if(cardName != null)
            mCardNameTextView.setText(cardName);
    }

    @Override
    public void setInstallmentTextView(String installment) {
        if(installment!=null)
            mInstallmentTextView.setText(installment);
    }

    @Override
    public void setSpentDateTextView(String spentDate) {

        if(spentDate != null && mSpentDateTextView != null)
            mSpentDateTextView.setText(spentDate);
    }

    @Override
    public void setSpentTimeTextView(String spentTime) {
        if(spentTime!=null)
            mSpentTimeTextView.setText(spentTime);
    }

    @Override
    public void setRepeatTextView(String repeat, int color) {
        if(repeat != null){
            mRepeatTextView.setText(repeat);
            mRepeatTextView.setTextColor(color);
        }

    }

    @Override
    public void setMemoEditText(String memo) {

        if(memo != null)
            mMemoEditText.setText(memo);
    }

    @Override
    public void setKeywordEditText(String keyword) {

        if(keyword != null)
            mKeywordEditText.setText(keyword);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_MONEY:
                if (resultCode == RESULT_OK) {
                    mDetailTranPresenter.receiverSpentMoneyData(data.getDoubleExtra(Constants.IntentParameter.SPENT_MONEY,0));
                }
                break;


            case Constants.RequestCode.SELECT_CATEGORY:
                if (resultCode == RESULT_OK) {
                    mDetailTranPresenter.receiveCategoryData(data.getStringExtra(Constants.IntentParameter.CATEGORY_CODE), data.getStringExtra(Constants.IntentParameter.CATEGORY));
                }
                break;


        }
    }

    @Override
    public void setVisibleFranchise(int visible) {
        mFranchiseFrame.setVisibility(visible);
    }

    @Override
    public void setVisibleMediumCategory(int visible) {
        mMediumCategoryLinear.setVisibility(visible);
    }

    @Override
    public void setVisibleInstallment(int visible) {

        mInstallmentFrame.setVisibility(visible);
    }

    @Override
    public void setVisibleInstallmentArrow(int visible) {
        mInstallmentArrowImageView.setVisibility(visible);
    }

    @Override
    public void onRepeatCancel() {
        mDetailTranPresenter.onRepeatCancel();
    }

    @Override
    public void onCalendar(Calendar calendar) {
        mDetailTranPresenter.onCalendar(calendar);
    }

    @Override
    public void onTimeCalendar(Calendar calendar) {
        mDetailTranPresenter.onTimeCalendar(calendar);
    }

    @Override
    public void onEnterAsDelete() {
        mDetailTranPresenter.deleteTransaction();
    }


    public class LoadTagTask extends AsyncTask<Void,Void, ArrayList<TagTableData>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TagTableData> doInBackground(Void... params) {
            return mDetailTranPresenter.loadTagList();
        }

        @Override
        protected void onPostExecute(ArrayList<TagTableData> tagList) {
            super.onPostExecute(tagList);
            mTagAdapter.addAll(tagList);
        }
    }



}



