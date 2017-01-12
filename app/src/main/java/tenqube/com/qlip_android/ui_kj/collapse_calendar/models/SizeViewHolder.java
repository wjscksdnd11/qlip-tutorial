package tenqube.com.qlip_android.ui_kj.collapse_calendar.models;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tenqube on 2016. 12. 30..
 */

public class SizeViewHolder extends AbstractViewHolder {

    private int mMinHeight;
    private int mMaxHeight;

    public SizeViewHolder(int minHeight, int maxHeight) {
        mMinHeight = minHeight;
        mMaxHeight = maxHeight;
    }

    public int getHeight() {
        return mMaxHeight - mMinHeight;
    }

    public void setMinHeight(int minHeight) {
        mMinHeight = minHeight;
    }

    public int getMaxHeight() {
        return mMaxHeight;
    }

    public int getMinHeight() {
        return mMinHeight;
    }

    public void setMaxHeight(int maxHeight) {
        mMaxHeight = maxHeight;
    }

    @Override
    public void onFinish(boolean done) {
        Log.i("onFinish",done+"");
        if (done) {
            onShown();
        } else {
            onHidden();
        }
    }

    public void onShown() {
        getView().getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getView().setVisibility(View.VISIBLE);
    }

    public void onHidden() {
        getView().getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getView().setVisibility(View.GONE);
    }

    @Override
    protected void onAnimate(float time) {
        Log.i("onAnimateTime",time+" , ");
        View view = getView();
        view.setVisibility(View.VISIBLE);
        view.getLayoutParams().height = (int) (getMinHeight() + getHeight() * time);
        view.requestLayout();
    }
}