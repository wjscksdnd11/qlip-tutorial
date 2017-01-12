package tenqube.com.qlip_android.ui_kj.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.CategoryCodeTableData;
import tenqube.com.qlip_android.data.MediumCategoryData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.ui_kj.dialog.DatePickerFragment;
import tenqube.com.qlip_android.ui_kj.dialog.DeleteDialogFragment;
import tenqube.com.qlip_android.ui_kj.dialog.TimePickerFragment;
import tenqube.com.qlip_android.ui_kj.select.category.SelectCategoryActivity;
import tenqube.com.qlip_android.util.DateUtil;
import tenqube.com.qlip_android.util.MoneyUtil;

import static tenqube.com.qlip_android.constans.Constants.DWType.DEPOSIT;
import static tenqube.com.qlip_android.constans.Constants.Repeat.REPEAT_EVERY_MONTH;
import static tenqube.com.qlip_android.constans.Constants.Repeat.REPEAT_EVERY_WEEK;
import static tenqube.com.qlip_android.constans.Constants.Repeat.REPEAT_NO;


//import com.tenqube.qlip.util.FusedLocationUtils;


public class DetailTranPresenterImpl implements DetailTranPresenter {

    private DetailTransactionActivity mActivity;
    private DetailTranModel mDetailTranModel;
    private DetailTranView mDetailtranView;
    private String mCategoryCode;
    private String mSpentDateStr = "";
    private String mSpentTimeStr = "";
    private String mLargeCategoryStr;
    private String mMediumCategoryStr;
    private String mFranchiseStr;
    private String mMemo;

    private double mSpentMoney;
    private long mIdentifier;
    private int mInstallmentCount;
    private int mRepeatType;
    public int iPos = -1;
    public int rPos = -1;
    private boolean mIsTouch;

    private UserTransactionData mSpendData = new UserTransactionData();
    private MediumCategoryData mMediumCategoryData;
    private CategoryCodeTableData mCategoryCodeTableData = new CategoryCodeTableData();
    private ArrayList<TagTableData> mSelectedTagList = new ArrayList<>();
    private CardTableData mCardData;
    private CardTableData mOriginCardData;
    private Calendar mCalendar = Calendar.getInstance();


    public DetailTranPresenterImpl(DetailTransactionActivity mActivity) {
        this.mActivity = mActivity;
        mDetailTranModel = new DetailTranModel(mActivity);
        mIsTouch = false;
    }


    @Override
    public void setView(DetailTranView view) {
        this.mDetailtranView=view;
    }

    @Override
    public void loadSpendData() {
        //초기 데이터를 셋팅하자
        mSpendData = mDetailTranModel.loadTransaction(mIdentifier);
        if(mSpendData == null){
            mActivity.finish();
            return;
        }
        mActivity.setTitle(mSpendData.dwType == Constants.DWType.WITHDRAW.ordinal()?
                R.string.withdraw_detail
                :
                R.string.deposit_detail);


        mCalendar = DateUtil.convertStringToCalendarFULL(mSpendData.spentDate);
        mCategoryCode = mSpendData.categoryCode;
        mFranchiseStr = mSpendData.fran;
        mSpentMoney = (long)mSpendData.spentMoney;
        mInstallmentCount = mSpendData.installmentCount;
        mRepeatType = mSpendData.repeatType;
        mMemo=mSpendData.memo!=null&&mSpendData.memo.contains(Constants.NONE)? "" : mSpendData.memo;

    }

    @Override
    public void loadCardData() {
        if(mSpendData != null){
            mOriginCardData = mDetailTranModel.loadCardData(mSpendData.cardId);
            mCardData = mOriginCardData;
        }
    }

    @Override
    public void loadCategoryData() {
        if(mSpendData != null){
            mCategoryCodeTableData = mDetailTranModel.loadCategoryData(mSpendData.categoryCode);
            mLargeCategoryStr = mCategoryCodeTableData.largeCategory;
            mMediumCategoryStr = mCategoryCodeTableData.mediumCategory;
        }
    }

    @Override
    public void loadSpentDate() {
        if(mSpendData != null){
            Calendar calendar = DateUtil.convertStringToCalendarFULL(mSpendData.spentDate);
            mSpentDateStr = DateUtil.getStringDateAsYYYYMMdd(calendar);
            mSpentTimeStr = DateUtil.getStringDateAsHHmmss(calendar);
            mDetailtranView.setSpentDateTextView(DateUtil.getStringDetailDate(calendar));
            mDetailtranView.setSpentTimeTextView(DateUtil.getStringDetailTime(calendar));
        }

    }

