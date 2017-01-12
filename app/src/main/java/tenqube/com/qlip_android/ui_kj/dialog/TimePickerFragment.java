package tenqube.com.qlip_android.ui_kj.dialog;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

import tenqube.com.qlip_android.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private static final String ARG_CAL = "param 1";
    private TimePickerFragment.Callback mListener;
    private Calendar mCalendar = Calendar.getInstance();
    public static TimePickerFragment newInstance(Calendar calendar) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAL, calendar);
        fragment.setArguments(args);
        return fragment;
    }
    public TimePickerFragment() {
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
        //Use the current time as the default values for the time picker
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(), R.style.DialogTheme,this, hour, minute,
                false);
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){

        mCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        mCalendar.set(Calendar.MINUTE,minute);
        onTimeCalendar(mCalendar);

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
    public void onTimeCalendar(Calendar calendar) {
        if (mListener != null) {
            mListener.onTimeCalendar(calendar);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Callback {
        void onTimeCalendar(Calendar calendar);

    }
}
