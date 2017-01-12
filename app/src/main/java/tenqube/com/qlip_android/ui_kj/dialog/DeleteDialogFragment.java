package tenqube.com.qlip_android.ui_kj.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import tenqube.com.qlip_android.R;


public class DeleteDialogFragment extends DialogFragment implements View.OnClickListener {
    private Callback mListener;
    private boolean twoTouch;
    public static DeleteDialogFragment newInstance() {
        return new DeleteDialogFragment();
    }


    public DeleteDialogFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alert_dialog_delete, container);
        getDialog().setCanceledOnTouchOutside(false);
        TextView mYesButton = (TextView) view.findViewById(R.id.enter);
        mYesButton.setOnClickListener(this);

        TextView mNoButton = (TextView) view.findViewById(R.id.cancel);
        mNoButton.setOnClickListener(this);


        return view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        twoTouch=false;
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.enter:
                if(!twoTouch){
                    twoTouch=true;
                    onButtonEntered();
                }
                break;
            case R.id.cancel:
                dismissAllowingStateLoss();
                break;

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
    public void onButtonEntered() {
        if (mListener != null) {
            mListener.onEnterAsDelete();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Callback {
         void  onEnterAsDelete();
    }


}
