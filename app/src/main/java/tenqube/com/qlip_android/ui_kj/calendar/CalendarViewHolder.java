package tenqube.com.qlip_android.ui_kj.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tenqube on 2016. 10. 11..
 */

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView day_text;
    public TextView income;
    public TextView spent;

    public interface OnCalendarItemClick {
        public void onCalendarItemClick(View view, int position);
    }

    OnCalendarItemClick listener;
    public void setOnCalendarItemClick(OnCalendarItemClick listener) {
        this.listener = listener;
    }

    public CalendarViewHolder(View convertView) {
        super(convertView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCalendarItemClick(v, getAdapterPosition());
                }
            }
        });
//        day_text = (TextView) convertView.findViewById(R.id.calandar_item_text);
//        income = (TextView) convertView.findViewById(R.id.spent1);
//        spent = (TextView) convertView.findViewById(R.id.spent2);
//

    }
}