package tenqube.com.qlip_android.ui_kj.search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.SearchData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.util.CategoryUtil;
import tenqube.com.qlip_android.util.Utils;
import tenqube.com.qlip_android.util.ValidationUtil;

import static tenqube.com.qlip_android.constans.Constants.DWType.DEPOSIT;
import static tenqube.com.qlip_android.constans.Constants.DWType.WITHDRAW;


public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private static final String SCREEN_LABEL = "Search_Activity";
    private SearchPresenter searchPresenter;
    public EditText searchEditText;
    public ArrayList<UserCategoryConfigTableData> withdrawCategoryList = new ArrayList<>();
    public ArrayList<UserCategoryConfigTableData> depositCategoryList = new ArrayList<>();

    public ArrayList<CardTableData> cardList = new ArrayList<>();
    public ArrayList<CardTableData> bankList = new ArrayList<>();
    public ArrayList<TagTableData> hashList = new ArrayList<>();

    private LinearLayout usdContainer;
    private LinearLayout domesticContainer;
    private LinearLayout installmentContainer;
    private LinearLayout repeatContainer;

    private SearchData searchData;

    private boolean usdFlag;
    private boolean domesticFlag;
    private boolean installmentFlag;
    private boolean repeatFlag;
    private int menuType;
    private TextView mWithdrawTitleTextView;
    private TextView mDepositTitleTextView;
    private RecyclerView mWithdrawCateRecyclerView;
    private RecyclerView mDepositCateRecyclerView;

    private InputMethodManager imm = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isFinishing()) {
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("");
        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = getActionBarToolbar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel();
            }
        });

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_black_lv0));
        menuType = getIntent().getIntExtra(Constants.IntentParameter.MENU_TYPE, Constants.MenuType.MENU_WITHDRAW_DEPOSIT.ordinal());

        searchData = new SearchData();
        searchEditText = (EditText) toolbar.findViewById(R.id.toolbar_edit_text);
        searchEditText.setHint(R.string.search_edit_text_hint);
        if (searchEditText != null)
            searchEditText.setFilters(new InputFilter[]{ValidationUtil.filter});

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onEnter();
                }
                return false;
            }
        });

        searchPresenter = new SearchPresenterImpl(SearchActivity.this);

        mWithdrawCateRecyclerView = (RecyclerView) findViewById(R.id.withdraw_category_rv);
        mDepositCateRecyclerView = (RecyclerView) findViewById(R.id.deposit_category_rv);

        mWithdrawTitleTextView = (TextView) findViewById(R.id.withdraw_title);
        mDepositTitleTextView = (TextView) findViewById(R.id.deposit_title);

        initCategory();

        RecyclerView cardRv = (RecyclerView) findViewById(R.id.card_rv);
        if (cardRv != null) {
            BlockScrollGridLayoutManager gridLayoutManager = new BlockScrollGridLayoutManager(getApplicationContext(), 3);
            cardRv.setLayoutManager(gridLayoutManager);
            cardRv.setHasFixedSize(true);

            cardList = searchPresenter.loadCardList(true);
            SearchCardAdapter searchCardAdapter = new SearchCardAdapter();
            cardRv.setAdapter(searchCardAdapter);
            searchCardAdapter.addAll();
            cardRv.getLayoutParams().height = cardList.isEmpty() ?
                    0
                    :
                    Utils.dpToPx(50) * ((int) Math.ceil(cardList.size() / 3f));

        }

        RecyclerView bankRv = (RecyclerView) findViewById(R.id.bank_rv);
        if (bankRv != null) {
            BlockScrollGridLayoutManager gridLayoutManager = new BlockScrollGridLayoutManager(getApplicationContext(), 3);
            bankRv.setLayoutManager(gridLayoutManager);
            bankRv.setHasFixedSize(true);

            bankList = searchPresenter.loadCardList(false);
            SearchBankAdapter searchCardAdapter = new SearchBankAdapter();
            bankRv.setAdapter(searchCardAdapter);
            searchCardAdapter.addAll();
            bankRv.getLayoutParams().height = bankList.isEmpty() ?
                    0
                    :
                    Utils.dpToPx(50) * ((int) Math.ceil(bankList.size() / 3f));

        }

        RecyclerView hashRv = (RecyclerView) findViewById(R.id.tag_rv);
        if (hashRv != null) {
            BlockScrollGridLayoutManager gridLayoutManager = new BlockScrollGridLayoutManager(getApplicationContext(), 3);
            hashRv.setLayoutManager(gridLayoutManager);
            hashRv.setHasFixedSize(true);
            hashList = searchPresenter.loadHashTagList();
            SearchHashTagAdapter searchHashTagAdapter = new SearchHashTagAdapter();
            hashRv.setAdapter(searchHashTagAdapter);
            searchHashTagAdapter.addAll();
            hashRv.getLayoutParams().height = hashList.isEmpty() ?
                    0
                    :
                    Utils.dpToPx(50) * ((int) Math.ceil(hashList.size() / 3f));

        }

        usdContainer = (LinearLayout) findViewById(R.id.usd_container);
        setOnClickListener(usdContainer, this);
        domesticContainer = (LinearLayout) findViewById(R.id.domestic_container);
        setOnClickListener(domesticContainer, this);
        installmentContainer = (LinearLayout) findViewById(R.id.installment_container);
        setOnClickListener(installmentContainer, this);

        repeatContainer = (LinearLayout) findViewById(R.id.repeat_container);
        setOnClickListener(repeatContainer, this);

    }

    protected void setOnClickListener(View v, View.OnClickListener listener) {
        if (v != null)
            v.setOnClickListener(listener);
    }

    private void initCategory() {
        mWithdrawCateRecyclerView.setVisibility(View.GONE);
        mDepositCateRecyclerView.setVisibility(View.GONE);
        if(menuType == Constants.MenuType.MENU_DEPOSIT.ordinal()){
            mWithdrawTitleTextView.setVisibility(View.GONE);
            mDepositTitleTextView.setVisibility(View.VISIBLE);
            setDepositCategory();
        }else if(menuType == Constants.MenuType.MENU_WITHDRAW.ordinal()){
            mWithdrawTitleTextView.setVisibility(View.VISIBLE);
            mDepositTitleTextView.setVisibility(View.GONE);
            setWithdrawCategory();
        }else{
            mWithdrawTitleTextView.setVisibility(View.VISIBLE);
            mDepositTitleTextView.setVisibility(View.VISIBLE);
            setWithdrawCategory();
            setDepositCategory();
        }

    }

    private void setDepositCategory() {
        if (mDepositCateRecyclerView != null) {
            mDepositCateRecyclerView.setVisibility(View.VISIBLE);
            BlockScrollGridLayoutManager gridLayoutManager = new BlockScrollGridLayoutManager(getApplicationContext(), 3);
            mDepositCateRecyclerView.setLayoutManager(gridLayoutManager);
            mDepositCateRecyclerView.setHasFixedSize(true);
            depositCategoryList = searchPresenter.loadCategoryList(DEPOSIT.ordinal());
            SearchDepositCategoryAdapter searchCategoryAdapter = new SearchDepositCategoryAdapter();
            mDepositCateRecyclerView.setAdapter(searchCategoryAdapter);
            searchCategoryAdapter.addAll();
            mDepositCateRecyclerView.getLayoutParams().height = depositCategoryList.isEmpty() ?
                    0
                    :
                    Utils.dpToPx(50) * ((int) Math.ceil(depositCategoryList.size() / 3f));
        }
    }

    private void setWithdrawCategory() {
        if (mWithdrawCateRecyclerView != null) {
            mWithdrawCateRecyclerView.setVisibility(View.VISIBLE);
            BlockScrollGridLayoutManager gridLayoutManager = new BlockScrollGridLayoutManager(getApplicationContext(), 3);
            mWithdrawCateRecyclerView.setLayoutManager(gridLayoutManager);
            mWithdrawCateRecyclerView.setHasFixedSize(true);
            withdrawCategoryList = searchPresenter.loadCategoryList(WITHDRAW.ordinal());
            SearchWithdrawCategoryAdapter searchCategoryAdapter = new SearchWithdrawCategoryAdapter();
            mWithdrawCateRecyclerView.setAdapter(searchCategoryAdapter);
            searchCategoryAdapter.addAll();
            mWithdrawCateRecyclerView.getLayoutParams().height = withdrawCategoryList.isEmpty() ? 0 : Utils.dpToPx(50) * ((int) Math.ceil(withdrawCategoryList.size() / 3f));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.domestic_container:
                if (domesticFlag) {
                    domesticContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_dark_3));
                    domesticFlag = false;
                } else {
                    domesticContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_green));
                    domesticFlag = true;
                }
                break;
            case R.id.usd_container:
                if (usdFlag) {
                    usdContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_dark_3));
                    usdFlag = false;
                } else {
                    usdContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_green));
                    usdFlag = true;
                }
                break;
            case R.id.installment_container:
                if (installmentFlag) {
                    installmentContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_dark_3));
                    installmentFlag = false;
                } else {
                    installmentContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_green));
                    installmentFlag = true;
                }
                break;

            case R.id.repeat_container:
                if (repeatFlag) {
                    repeatContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_dark_3));
                    repeatFlag = false;
                } else {
                    repeatContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.qlip_main_green));
                    repeatFlag = true;
                }
                break;

        }

    }





    public class SearchWithdrawCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public SearchWithdrawCategoryAdapter() {
        }

        public void addAll() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return withdrawCategoryList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search_item, viewGroup, false);
            return new CategoryViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CategoryViewHolder nHolder0 = (CategoryViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(CategoryUtil.getIconImage(Integer.parseInt(withdrawCategoryList.get(position).categoryCode.substring(0, 2))));
            nHolder0.categoryNameTextView.setText(withdrawCategoryList.get(position).largeCategory);
            nHolder0.categoryLinearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), withdrawCategoryList.get(position).check ? R.color.qlip_main_green : R.color.qlip_main_dark_3));

        }


        public class CategoryViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CategoryViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() != -1) {
                            withdrawCategoryList.get(getAdapterPosition()).check = !withdrawCategoryList.get(getAdapterPosition()).check;
                            notifyDataSetChanged();
                        }

                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }


    }

    public class SearchDepositCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public SearchDepositCategoryAdapter() {
        }

        public void addAll() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return depositCategoryList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search_item, viewGroup, false);
            return new CategoryViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CategoryViewHolder nHolder0 = (CategoryViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(CategoryUtil.getIconImage(Integer.parseInt(depositCategoryList.get(position).categoryCode.substring(0, 2))));
            nHolder0.categoryNameTextView.setText(depositCategoryList.get(position).largeCategory);
            nHolder0.categoryLinearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), depositCategoryList.get(position).check ? R.color.qlip_main_green : R.color.qlip_main_dark_3));

        }


        public class CategoryViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CategoryViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() != -1) {
                            depositCategoryList.get(getAdapterPosition()).check = !depositCategoryList.get(getAdapterPosition()).check;
                            notifyDataSetChanged();
                        }

                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }


    }

    public class SearchCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public SearchCardAdapter() {
        }

        public void addAll() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return cardList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search_item, viewGroup, false);
            return new CardViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CardViewHolder nHolder0 = (CardViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(R.drawable.ic_card);
            nHolder0.categoryNameTextView.setText(cardList.get(position).changeCardName);
            nHolder0.categoryLinearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), cardList.get(position).flag ? R.color.qlip_main_green : R.color.qlip_main_dark_3));
            nHolder0.categoryNameTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), cardList.get(position).exceptType == 1 ? R.color.qlip_sub_text : R.color.qlip_main_white));

        }


        public class CardViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CardViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() != -1) {
                            if (cardList.get(getAdapterPosition()).exceptType == 1) {
                                Toast.makeText(getApplicationContext(), getString(R.string.search_except_msg), Toast.LENGTH_SHORT).show();
                            } else {
                                cardList.get(getAdapterPosition()).flag = !cardList.get(getAdapterPosition()).flag;
                                notifyDataSetChanged();
                            }
                        }

                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }

    }

    public class SearchBankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public SearchBankAdapter() {
        }

        public void addAll() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return bankList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search_item, viewGroup, false);
            return new CardViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CardViewHolder nHolder0 = (CardViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(R.drawable.ic_card);
            nHolder0.categoryNameTextView.setText(bankList.get(position).changeCardName);
            nHolder0.categoryLinearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), bankList.get(position).flag ? R.color.qlip_main_green : R.color.qlip_main_dark_3));
            nHolder0.categoryNameTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), bankList.get(position).exceptType == 1 ? R.color.qlip_sub_text : R.color.qlip_main_white));

        }


        public class CardViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CardViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() != -1) {
                            if (bankList.get(getAdapterPosition()).exceptType == 1) {
                                Toast.makeText(getApplicationContext(), getString(R.string.search_except_msg), Toast.LENGTH_SHORT).show();
                            } else {
                                bankList.get(getAdapterPosition()).flag = !bankList.get(getAdapterPosition()).flag;
                                notifyDataSetChanged();
                            }
                        }

                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }

    }

    public class SearchHashTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public SearchHashTagAdapter() {
        }

        public void addAll() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return hashList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_search_item, viewGroup, false);
            return new CategoryViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CategoryViewHolder nHolder0 = (CategoryViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(R.drawable.ic_label_white);
            nHolder0.categoryNameTextView.setText(hashList.get(position).tagName);
            nHolder0.categoryLinearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), hashList.get(position).check ? R.color.qlip_main_green : R.color.qlip_main_dark_3));


        }


        public class CategoryViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CategoryViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() != -1) {
                            hashList.get(getAdapterPosition()).check = !hashList.get(getAdapterPosition()).check;
                            notifyDataSetChanged();
                        }

                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_complete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onEnter();
                return true;
            default:
                return false;
        }
    }

    private void onCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }




    private void onEnter() {
        searchData.cardList = searchPresenter.makeCardList(cardList);
        searchData.cardList.addAll(searchPresenter.makeCardList(bankList));
        searchData.categoryList = searchPresenter.makeCategoryList(withdrawCategoryList);
        searchData.categoryList.addAll(searchPresenter.makeCategoryList(depositCategoryList));
        searchData.hashList = searchPresenter.makeHashTagList(hashList);
        searchData.keyword = searchEditText.getText().toString();
        searchData.usd = usdFlag ? 1 : 0;
        searchData.domestic = domesticFlag ? 1 : 0;
        searchData.installment = installmentFlag ? 1 : 0;
        searchData.repeat = repeatFlag ? 1 : 0;
        Intent intent = new Intent();
        if (searchData.cardList.isEmpty() &&
                searchData.categoryList.isEmpty() &&
                searchData.hashList.isEmpty() &&
                "".equals(searchData.keyword) &&
                searchData.usd == 0 &&
                searchData.domestic == 0 &&
                searchData.installment == 0 &&
                searchData.repeat == 0) {
        } else {
            intent.putExtra(Constants.IntentParameter.SEARCH_DATA, searchData);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        onCancel();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

}
