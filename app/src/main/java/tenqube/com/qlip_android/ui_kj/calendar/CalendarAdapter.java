package tenqube.com.qlip_android.ui_kj.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

/**
 * Created by tenqube on 2016. 10. 11..
 */


public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CalendarViewHolder.OnCalendarItemClick{
    private ArrayList<String> list = new ArrayList<>();
    private Context mContext;
    private CalendarPresenter calendarPresenter;
    private HashMap<String, UserTransactionData>  dayMap = new HashMap<>();

    private HashMap<String, UserTransactionData> spentMap = new HashMap<>();

    public CalendarAdapter(CalendarActivity activity) {
        this.mContext = activity;
        this.calendarPresenter  =activity.calendarPresenter;


    }

    public void addAll(ArrayList<String> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
    public void addIncomeMap(HashMap<String,UserTransactionData> map){
        this.dayMap.clear();
        this.dayMap = map;
        notifyDataSetChanged();
    }
    public void addSpentMap(HashMap<String,UserTransactionData> map ){
        this.spentMap.clear();
        this.spentMap = map;
        notifyDataSetChanged();
    }
    public String comma_won(String comma) {
        int inValues = Integer.parseInt(comma);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result_int = (String) Commas.format(inValues);
        return result_int;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup vFirst = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_calendar_item, parent, false);
            CalendarViewHolder holder = new CalendarViewHolder(vFirst);
        holder.setOnCalendarItemClick(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CalendarViewHolder calendarViewHolder = (CalendarViewHolder) holder;

        calendarViewHolder.spent.setText("");
        calendarViewHolder.income.setText("");
        calendarViewHolder.day_text.setText(list.get(position));
        String dateformat = calendarPresenter.initData() + "-" + list.get(position);

        Log.i("dayMap",list.get(0));
        Log.i("dateformat",dateformat+"");

        if (dayMap.containsKey(dateformat)&&dateformat.length()>8) {

            String temp = (int) dayMap.get(dateformat).spentMoney + "";

            calendarViewHolder.income.setText(comma_won(temp) + "");
            calendarViewHolder.income.setTextColor(mContext.getResources().getColor(R.color.income_main_color));

        }
        ;
        if (spentMap.containsKey(dateformat)&&dateformat.length()>8) {
            String temp = (int) spentMap.get(dateformat).spentMoney + "";

            calendarViewHolder.spent.setText(comma_won(temp) + "");
            calendarViewHolder.spent.setTextColor(mContext.getResources().getColor(R.color.withdraw_color));

        }


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(CalendarAdapter adapter,View view, UserTransactionData userTransactionData,int position);
    }

    OnAdapterItemClickLIstener mAdapterListener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.mAdapterListener = listener;
    }
        @Override
        public void onCalendarItemClick(View view, int position) {
            if (mAdapterListener != null) {
                String dateformat = calendarPresenter.initData() + "-" + list.get(position);
                if (dayMap.containsKey(dateformat)) {
                    UserTransactionData userTransactionData = dayMap.get(dateformat);
                    Log.i("data", userTransactionData.spentDate + "");
                    mAdapterListener.onAdapterItemClick(this, view, userTransactionData, position);

                }
            }

    }
}
