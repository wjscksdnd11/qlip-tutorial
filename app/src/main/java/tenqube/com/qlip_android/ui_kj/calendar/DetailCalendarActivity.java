package tenqube.com.qlip_android.ui_kj.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

public class DetailCalendarActivity extends AppCompatActivity {
    UserTransactionData userData;
    DetailIncomeCalendarAdapter incomeCalendarAdapter;
    DetailSpentCalendarAdapter detailSpentCalendarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_calendar);
        Intent  intent = getIntent();

        userData =(UserTransactionData)intent.getSerializableExtra("userTransactionData") ;


        Toast.makeText(this, +userData.spentMoney+"", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
   

        if(recyclerView !=null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            incomeCalendarAdapter = new DetailIncomeCalendarAdapter();
            detailSpentCalendarAdapter = new DetailSpentCalendarAdapter();
            recyclerView.setAdapter(incomeCalendarAdapter);


        }
    }


    private class DetailIncomeCalendarAdapter extends  RecyclerView.Adapter<DetailCalendarViewHolder>{
      private  ArrayList<String> mList = new ArrayList<>();


        @Override
        public DetailCalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewGroup vFirst =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_detail_calendar_item,parent,false);
            return new DetailCalendarViewHolder(vFirst);
        }

        @Override
        public void onBindViewHolder(DetailCalendarViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


    private class DetailSpentCalendarAdapter extends  RecyclerView.Adapter<DetailCalendarViewHolder>{

      private  ArrayList<String> mList = new ArrayList<>();
        @Override
        public DetailCalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewGroup vFirst =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.spent_item,parent,false);
            return new DetailCalendarViewHolder(vFirst);
        }

        @Override
        public void onBindViewHolder(DetailCalendarViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    public class DetailCalendarViewHolder extends RecyclerView.ViewHolder{


        public DetailCalendarViewHolder(View itemView) {
            super(itemView);
            TextView textView = (TextView)findViewById(R.id.spent_text);
        }
    }
}
