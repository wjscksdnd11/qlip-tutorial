package tenqube.com.qlip_android.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class BackPressEditText extends EditText
{
    private OnBackPressListener _listener;


    public BackPressEditText(Context context)
    {
        super(context);
    }


    public BackPressEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    public BackPressEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && _listener != null)
        {
            _listener.onBackPress();
            return  false;
        }else{
            return super.onKeyPreIme(keyCode, event);
        }


    }


    public void setOnBackPressListener(OnBackPressListener $listener)
    {
        _listener = $listener;
    }

    public interface OnBackPressListener
    {
        void onBackPress();
    }
}