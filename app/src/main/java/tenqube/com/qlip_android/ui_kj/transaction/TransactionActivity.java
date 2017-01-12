package tenqube.com.qlip_android.ui_kj.transaction;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.data.UserTransactionData;

public class TransactionActivity extends BaseActivity {

    private TransactionAdapter mAdapter;
    SimpleDateFormat orginFormat;
    SimpleDateFormat new_Format;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        TransactionPresenter transactionPresenter = new TransactionPresenterImpl(TransactionActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        transactionPresenter.settingToolbar(getActionBarToolbar(toolbar));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TransactionActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            mAdapter = new TransactionAdapter();

            recyclerView.setAdapter(mAdapter);
            mAdapter.addAll(transactionPresenter.monthTransactionDataList());
            orginFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            new_Format = new SimpleDateFormat("MM월 yyyy");
            new_Format = new SimpleDateFormat("MM월 yyyy");
            date = new Date();


        }

    }


    public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<UserTransactionData> mTransactionList = new ArrayList<>();

        public TransactionAdapter() {
        }

        public void addAll(ArrayList<UserTransactionData> mTransactionList) {
            this.mTransactionList = mTransactionList;
            notifyDataSetChanged();

        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_first_item, parent, false);

            return new TransactionActivity.TransactionAdapter.TransactionViewHolder(vFirst);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final TransactionViewHolder viewHolder = (TransactionViewHolder) holder;


            try {
                date = orginFormat.parse(mTransactionList.get(position).spentDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            double reg_money =mTransactionList.get(position).spentMoney;
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String str = decimalFormat.format(reg_money);
            viewHolder.month_total_sum.setText(str+"원");
            viewHolder.month.setText(new_Format.format(date));


        }

        @Override
        public int getItemCount() {
            return mTransactionList.size();
        }


        public class TransactionViewHolder extends RecyclerView.ViewHolder {
            TextView month;
            TextView month_total_sum;



            public TransactionViewHolder(View convertView) {
                super(convertView);

                month = (TextView) convertView.findViewById(R.id.tr_month);
                month_total_sum = (TextView) convertView.findViewById(R.id.tr_total_sum);

            }


        }

    }


}
