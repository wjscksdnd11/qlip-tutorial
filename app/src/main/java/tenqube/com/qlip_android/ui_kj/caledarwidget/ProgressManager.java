package tenqube.com.qlip_android.ui_kj.caledarwidget;

import android.util.Log;

import tenqube.com.qlip_android.ui_kj.caledarwidget.models.AbstractViewHolder;
import tenqube.com.qlip_android.ui_kj.caledarwidget.models.SizeViewHolder;

/**
 * Created by tenqube on 2016. 12. 30..
 */

public abstract class ProgressManager {


    protected CalendarWidgetView mCalendarView;

    protected CalendarWidgetItemView mWeeksView;
    protected AbstractViewHolder[] mViews;

    protected SizeViewHolder mCalendarHolder;
    protected SizeViewHolder mWeeksHolder;

    final int mActiveIndex;

    private boolean mInitialized = false;

    final boolean mFromMonth;

    protected ProgressManager( CalendarWidgetView calendarView, int activeWeek, boolean fromMonth) {
        mCalendarView = calendarView;
        mWeeksView = calendarView.getChildView(0);
        mActiveIndex = activeWeek;
        mFromMonth = fromMonth;
    }

    public void applyDelta(float delta) {
        float progress = getProgress(getDeltaInBounds(delta));
        Log.i("apply",progress+"");
        apply(progress);
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


    public int getEndSize() {
        return mCalendarHolder.getHeight();
    }

    public abstract void finish(boolean expanded);

    public float getProgress(int distance) {
        Log.i("getProgress",distance*1f +" , "+mCalendarHolder.getHeight()+" , "+Math.max(0, Math.min(distance * 1f / mCalendarHolder.getHeight(), 1)));
        return Math.max(0, Math.min(distance * 1f / mCalendarHolder.getHeight(), 1));
    }

    protected int getActiveIndex() {
        return mActiveIndex;
    }

    private int getDeltaInBounds(float delta) {
        Log.i("getDeltaInBounds", (int) Math.max(-mCalendarHolder.getHeight(), Math.min(0, delta)) + mCalendarHolder.getHeight()+" , "+mCalendarHolder.getHeight());
        if (mFromMonth) {
            return (int) Math.max(-mCalendarHolder.getHeight(), Math.min(0, delta)) + mCalendarHolder.getHeight();
        } else {
            return (int) Math.max(0, Math.min(mCalendarHolder.getHeight(), delta));
        }

    }

}