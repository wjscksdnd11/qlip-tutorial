package tenqube.com.qlip_android.ui_kj.caledarwidget;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import tenqube.com.qlip_android.ui_kj.caledarwidget.models.AbstractViewHolder;
import tenqube.com.qlip_android.ui_kj.caledarwidget.models.SizeViewHolder;
import tenqube.com.qlip_android.ui_kj.caledarwidget.models.StubViewHolder;

/**
 * Created by tenqube on 2016. 12. 30..
 */
public class ProgressManagerImpl extends ProgressManager {

    public ProgressManagerImpl(CalendarWidgetView calendarView, int activeWeek, boolean fromMonth) {
        super(calendarView, activeWeek, fromMonth);

        if (!fromMonth) {
            initMonthView();
        } else {
            initWeekView();
        }

    }

    @Override
    public void finish(final boolean expanded) {

        mCalendarView.post(new Runnable() { // to prevent flickering
            @Override
            public void run() {
                mCalendarView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mWeeksView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                for (AbstractViewHolder view : mViews) {
                    view.onFinish(true);
                }


                if (!expanded) {
                    CalendarManager manager = mCalendarView.getManager();
                    if (mFromMonth) {
                        manager.toggleView();
                    } else {
                        manager.toggleToWeek();
                    }
                }
            }
        });
    }

    private void initMonthView() {
        Log.i("initWeekView","initWeekView");
        mCalendarHolder = new SizeViewHolder(mCalendarView.getHeight(),0 );
        mCalendarHolder.setView(mCalendarView);
        mCalendarHolder.setDelay(0);
        mCalendarHolder.setDuration(1);
        mWeeksHolder = new SizeViewHolder(mCalendarView.getChildAt(0).getHeight(),0);
        mWeeksHolder.setView(mWeeksView);
        mWeeksHolder.setDelay(0);
        mWeeksHolder.setDuration(1);

        initializeChildren();

        mCalendarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mCalendarView.getViewTreeObserver().removeOnPreDrawListener(this);
                mCalendarHolder.setMinHeight(mWeeksView.getHeight());
                mWeeksHolder.setMinHeight(mWeeksView.getHeight());
                mCalendarView.getLayoutParams().height = mCalendarHolder.getMaxHeight();
                mWeeksView.getLayoutParams().height = mCalendarHolder.getMaxHeight();

                setInitialized(true);

                return false;
            }
        });
    }
    private void initWeekView() {
        Log.i("initWeekView",mCalendarView.getHeight()+"");
        mCalendarHolder = new SizeViewHolder(0,mCalendarView.getHeight() );
        mCalendarHolder.setView(mCalendarView);
        mCalendarHolder.setDelay(0);
        mCalendarHolder.setDuration(1);
        mWeeksHolder = new SizeViewHolder(0, mCalendarView.getChildView(0).getHeight());
        mWeeksHolder.setView(mWeeksView);
        mWeeksHolder.setDelay(0);
        mWeeksHolder.setDuration(1);
        Log.i("onPreDraw",mCalendarHolder.getHeight()+" 위크뷰의 높이 ");

        initializeChildren();

        mCalendarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mCalendarView.getViewTreeObserver().removeOnPreDrawListener(this);

                mCalendarHolder.setMinHeight(mCalendarView.getHeight());
                mWeeksHolder.setMinHeight(mWeeksView.getHeight());
                mCalendarView.getLayoutParams().height = mCalendarHolder.getMaxHeight();
                mWeeksView.getLayoutParams().height = mCalendarHolder.getMaxHeight();
                Log.i("onPreDraw",mCalendarHolder.getHeight()+" 위크뷰의 max 높이 "+ mCalendarView.getHeight());
                setInitialized(true);

                return false;
            }
        });
    }

    private void initializeChildren() {

            int childCount = 6;

            // FIXME do not assume that all views are the same height
            mViews = new AbstractViewHolder[childCount];
            for (int i = 0; i < childCount; i++) {

                View view = mCalendarView.getChildView(i);
                int activeIndex = getActiveIndex();
                AbstractViewHolder holder;
                if (i == activeIndex) {
                    holder = new StubViewHolder();
                } else {
                  SizeViewHolder tmpHolder = new SizeViewHolder(0, view.getHeight());
                    final int duration = mWeeksHolder.getMaxHeight() - view.getHeight();
                    if (i < activeIndex) {
                        tmpHolder.setDelay(view.getTop() * 1.0f / duration);
                    } else {
                        tmpHolder.setDelay((view.getTop() - view.getHeight()) * 1.0f / duration);
                    }
                    tmpHolder.setDuration(view.getHeight() * 1.0f / duration);

                    holder = tmpHolder;

                    view.setVisibility(View.GONE);
                }

                holder.setView(view);

                mViews[i] = holder;
            }



    }

}