package tenqube.com.qlip_android.ui_kj.calendarsample;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

public class CalendarFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String MONTH = "month";
    public static final String MONTH_DATA = "month_data";
    public static final String SP_POSITION = "sp_position";
    public static final String PARENT_POSITION = "parent_position";
    public static final String SUMTYPE = "sum_type";

    private int spinner_type;
    private int sumType;
    private CalendarData calendarData;
    public CalRecyclerAdapter mAdapter;
    private Callback mListener;
    private int parentPos = 0;
    private static int childPos = -1;
    private String this_month;
    private static String selectDate;
    private String prev_selectDate;
    private CalSamplePresenter calSamplePresenter;
    private Animation animFadein;
    private Animation animFadeout;
    private  static int prev_select_parentPos = -1;
    private  static int prev_child_position = -1;
    private  static int selectPos = -1;
    public CalendarFragment() {


    }

    public static CalendarFragment newInstance(CalendarData calendarData, int spinner_position, int parent_position, int sumType) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putSerializable(MONTH_DATA, calendarData);
        args.putInt(SP_POSITION, spinner_position);
        args.putInt(PARENT_POSITION, parent_position);
        args.putInt(SUMTYPE, sumType);

        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            calendarData = (CalendarData) getArguments().getSerializable(MONTH_DATA);
            spinner_type = getArguments().getInt(SP_POSITION);
            parentPos = getArguments().getInt(PARENT_POSITION);
            sumType = getArguments().getInt(SUMTYPE);
            calSamplePresenter = new CalSamplePresenterImpl((CalSampleActivity) getActivity());

        }
        for (int i = 0; i < calendarData.dayDataList.size(); i++) {
            if (calendarData.dayDataList.get(i).spentdata != null)
                calendarData.dayDataList.get(i).spentdata = null;

        }// 소비데이터 초기화



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
            recyclerView.setLayoutManager(gridLayoutManager);

            mAdapter = new CalRecyclerAdapter(this, calendarData, parentPos);

            recyclerView.setAdapter(mAdapter);
            recyclerView.setHasFixedSize(true);

        }

        animFadein = AnimationUtils.loadAnimation(getContext(),
                R.anim.fad_in);
        animFadeout = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

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

    public void onRefresh(int parentPos, int childPos, ArrayList<UserTransactionData> dayDataList, String date) {
        if (mListener != null) {
            prev_child_position = this.childPos;
            this.childPos = childPos;
            prev_select_parentPos = this.selectPos;
            this.selectPos = this.parentPos;


            mListener.onRefresh(parentPos, childPos, dayDataList, date);
            mAdapter.notifyItemChanged(childPos);
            mAdapter.notifyItemChanged(prev_child_position);


        }
    }


    public void setData() {
        SpentDataSetTask spentDataSetTask = new SpentDataSetTask();
        spentDataSetTask.execute();
    }

    public void updateFragment() {

        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {

            if(mAdapter!=null&& prev_select_parentPos ==parentPos) {

                mAdapter.notifyDataSetChanged();

                        prev_select_parentPos = selectPos;

                Log.i("setUSerVisibleHint",prev_child_position+" , "+ prev_select_parentPos);
            }

        } else {


        }
    }

    public interface Callback {
        void onRefresh(int parentPos, int childPos, ArrayList<UserTransactionData> dayDataList, String date);

    }

    public void viewpager() {

    }

    public class CalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private CalSamplePresenter calendarPresenter;


        private int parentPos;
        private CalendarData calendarData;

        private CalendarFragment mFragment;

        public CalRecyclerAdapter(CalendarFragment fragment, CalendarData calendarData, int parentPos) {
            this.mContext = fragment.getContext();
            this.mFragment = fragment;
            this.calendarData = calendarData;
            this.parentPos = parentPos;
            this_month = calendarData.yearMonth.replace('.', '-');
            setData();
        }


        public String comma_won(int comma) {
            int inValues = comma;
            DecimalFormat Commas = new DecimalFormat("#,###");
            String result_int = (String) Commas.format(inValues);
            return result_int;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_calendar_item, parent, false);
            CalRecyclerViewHolder holder = new CalRecyclerViewHolder(vFirst);// 여기 다시 체크 안쓰는 부분 있는거 같음 .

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final CalRecyclerViewHolder calRecyclerViewHolder = (CalRecyclerViewHolder) holder;


            calRecyclerViewHolder.day.setText("");
            calRecyclerViewHolder.calLayout.setBackgroundColor(Color.TRANSPARENT);
            calRecyclerViewHolder.calLayout.setAlpha(1f);
            calRecyclerViewHolder.contentSpent.removeAllViews();


            if (calendarData.dayDataList.get(position).date.equals(selectDate) || !calendarData.dayDataList.get(position).date.substring(0, 7).equals(this_month)) {
                Log.i("this_maonth", position + "");
                calRecyclerViewHolder.calLayout.setAlpha(0.5f);
            }

            if (parentPos ==selectPos&&position ==childPos) {
                calRecyclerViewHolder.calLayout.setBackgroundColor(ContextCompat.getColor(mFragment.getActivity(), R.color.green));

            } else {
                calRecyclerViewHolder.calLayout.setBackgroundColor(ContextCompat.getColor(mFragment.getActivity(), R.color.transparent_background));
            }



            calRecyclerViewHolder.day.setText(calendarData.dayDataList.get(position).day + "");

            if (calendarData.dayDataList.get(position).spentdata != null) {
                TextView spenttext = new TextView(getContext());
                spenttext.setTextColor(ContextCompat.getColor(mFragment.getActivity(), R.color.qlip_main_white));
                spenttext.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
//                spenttext.startAnimation(animFadein);

                for (UserTransactionData data : calendarData.dayDataList.get(position).spentdata) {
                    if (sumType == 0 || sumType == 1) {
                        spenttext.setText(comma_won((int) data.spentMoney) + "");
                    } else {
                        spenttext.setText(data.keyword);
                    }
                    if (data.dwType == 1) {

                        spenttext.setTextColor(ContextCompat.getColor(mFragment.getActivity(), R.color.red));

                    }
                    calRecyclerViewHolder.contentSpent.addView(spenttext);


                }
            }
            if (!calendarData.dayDataList.get(position).date.substring(0, 7).equals(this_month)) {
                calRecyclerViewHolder.calLayout.setAlpha(0.5f);
            }
        }

        @Override
        public int getItemCount() {
            return calendarData.dayDataList.size();
        }


    }


    public class CalRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public TextView extra_cnt;

        public LinearLayout calLayout;
        public LinearLayout contentSpent;


        public CalRecyclerViewHolder(final View convertView) {

            super(convertView);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAdapterPosition() != -1) {

                        prev_selectDate = selectDate;
                        selectDate = calendarData.dayDataList.get(getAdapterPosition()).date;

                        onRefresh(parentPos, getAdapterPosition(), calendarData.dayDataList.get(getAdapterPosition()).spentdata, this_month);

                    }

                }
            });
            day = (TextView) convertView.findViewById(R.id.calandar_item_text);