    @Override
    public void initView() {
        if(mSpendData != null){
            mDetailtranView.setLargeCategoryView(mCategoryCode, mLargeCategoryStr);
            mDetailtranView.setMediumCategoryTextView(mMediumCategoryStr);
            mDetailtranView.setKeywordEditText(mSpendData.keyword);
            mDetailtranView.setFranchiseTextView(mFranchiseStr == null ||
                    mFranchiseStr.equals("none") ||
                    mFranchiseStr.equals("") ?
                    ""
                    :
                    "'" + mFranchiseStr + "'(으)로 분류");

            mDetailtranView.setSpentMoneyTextView(MoneyUtil.converThreeCommaNotUnit(mSpentMoney));
            mDetailtranView.setCardNameTextView(mCardData.changeCardName);

            if(mSpendData.spentMoney < 0 ){
                mDetailtranView.setInstallmentTextView("취소");
                mDetailtranView.setVisibleInstallmentArrow(View.GONE);
            }else{
                mDetailtranView.setVisibleInstallmentArrow(View.VISIBLE);
                mDetailtranView.setInstallmentTextView(mInstallmentCount == Constants.INSTALLMENT_DEFAULT ? mActivity.getString(R.string.user_add_transaction_not_installment) : mActivity.getString(R.string.detail_tran_installment_months, mInstallmentCount));
            }

            if(mRepeatType == REPEAT_NO.ordinal()){
                mDetailtranView.setRepeatTextView(mActivity.getString(R.string.repeat_setting), ContextCompat.getColor(mActivity, R.color.qlip_sub_text));
            }else if(mRepeatType == REPEAT_EVERY_MONTH.ordinal()){
                mDetailtranView.setRepeatTextView(mActivity.getString(R.string.repeat_every_month), ContextCompat.getColor(mActivity, R.color.qlip_main_green));
            }
            else{
                mDetailtranView.setRepeatTextView(mActivity.getString(R.string.repeat_every_week), ContextCompat.getColor(mActivity, R.color.qlip_main_green));
            }
            mDetailtranView.setMemoEditText(mMemo);

            if(mSpendData.dwType == DEPOSIT.ordinal()){
                mDetailtranView.setVisibleFranchise(View.GONE);
                mDetailtranView.setVisibleInstallment(View.GONE);
                mDetailtranView.setVisibleMediumCategory(View.GONE);
            }
        }

    }


    @Override
    public void receiveIntentValue() {
        mIdentifier = mActivity.getIntent().getLongExtra(Constants.IntentParameter.IDENTIFIER,0);
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
    public void goSelectCategoryActivity() {
        Intent intent=new Intent(mActivity, SelectCategoryActivity.class);
        intent.putExtra(Constants.IntentParameter.WHERE_NUM, 1);
        intent.putExtra(Constants.IntentParameter.DW_TYPE_CODE, mSpendData.dwType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        mActivity.startActivityForResult(intent, Constants.RequestCode.SELECT_CATEGORY);
    }

    public int mPos = -1;
    @Override
    public void showMediumCategoryDialog() {

        mMediumCategoryData = mDetailTranModel.getMediumCategoryList(mCategoryCode.substring(0,2));

        if(mPos == -1) mPos =mDetailTranModel.getMediumCategoryPos(mMediumCategoryData.mediumCategoryList, mMediumCategoryStr);

        new AlertDialog.Builder(mActivity, R.style.DialogTheme)
                .setSingleChoiceItems(mMediumCategoryData.mediumCategoryList, mPos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPos = i;
                        if(!mCategoryCodeTableData.mediumCategory.equals(mMediumCategoryData.mediumCategoryList[i])){
                            mFranchiseStr = Constants.NONE;
                            mDetailtranView.setFranchiseTextView("");
                        }else{
                            mFranchiseStr = mSpendData.fran;
                            mDetailtranView.setFranchiseTextView(mFranchiseStr == null || mFranchiseStr.equals("none") || mFranchiseStr.equals("") ? "" : "'" + mFranchiseStr + "'(으)로 분류");
                        }
                        mMediumCategoryStr = mMediumCategoryData.mediumCategoryList[i];
                        mDetailtranView.setMediumCategoryTextView(mMediumCategoryStr);
                        mCategoryCode = mMediumCategoryData.categoryCodeList[i];


                        dialogInterface.dismiss();
                    }
                })
                .show();
    }



