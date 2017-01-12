package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.ui_kj.caledarwidget.Common;
import tenqube.com.qlip_android.ui_kj.detail.DetailTransactionActivity;
import tenqube.com.qlip_android.util.CategoryUtil;


class CalViewFrgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String DATE = "date";

    private int FOOTER = 1;
    private Context mContext;
    private ArrayList<UserTransactionData> mList = new ArrayList<>();
    private CalViewPresenter mCalViewPresenter;
    private long mTime;

    CalViewFrgAdapter(Context mContext, CalViewPresenter calViewPresenter) {
        this.mContext = mContext;
        this.mCalViewPresenter = calViewPresenter;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_footer, parent, false);
            return new FooterViewHolder(viewGroup);
        } else {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_day_item, parent, false);
            return new SpentRecyclerViewHolder(viewGroup);
        }
    }

    public void addAll(long time, ArrayList<UserTransactionData> mList) {

        this.mTime = time;
        if (mList == null) {
            mList = new ArrayList<>();
        }
        this.mList = mList;
        if (mCalViewPresenter!=null)
        mCalViewPresenter.setUserTransactionDataList(mList);
        FOOTER = this.mList.size() + 1;
        notifyDataSetChanged();
    }

    interface OnClickSpentItemListener {
        void onReceivedIdentifier(long identifier);
    }

    private OnClickSpentItemListener mOnClickSpentItmeListner;

    void setOnClickSpentItmeListener(OnClickSpentItemListener onClickSpentItmeListner) {
        this.mOnClickSpentItmeListner = onClickSpentItmeListner;
    }

    private void onClickSpentItem(long identifier) {
        if (mOnClickSpentItmeListner != null) {
            mOnClickSpentItmeListner.onReceivedIdentifier(identifier);
        }
    }

    interface OnSpentItemRemoveListenter {
        void onReceiveRemoveIdentifierList(int position);
    }

    private OnSpentItemRemoveListenter mOnSpentItemRemoveListener;

    void setOnClickRemoveIdentifierListener(OnSpentItemRemoveListenter listener) {
        mOnSpentItemRemoveListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == FOOTER) {
            onBindFooterViewHolder(holder);

        } else {
            onBindBasicViewHolder(holder, position);
        }
    }

    private void onBindBasicViewHolder(RecyclerView.ViewHolder holder, int position) {
        SpentRecyclerViewHolder spentRecyclerViewHolder = (SpentRecyclerViewHolder) holder;
        if (mCalViewPresenter.refreshTag()) {
            spentRecyclerViewHolder.checkView.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = spentRecyclerViewHolder.keyword.getLayoutParams();
            layoutParams.width = spentRecyclerViewHolder.dp120;
            spentRecyclerViewHolder.keyword.setLayoutParams(layoutParams);
            layoutParams = spentRecyclerViewHolder.icon.getLayoutParams();
            layoutParams.width = spentRecyclerViewHolder.dp20;
            spentRecyclerViewHolder.icon.setLayoutParams(layoutParams);
            spentRecyclerViewHolder.icon.setPadding(0, 0, 0, 20);
            if (mCalViewPresenter.checkIdentifier(mList.get(position).identifier)) {
                spentRecyclerViewHolder.checkView.setImageResource(R.drawable.ic_setting_check_box_on);
            } else {
                spentRecyclerViewHolder.checkView.setImageResource(R.drawable.ic_setting_check_box_off);
            }
        }
        spentRecyclerViewHolder.card_name.setText(mList.get(position).cardName);
        spentRecyclerViewHolder.mSpentMoney.setText(Common.comma_won((int) mList.get(position).spentMoney));
        spentRecyclerViewHolder.spent_time.setText(mList.get(position).spentDate.substring(11, 16));
        spentRecyclerViewHolder.icon.setImageResource(CategoryUtil.getIconImage(Integer.valueOf(mList.get(position).categoryCode.substring(0, 2))));
        spentRecyclerViewHolder.keyword.setText(mList.get(position).keyword);

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == FOOTER || mList.size() == 0) {
            return FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }

    private void setItemChecked(int position) {


        if (mOnSpentItemRemoveListener != null) {
            mOnSpentItemRemoveListener.onReceiveRemoveIdentifierList(position);
        }
    }

    @Override
    public int getItemCount() {

        return mList.size() + 1;
    }

    private void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        FooterViewHolder footerviewholder = (FooterViewHolder) holder;
        footerviewholder.addSpent.setText("+ 내역을 등록하세요 :)");
    }


    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private TextView addSpent;

        private FooterViewHolder(View itemView) {
            super(itemView);
            addSpent = (TextView) itemView.findViewById(R.id.footer_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(mTime);
                    Toast.makeText(mContext, calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, DetailTransactionActivity.class);
                    intent.putExtra(DATE, mTime);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private class SpentRecyclerViewHolder extends RecyclerView.ViewHolder implements Checkable {
        private TextView card_name;
        private TextView spent_time;
        private TextView keyword;
        private TextView mSpentMoney;
        private ImageView icon;
        private ImageView checkView;
        private int dp120;
        private int dp20;

        private SpentRecyclerViewHolder(final View convertview) {
            super(convertview);
            convertview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCalViewPresenter.refreshTag()) {
                        setItemChecked(getAdapterPosition());
                    } else {
                        onClickSpentItem(mList.get(getAdapterPosition()).identifier);
                    }
                }
            });
            convertview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mCalViewPresenter.clearIdentifier();
                    mCalViewPresenter.setTag(true);
                    setItemChecked(getAdapterPosition());

                    return true;
                }
            });

            checkView = (ImageView) convertview.findViewById(R.id.checkbox);
            dp120 = (int) Common.dp2px(mContext, 120);
            dp20 = (int) Common.dp2px(mContext, 20);
            icon = (ImageView) convertview.findViewById(R.id.category_icon);
            card_name = (TextView) convertview.findViewById(R.id.text_card_name);
            spent_time = (TextView) convertview.findViewById(R.id.text_spnet_time);
            keyword = (TextView) convertview.findViewById(R.id.text_spent_keyword);
            mSpentMoney = (TextView) convertview.findViewById(R.id.text_spnet_money);

        }


        boolean isChecked;

        @Override
        public void setChecked(boolean checked) {
            if (isChecked != checked) {
                isChecked = checked;
                drawCheck();
            }
        }

        private void drawCheck() {
            if (isChecked) {
                checkView.setImageResource(R.drawable.ic_setting_check_box_on);
            } else {
                checkView.setImageResource(R.drawable.ic_setting_check_box_off);
            }
        }

        @Override
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void toggle() {
            setChecked(!isChecked);
        }
    }
}