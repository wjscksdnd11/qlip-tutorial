package tenqube.com.qlip_android.ui_kj.dialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import java.util.Calendar;

import tenqube.com.qlip_android.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String ARG_CAL = "param 1";
    private Callback mListener;
    private Calendar mCalendar = Calendar.getInstance();
    public static DatePickerFragment newInstance(Calendar calendar) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAL, calendar);
        fragment.setArguments(args);
        return fragment;
    }
    public DatePickerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCalendar=(Calendar)getArguments().getSerializable(ARG_CAL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors - Here we uses first one
            public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth)
//            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
         */
        return new DatePickerDialog(getActivity(), R.style.DialogTheme,this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mCalendar.set(year,month,day);
        int todayYear= Calendar.getInstance().get(Calendar.YEAR);
        int todayMonth= Calendar.getInstance().get(Calendar.MONTH);
        int todayDate= Calendar.getInstance().get(Calendar.DATE);
        onCalendar(mCalendar);

        if(
                year<todayYear||
                        (year==todayYear&&month<todayMonth)||
                        (year==todayYear&&month==todayMonth&&day<todayDate)
                ){

            onRepeatCancel();
        }
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
    public void onRepeatCancel() {
        if (mListener != null) {
            mListener.onRepeatCancel();
        }
    }
    public void onCalendar(Calendar calendar) {
        if (mListener != null) {
            mListener.onCalendar(calendar);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Callback {
        void onRepeatCancel();
        void onCalendar(Calendar calendar);

    }
}
