package tenqube.com.qlip_android.ui_kj.license;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.data.OpenSourceData;


public class OpenSourceLicenseActivity extends BaseActivity {
    private static final String SCREEN_LABEL="OpenSourceLicense_Activity";
    private OpenSourceAdapter mOpenSourceAdapter;
    private ArrayList<OpenSourceData> mLicenseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) {
            return;
        }
        setContentView(R.layout.activity_open_source_license);
        OpenSourceLicensePresenter openSourceLicensePresenter = new OpenSourceLicensePresenterImpl(OpenSourceLicenseActivity.this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        openSourceLicensePresenter.settingToolbar(getActionBarToolbar(toolbar));
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        if(recyclerView!=null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OpenSourceLicenseActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            mOpenSourceAdapter = new OpenSourceAdapter();
            recyclerView.setAdapter(mOpenSourceAdapter);
            mLicenseList = openSourceLicensePresenter.loadLicenseDataList();
            mOpenSourceAdapter.addAll();
        }
    }






    public class OpenSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public OpenSourceAdapter() {
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
            return mLicenseList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_open_source_item, viewGroup, false);
            return new OpenSourceViewHolder(vFirst);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            OpenSourceViewHolder viewHolder = (OpenSourceViewHolder) holder;

            viewHolder.title.setText(mLicenseList.get(position).title);
            viewHolder.url.setText(mLicenseList.get(position).url);
            viewHolder.copyright.setText(mLicenseList.get(position).copyright);
            viewHolder.license.setText(mLicenseList.get(position).license);


        }




        public class OpenSourceViewHolder extends RecyclerView.ViewHolder {

            TextView title;
            TextView url;
            TextView copyright;
            TextView license;

            public OpenSourceViewHolder(View convertView) {
                super(convertView);

                title=(TextView)convertView.findViewById(R.id.title);
                url=(TextView)convertView.findViewById(R.id.url);
                copyright=(TextView)convertView.findViewById(R.id.copyright);
                license=(TextView)convertView.findViewById(R.id.license);

            }

        }
    }



}
