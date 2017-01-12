package tenqube.com.qlip_android.ui_kj.calendar_kj;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import tenqube.com.qlip_android.R;


public class CalendarFragment extends Fragment {
    private static final String ARG_CAL_DATA = "param1";
    private static final String ARG_PARENT_POS = "param2";

    private CalendarData calendarData;
    private int parentPos;
    private Callback mListener;
    private RecyclerView calendarRecyclerView;
    private CalendarContentAdapter mAdapter;

    public CalendarFragment() {
    }

    public static CalendarFragment newInstance(CalendarData calendarData, int parentPos) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAL_DATA, calendarData);
        args.putInt(ARG_PARENT_POS, parentPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            calendarData = (CalendarData)getArguments().getSerializable(ARG_CAL_DATA);
            parentPos = getArguments().getInt(ARG_PARENT_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar_contents, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendar);
//        calendarRecyclerView = (RecyclerView)view.findViewById(R.id.calendarRecyclerView);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),7);
//        calendarRecyclerView.setLayoutManager(gridLayoutManager);
//        calendarRecyclerView.setHasFixedSize(true);
//        mAdapter = new CalendarContentAdapter();
//        calendarRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mListener = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onRefresh(int parentPos, int childPos){
        if (mListener != null) {
            mListener.onRefresh(parentPos, childPos);
        }
    }

    public void updateFragment() {
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
    }

    public interface Callback {
        void onRefresh(int parentPos, int childPos);

    }


    public class CalendarContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public CalendarContentAdapter() {
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
            return calendarData.calendarSubList.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_calendar_first_rv_item, viewGroup, false);
            return new CalendarViewHolder(vFirst);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CalendarViewHolder nHolder = (CalendarViewHolder) holder;
            if(calendarData.calendarSubList.get(position).isChecked){
                nHolder.dayTextView.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.qlip_main_green));
            }else{
                nHolder.dayTextView.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.qlip_main_dark_3));
            }
            nHolder.dayTextView.setText(calendarData.calendarSubList.get(position).day+"");
        }

        public class CalendarViewHolder extends RecyclerView.ViewHolder {

            TextView dayTextView;

            public CalendarViewHolder(View convertView) {
                super(convertView);
                dayTextView=(TextView)convertView.findViewById(R.id.day);
                dayTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(getAdapterPosition() != -1){
                            onRefresh(parentPos, getAdapterPosition());
                        }
                    }
                });
            }
        }
    }

}
