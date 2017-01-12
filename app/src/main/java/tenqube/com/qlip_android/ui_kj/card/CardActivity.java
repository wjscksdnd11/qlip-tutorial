package tenqube.com.qlip_android.ui_kj.card;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.base.BaseActivity;
import tenqube.com.qlip_android.data.CardTableData;

import static tenqube.com.qlip_android.R.id.btn_insert;

public class CardActivity extends BaseActivity {

    private CardAdapter mAdapter ;
    private CardPresenter cardPresenter;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) {
            return;
        }

        setContentView(R.layout.activity_card);



         cardPresenter = new CardPresenterImpl(CardActivity.this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        cardPresenter.settingToolbar(getActionBarToolbar(toolbar));
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        if(recyclerView!=null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CardActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            mAdapter = new CardAdapter();
            recyclerView.setAdapter(mAdapter);

            mAdapter.addAll(cardPresenter.loadCardDataList());
        }

        btn = (Button)findViewById(btn_insert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPresenter.insertCard();
                mAdapter.mCardList = cardPresenter.loadCardDataList();
                mAdapter.notifyDataSetChanged();


            }
        });

        btn = (Button)findViewById(R.id.btn_select);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mAdapter.mCardList =  cardPresenter.selectCard();
                mAdapter.notifyDataSetChanged();
            }
        });


    }
        public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

            private ArrayList<CardTableData> mCardList = new ArrayList<>();

            public CardAdapter(){}

            public void addAll(ArrayList<CardTableData> cardList){

                    this.mCardList = cardList;
                    notifyDataSetChanged();


                }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               ViewGroup vFirst = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_card_item,parent,false);

                return new CardActivity.CardAdapter.CardViewHolder(vFirst);

            }


            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                    final CardViewHolder viewHolder = (CardViewHolder) holder;
                viewHolder.card_name.setText(mCardList.get(position).cardName);
                viewHolder.card_type.setText("Card Type : "+mCardList.get(position).cardType);
                viewHolder.card_subType.setText("Card SubType : "+mCardList.get(position).cardSubType);


            }

            @Override
            public int getItemCount() {
                return mCardList.size();
            }




            public class CardViewHolder extends RecyclerView.ViewHolder {

                TextView card_name;
                TextView card_type;
                TextView card_subType;
                Button btn_update;
                Button btn_delete;
                public CardViewHolder(View convertView){
                    super(convertView);
                    btn_update = (Button)convertView.findViewById(R.id.btn_update);
                    btn_delete = (Button)convertView.findViewById(R.id.btn_delete);

                    btn_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            cardPresenter.updateCard(mCardList.get(getAdapterPosition()));
                            mCardList = cardPresenter.loadCardDataList();
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                    btn_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardPresenter.delete(mCardList.get(getAdapterPosition()));
                            mCardList = cardPresenter.loadCardDataList();
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                    card_name = (TextView)convertView.findViewById(R.id.card_name);
                    card_type = (TextView)convertView.findViewById(R.id.card_type);
                    card_subType = (TextView)convertView.findViewById(R.id.card_sub_type);

                }

        }



}




}
