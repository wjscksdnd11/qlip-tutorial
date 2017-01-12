package tenqube.com.qlip_android.ui_kj.select.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.util.CategoryUtil;

public class SelectCategoryActivity extends BaseActivity {

    private static final String SCREEN_LABEL="Select_Category_Activity";
    private CategoryAdapter mCategoryAdapter;
    private SelectCategoryPresenter mSelectCategoryPresenter;
    public ArrayList<UserCategoryConfigTableData> mCategoryList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) {
            return;
        }
        setContentView(R.layout.activity_select_category);

        mSelectCategoryPresenter=new SelectCategoryPresenterImpl(SelectCategoryActivity.this);
        mSelectCategoryPresenter.receiveIntentValue();

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar=getActionBarToolbar(toolbar);
        mSelectCategoryPresenter.settingToolbar(toolbar);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        if(recyclerView!=null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            mCategoryAdapter = new CategoryAdapter(getApplicationContext());
            recyclerView.setAdapter(mCategoryAdapter);
            mCategoryList = mSelectCategoryPresenter.loadCategoryList();
            mCategoryAdapter.addAll();
        }



    }

    public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public Context mContext;

        public CategoryAdapter(Context mContext) {
            this.mContext = mContext;
        }
        public void addAll(){
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mCategoryList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category_item, viewGroup, false);
            return new CategoryViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CategoryViewHolder nHolder0 = (CategoryViewHolder) holder;
            nHolder0.categoryImg.setBackgroundResource(CategoryUtil.getIconImage(Integer.parseInt(mCategoryList.get(position).categoryCode.substring(0, 2))));
            nHolder0.categoryNameTextView.setText(mCategoryList.get(position).largeCategory);

        }


        public class CategoryViewHolder extends RecyclerView.ViewHolder {

            ImageView categoryImg;
            TextView categoryNameTextView;
            LinearLayout categoryLinearLayout;

            public CategoryViewHolder(View convertView) {
                super(convertView);
                categoryLinearLayout=(LinearLayout)convertView.findViewById(R.id.category_container);
                categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(getAdapterPosition()!=-1) {
                            if (getIntent().getIntExtra(Constants.IntentParameter.WHERE_NUM, 0) != 0) {
                                mSelectCategoryPresenter.goReturnActivity(mCategoryList.get(getAdapterPosition()).categoryCode, mCategoryList.get(getAdapterPosition()).largeCategory);
                            }
                        }
                    }
                });
                categoryImg = (ImageView) convertView.findViewById(R.id.category_img);
                categoryNameTextView = (TextView) convertView.findViewById(R.id.category_name);
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.APP_WIDGET:
                if (resultCode == RESULT_OK) {
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
    }

}