    @Override
    public void showInstallmentDialog() {
        if(mSpendData.spentMoney >= 0){

            final String[] installmentItems = new String[36];

            for(int i = 0; i<installmentItems.length; i++){
                if(i==0){
                    installmentItems[i] = mActivity.getString(R.string.user_add_transaction_not_installment);
                }else{
                    installmentItems[i] = (i+1)+"개월";
                }
            }
            if(iPos == -1) iPos = mSpendData.installmentCount == 0?0:mSpendData.installmentCount - 1;

            new AlertDialog.Builder(mActivity, R.style.DialogTheme)
                    .setSingleChoiceItems(installmentItems, iPos, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            iPos = i;
                            if(i == 0){
                                mInstallmentCount = Constants.INSTALLMENT_DEFAULT;
                            }else{
                                mInstallmentCount = i+1;
                            }
                            mDetailtranView.setInstallmentTextView(installmentItems[i]);

                            if(mInstallmentCount != 0){
                                mRepeatType = REPEAT_NO.ordinal();
                                mDetailtranView.setRepeatTextView(mActivity.getString(R.string.repeat_setting), ContextCompat.getColor(mActivity,R.color.qlip_sub_text));
                            }
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }

    }


    @Override
    public void showRepeatDialog() {
        Calendar tempCalendar= DateUtil.convertStringToCalendarFULL(mSpentDateStr + " " + mSpentTimeStr);
        int year = tempCalendar.get(Calendar.YEAR);
        int month = tempCalendar.get(Calendar.MONTH);
        int day = tempCalendar.get(Calendar.DATE);

        int todayYear = Calendar.getInstance().get(Calendar.YEAR);
        int todayMonth = Calendar.getInstance().get(Calendar.MONTH);
        int todayDate = Calendar.getInstance().get(Calendar.DATE);

        if(mInstallmentCount > Constants.INSTALLMENT_DEFAULT){
            Toast.makeText(mActivity, mActivity.getString(R.string.user_add_transaction_msg_installment), Toast.LENGTH_SHORT).show();
        }else{
            if( year<todayYear||
                (year == todayYear&&month<todayMonth)||
                (year == todayYear&&month==todayMonth&&day<todayDate)
            ){
                Toast.makeText(mActivity,mActivity.getString(R.string.user_add_transaction_msg_repeat), Toast.LENGTH_SHORT).show();
            }

            if(rPos == -1) rPos = mSpendData.repeatType;
            final String[] repeatItems = new String[]{
                    mActivity.getString(R.string.repeat_no),
                    mActivity.getString(R.string.repeat_every_month),
                    mActivity.getString(R.string.repeat_every_week)};

            new AlertDialog.Builder(mActivity, R.style.DialogTheme)
                    .setSingleChoiceItems(repeatItems, rPos, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rPos = i;
                            switch (i){
                                case 0:
                                    mRepeatType = REPEAT_NO.ordinal();
                                    mDetailtranView.setRepeatTextView(repeatItems[i], ContextCompat.getColor(mActivity,R.color.qlip_sub_text));
                                    break;
                                case 1:
                                    mRepeatType = REPEAT_EVERY_MONTH.ordinal();
                                    mDetailtranView.setRepeatTextView(repeatItems[i], ContextCompat.getColor(mActivity,R.color.qlip_main_green));
                                    break;

                                default:
                                    mRepeatType = REPEAT_EVERY_WEEK.ordinal();
                                    mDetailtranView.setRepeatTextView(repeatItems[i], ContextCompat.getColor(mActivity,R.color.qlip_main_green));
                                    break;
                            }
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
    }



    @Override
    public void confirm() {


        if (mSpendData == null || (
            mSpendData.keyword.equals(mActivity.mKeywordEditText.getText().toString()) &&
            mCategoryCode.equals(mSpendData.categoryCode) &&
            mSpentMoney == (long)mSpendData.spentMoney &&
            mRepeatType == mSpendData.repeatType &&
            mOriginCardData.changeCardName.equals(mCardData.changeCardName) &&
            mOriginCardData.changeCardType==mCardData.changeCardType &&
            (mSpentDateStr + " " + mSpentTimeStr).equals(mSpendData.spentDate) &&
            mInstallmentCount == mSpendData.installmentCount &&
            mMemo.equals(mActivity.mMemoEditText.getText().toString())
            )) {
            mActivity.finish();
        }

        mSpendData.spentDate = mSpentDateStr+" "+mSpentTimeStr;
        if(mActivity.getString(R.string.user_add_transaction_medium_category_default).equals(mMediumCategoryStr)){
            mSpendData.isUserUpdate = 2;
            mCategoryCode = mCategoryCode.length()>1?mCategoryCode.substring(0,2)+"1010": mSpendData.dwType== DEPOSIT.ordinal()?Constants.CategoryCode.DEPOSIT_ETC_STR:Constants.CategoryCode.UNCATEGORY_STR;
        }else{
            mSpendData.isUserUpdate = 1;
            mCategoryCode = mCategoryCode.length()>3?mCategoryCode.substring(0, 4)+"10":mSpendData.dwType==  DEPOSIT.ordinal()?Constants.CategoryCode.DEPOSIT_ETC_STR:Constants.CategoryCode.UNCATEGORY_STR;
        }
        makeTransaction();
        receiveServer();

    }

    private void makeTransaction(){

        mSpendData.categoryCode = mCategoryCode;
        mSpendData.categoryConfigId = mDetailTranModel.getCategoryConfigId(mSpendData.categoryCode);
        mSpendData.keyword = TextUtils.isEmpty(mActivity.mKeywordEditText.getText().toString().replace(" ","").replace("\n",""))?
                "내용없음"
                :
                mActivity.mKeywordEditText.getText().toString();
        mSpendData.memo = mActivity.mMemoEditText.getText().toString();
        mSpendData.repeatType = mRepeatType;
        mSpendData.installmentCount = mInstallmentCount;
        mSpendData.spentMoney = mSpentMoney;
        mSpendData.spentDate = mSpentDateStr+" "+mSpentTimeStr;
        mSpendData.spentLatitude = -1;
        mSpendData.spentLongitude = -1;
        mSpendData.cardId = mCardData.cardId;
        mSpendData.cardName = mCardData.totalCardName;
        mSpendData.cardType = mCardData.totalCardType;
        mSpendData.totalSum = mCardData.totalSum;

        mSelectedTagList = mDetailTranModel.getSelectedTagList(mActivity.mTagAdapter.hashTagList);
        mSpendData.tagList = mDetailTranModel.getHashTagNameList(mSelectedTagList);


    }

    private void receiveServer() {
        mDetailTranModel.deleteTransaction(mSpendData.identifier);
        mSpendData = mDetailTranModel.insertUserAddTran(mSpendData);
        if (!mSelectedTagList.isEmpty())
            mDetailTranModel.insertHashTag(mSpendData.identifier, mSelectedTagList);
        doEnd();
    }


    private void doEnd(){
        mIsTouch = false;
        mActivity.finish();
    }


    @Override
    public void goDatePicker(){
        DialogFragment newFragment = DatePickerFragment.newInstance(mCalendar);
        newFragment.show(mActivity.getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void goTimePicker(){
        DialogFragment newFragment = TimePickerFragment.newInstance(mCalendar);
        newFragment.show(mActivity.getSupportFragmentManager(), "Time Picker");
    }


    @Override
    public void onRepeatCancel() {
        mRepeatType= REPEAT_NO.ordinal();
        mDetailtranView.setRepeatTextView(mActivity.getString(R.string.repeat_setting), ContextCompat.getColor(mActivity, R.color.qlip_sub_text));

    }

    @Override
    public void onCalendar(Calendar calendar) {
        if(calendar != null ){
            this.mCalendar = calendar;
            mDetailtranView.setSpentDateTextView(DateUtil.getStringDetailDate(calendar));
            mSpentDateStr = DateUtil.getStringDateAsYYYYMMdd(calendar);
        }
    }

    @Override
    public void onTimeCalendar(Calendar calendar) {
        if(calendar != null ){
            this.mCalendar = calendar;
            mDetailtranView.setSpentTimeTextView(DateUtil.getStringDetailTime(calendar));
            mSpentTimeStr = DateUtil.getStringDateAsHHmmss(calendar);
        }

    }


    @Override
    public void receiverSpentMoneyData(double spentMoney) {
        mSpentMoney=spentMoney;
        mDetailtranView.setSpentMoneyTextView(MoneyUtil.converThreeCommaNotUnit(mSpentMoney));

    }



    @Override
    public void receiveCategoryData(String categoryCode, String largeCategoryName) {

        mFranchiseStr = Constants.NONE;
        mDetailtranView.setFranchiseTextView("");
        mCategoryCode = categoryCode;
        mLargeCategoryStr = largeCategoryName;
        mMediumCategoryStr = mActivity.getString(R.string.user_add_transaction_medium_category_default);
        mDetailtranView.setLargeCategoryView(categoryCode,largeCategoryName);
        mDetailtranView.setMediumCategoryTextView(mMediumCategoryStr);

    }



    @Override
    public void showDeleteDialog() {
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
        Fragment prev = mActivity.getSupportFragmentManager().findFragmentByTag("dialog_delete");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment deleteDialogFrag = DeleteDialogFragment.newInstance();
        deleteDialogFrag.show(ft, "dialog_delete");

    }

    @Override
    public void deleteTransaction() {
        if(!mIsTouch){
            mIsTouch = true;
            mDetailTranModel.deleteTransaction(mSpendData.identifier);
            doEnd();
        }
    }


    @Override
    public ArrayList<TagTableData> loadTagList() {
        if(mSpendData!=null){
            return mDetailTranModel.loadTagList(mSpendData.identifier);
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public long insertHashTagName(String tagName) {
        return mDetailTranModel.insertHashTagName(tagName);
    }


}
