package tenqube.com.qlip_android.ui_kj.caledarwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.R;

public class CalendarWidgetView extends ViewGroup {
    private final int mScreenWidth;
    private final int mWidthDate;
    private long mMillis;
    private int mDateOfWeek;
    public static String[] DAY_OF_WEEK = null;
    private ResizeManager mResizeManager;
    private onCalendarItemSelectedListener mOnCalendarItemSelectedListener;
    private CalendarManager mManager;
    private ArrayList<CalendarWidgetItemView> mWeekView = new ArrayList<>();

    public CalendarWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mWidthDate = mScreenWidth / 7;
        DAY_OF_WEEK = getResources().getStringArray(R.array.day_of_week);
        mResizeManager = new ResizeManager(this);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();//차일드 개수 캘린더 일수
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int mLeftWidth = 0;
        int rowCount = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mMillis);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);//차일드에 하나씩 접근

            if (child.getVisibility() == GONE)
                continue;
            maxWidth += Math.max(maxWidth, child.getMeasuredWidth());
            mLeftWidth += child.getMeasuredWidth();// onDraw 전에 뷰의 크기를 할당;

            if ((mLeftWidth / mScreenWidth) > rowCount) {
                maxHeight += child.getMeasuredHeight();
                rowCount++;
            } else {
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }
        maxHeight = (int) (Math.ceil((count + mDateOfWeek - 1) / 7d) * (mWidthDate * 1.1));// 요일중 일요일이 1부터 시작하므로 1을 빼줌
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, expandSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
        LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;
        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE)
                return;
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = mWidthDate - 1;
            curHeight = (int) (mWidthDate * 1.1);
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            if (i < 7) {
                curHeight = (int) (curHeight * 0.9);

            }
            child.layout(curLeft + 10, curTop, curLeft + curWidth, curTop + curHeight);

            if (maxHeight < curHeight) {
                maxHeight = curHeight;
            }
            curLeft += curWidth;
        }
    }

    public void setDate(long millis) {
        mMillis = millis;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.set(Calendar.DATE, 1);
        mDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    public void setCurrentSelectedView(View view) {
        if (getRootView().findViewById(R.id.pager) instanceof ViewPager) {
            ViewPager pager = (ViewPager) getRootView().findViewById(R.id.pager);
            View tagView = (View) pager.getTag();
            if (tagView != null) {
                long time = (long) tagView.getTag();

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(time);
                for (int i = 0; i < pager.getChildCount(); i++) {
                    for (int j = 0; j < getChildCount(); j++) {
                        CalendarWidgetItemView child = (CalendarWidgetItemView) ((CalendarWidgetView) pager.getChildAt(i).findViewById(R.id.calendar_widget)).getChildAt(j);
                        if (child == null) {
                            continue;
                        }
                        if (child.isSameDay((Long) child.getTag(), (Long) tagView.getTag())) {

                            child.invalidate();
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < pager.getChildCount(); i++) {
                    for (int j = 0; j < getChildCount(); j++) {
                        CalendarWidgetItemView child = (CalendarWidgetItemView) ((CalendarWidgetView) pager.getChildAt(i).findViewById(R.id.calendar_widget)).getChildAt(j);
                        if (child.checkToday) {
                            child.todayFlag = false;
                            child.invalidate();
                            break;
                        }
                    }
                }
            }
            if (tagView == view) {
                onReceiveSelectedCalendarItem((Long) view.getTag(), view, true);
                return;
            }
            long time = (long) view.getTag();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            pager.setTag(view);
            view.invalidate();
        }
    }


    public void onReceiveSelectedCalendarItem(long millis, View v, boolean isDoubleClicked) {

        if (mOnCalendarItemSelectedListener != null) {
            mOnCalendarItemSelectedListener.onSelectedItem(millis, v, isDoubleClicked);
        }
    }



    public interface onCalendarItemSelectedListener {
        void onSelectedItem(long millis, View v, boolean isDoubleClicked);
    }

    public void setOnCalendarItemSelectedListener(onCalendarItemSelectedListener mOnCalendarItemSelectedListener) {
        this.mOnCalendarItemSelectedListener = mOnCalendarItemSelectedListener;
    }


    public CalendarManager getManager() {
        return mManager;
    }


    public void init( CalendarManager manager) {
        if (manager != null) {
            mManager = manager;
        }
    }

    public void addChildView(CalendarWidgetItemView calendarWidgetItemView){
        mWeekView.add(calendarWidgetItemView);
    }
    public CalendarWidgetItemView getChildView(int position){
        return mWeekView.get(position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mResizeManager.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mResizeManager.onInterceptTouchEvent(ev);
    }
    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        Log.i("resizeManager","dispatchDraw");
        super.dispatchDraw(canvas);
        mResizeManager.onDraw();

    }

}
