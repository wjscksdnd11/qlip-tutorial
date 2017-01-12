package tenqube.com.qlip_android.ui_kj.calendarsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpentRecyclerFragment extends Fragment {

    static ArrayList<UserTransactionData> mDayList = new ArrayList<>();
    CalSamplePresenter calSamplePresenter;
    private SpentRecyclerAdapter spentRecyclerAdapter;
    private String date;
    public SpentRecyclerFragment() {

    }

    public static SpentRecyclerFragment newInstance() {
        SpentRecyclerFragment fragment = new SpentRecyclerFragment();


        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spent_recycler, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            spentRecyclerAdapter = new SpentRecyclerAdapter();
            recyclerView.setAdapter(spentRecyclerAdapter);
            calSamplePresenter = new CalSamplePresenterImpl((CalSampleActivity) getActivity());

            spentRecyclerAdapter.notifyDataSetChanged();
        }
        v = view;

        return view;
    }

    static View v;

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
        }

    }

    public void setAdapter(ArrayList<UserTransactionData> userTransactionDatas,String date) {
        spentRecyclerAdapter.addAll(userTransactionDatas);
        this.date = date;

        spentRecyclerAdapter.notifyDataSetChanged();


    }
    public void getIntent(){
        //여기서 액티비티 연결
//        Toast.makeText(getContext(), "date? "+date, Toast.LENGTH_SHORT).show();
    }
    private class SpentRecyclerAdapter extends RecyclerView.Adapter {
        ArrayList<UserTransactionData> mList = new ArrayList();
        private int FOOTER = 1;


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == FOOTER) {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_footer, parent, false);
                FooterViewHolder footerHolder = new FooterViewHolder(viewGroup);
                return footerHolder;
            } else {

                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_day_item, parent, false);
                SpentRecyclerViewHolder holder = new SpentRecyclerViewHolder(viewGroup, viewType);


                return holder;
            }
        }


        public void addAll(ArrayList<UserTransactionData> mList) {
            this.mList = mList;

            FOOTER = mList.size() + 1;
            notifyDataSetChanged();

        }

        public String comma_won(String comma) {
            int inValues = Integer.parseInt(comma);
            DecimalFormat Commas = new DecimalFormat("#,###");
            String result_int = (String) Commas.format(inValues);
            return result_int;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder.getItemViewType()==FOOTER) {
                onBindFooterViewHolder(holder, position);
            } else {
                onBindBasicViewHolder(holder, position);
            }
        }


        public void onBindBasicViewHolder(RecyclerView.ViewHolder holder, int position) {
            SpentRecyclerViewHolder spentRecyclerViewHolder = (SpentRecyclerViewHolder) holder;
            spentRecyclerViewHolder.spent_money.setText("");
            spentRecyclerViewHolder.keyword.setText("");
            spentRecyclerViewHolder.card_name.setText("");
            spentRecyclerViewHolder.spent_time.setText("");

                spentRecyclerViewHolder.card_name.setText(mList.get(position).cardName + "");
                spentRecyclerViewHolder.spent_money.setText(comma_won((int) mList.get(position).spentMoney + "") + "원");
                spentRecyclerViewHolder.spent_time.setText(mList.get(position).spentDate.substring(11, 16));

            Log.i("spentRecyclerViewHolder", mList.size()+"");


        }

        @Override
        public int getItemViewType(int position) {
            if (position+1 ==FOOTER||mList.size()==0) {
                return FOOTER;
            } else {

                return super.getItemViewType(position);
            }

        }


        @Override
        public int getItemCount() {

            return mList.size() + 1;
        }

        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
             FooterViewHolder footerviewholder = (FooterViewHolder) holder;
            footerviewholder.addSpent.setText("+ 내역을 등록하세요 :)");

        }

        ;

    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private TextView addSpent;
        private static final String DATE ="date";
        public FooterViewHolder(View itemView) {
            super(itemView);
            addSpent = (TextView) itemView.findViewById(R.id.footer_txt);
            addSpent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getIntent();
                }
            });
        }

    }

    private class SpentRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView card_name;
        private TextView spent_time;
        private TextView keyword;
        private TextView spent_money;
        int Holderid;


        public SpentRecyclerViewHolder(View convertview, int viewtype) {
            super(convertview);

            card_name = (TextView) convertview.findViewById(R.id.text_card_name);
            spent_time = (TextView) convertview.findViewById(R.id.text_spnet_time);
            keyword = (TextView) convertview.findViewById(R.id.text_spent_keyword);
            spent_money = (TextView) convertview.findViewById(R.id.text_spnet_money);

        }

    }
}