//            extra_cnt = (TextView) convertView.findViewById(R.id.extra_item);
//            spenttext = (TextView) convertView.findViewById(R.id.spent_text1);
            calLayout = (LinearLayout) convertView.findViewById(R.id.calendar_layout);
            contentSpent = (LinearLayout) convertView.findViewById(R.id.spent_layout);


        }


    }

    public class SpentDataSetTask extends AsyncTask<Void, Void, ArrayList<CalendarSpentData>> {


        @Override
        protected ArrayList<CalendarSpentData> doInBackground(Void... params) {
            if (calendarData.dayDataList.size() == 0) {
                return null;
            }
            ArrayList<UserTransactionData> spentDataArrayList;
            ArrayList<CalendarSpentData> userTransactionDatas;
            userTransactionDatas = calendarData.dayDataList;
            spentDataArrayList = calSamplePresenter.spentArrayList(calendarData.dayDataList.get(0).date, calendarData.dayDataList.get(41).date, spinner_type, sumType);

//            Log.i("tempdata",calendarData.dayDataList.get(0).date);
            if (spentDataArrayList.size() > 0) {
                for (int i = 0; i < calendarData.dayDataList.size(); i++) {
                    calendarData.dayDataList.get(i).spentdata = null;
                    calendarData.dayDataList.get(i).spentdata = new ArrayList<>();
                    userTransactionDatas.get(i).spentdata = new ArrayList<>();


                    for (int j = 0; j < spentDataArrayList.size(); j++) {

                        if (userTransactionDatas.get(i).date.equals(spentDataArrayList.get(j).spentDate.substring(0, 10))) {
                            userTransactionDatas.get(i).spentdata.add(spentDataArrayList.get(j));
                        }
//                        Log.i("dayDataList", calendarData.dayDataList.get(i).spentdata.size() + "' " + calendarData.dayDataList.get(i).date);
                    }


                }
            }

            return userTransactionDatas;

        }


        @Override
        protected void onPostExecute(ArrayList<CalendarSpentData> userTransactionDatas) {
            super.onPostExecute(userTransactionDatas);


            calendarData.dayDataList = userTransactionDatas;

            updateFragment();

        }
    }
}
