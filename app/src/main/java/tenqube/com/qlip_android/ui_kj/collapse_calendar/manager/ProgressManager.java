package tenqube.com.qlip_android.ui_kj.collapse_calendar.manager;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.LinearLayout;

import tenqube.com.qlip_android.ui_kj.collapse_calendar.CollapseCalendarView;
import tenqube.com.qlip_android.ui_kj.collapse_calendar.models.AbstractViewHolder;
import tenqube.com.qlip_android.ui_kj.collapse_calendar.models.SizeViewHolder;

/**
 * Created by tenqube on 2016. 12. 30..
 */

public abstract class ProgressManager {

    private static final String TAG = "ProgressManager";

    @NonNull
    protected CollapseCalendarView mCalendarView;

    protected LinearLayout mWeeksView;
    protected AbstractViewHolder[] mViews;

    protected SizeViewHolder mCalendarHolder;
    protected SizeViewHolder mWeeksHolder;

    final int mActiveIndex;

    private boolean mInitialized = false;

    final boolean mFromMonth;

    protected ProgressManager(@NonNull CollapseCalendarView calendarView, int activeWeek, boolean fromMonth) {
        mCalendarView = calendarView;
        mWeeksView = calendarView.getWeeksView();
        mActiveIndex = activeWeek;
        mFromMonth = fromMonth;
    }

    public void applyDelta(float delta) { Log.i("apply",mCalendarView.getHeight()+"");
        float progress = getProgress(getDeltaInBounds(delta));
        apply(progress);
        Log.i(getClass().getSimpleName(),getClass().getSimpleName()+" , "+ delta);
    }

    public void apply(float progress) {

        mCalendarHolder.animate(progress);
        mWeeksHolder.animate(progress);
        // animate views if necessary
        if (mViews != null) {
            for (AbstractViewHolder view : mViews) {
                view.animate(progress);
            }
        }

        // request layout
        mCalendarView.requestLayout();

    }

    public boolean isInitialized() {
        return mInitialized;
    }

    void setInitialized(boolean initialized) {
        mInitialized = initialized;
    }

    public int getCurrentHeight() {
        return mCalendarView.getLayoutParams().height - mCalendarHolder.getMinHeight();
    }

    public int getStartSize() {
        return 0;
    }

    public int getEndSize() {
        return mCalendarHolder.getHeight();
    }

    public abstract void finish(boolean expanded);

    public float getProgress(int distance) {
        Log.i("getProgress",distance +" , "+mCalendarHolder.getHeight());
//        Log.i("getProgress",(distance * 1f / mCalendarHolder.getHeight())+"");
        return Math.max(0, Math.min(distance * 1f / mCalendarHolder.getHeight(), 1));
    }

    protected int getActiveIndex() {
        return mActiveIndex;
    }

    private int getDeltaInBounds(float delta) {

        Log.i("getDeltaInBounds",  (int) Math.max(-mCalendarHolder.getHeight(), Math.min(0, delta)) + mCalendarHolder.getHeight()+"");
        if (mFromMonth) {
            return (int) Math.max(-mCalendarHolder.getHeight(), Math.min(0, delta)) + mCalendarHolder.getHeight();
        } else {
            return (int) Math.max(0, Math.min(mCalendarHolder.getHeight(), delta));
        }

    }

}