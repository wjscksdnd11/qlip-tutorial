package tenqube.com.qlip_android.ui_kj.caledarwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.data.UserTransactionData;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;

public class CalendarWidgetItemView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintExtraText = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBound = new Paint(Paint.ANTI_ALIAS_FLAG);
    private long mMillis;
    private Rect rect;
    private boolean isTouchMode;
    private int dp12;
    private boolean isThisMonth = false;
    public boolean checkToday = false;
    public boolean todayFlag = false;
    private int checkSum;
    private int position;
    private ArrayList<UserTransactionData> spent;
    private boolean isSpent;

    public CalendarWidgetItemView(Context context) {
        super(context);
        initialize();
    }

    public CalendarWidgetItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {

        dp12 = (int) Common.dp2px(getContext(), 12);
        int dp10 = (int) Common.dp2px(getContext(), 10);
        int dp8 = (int) Common.dp2px(getContext(), 8);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.qlip_main_text));
        mPaint.setTextSize(dp12);
        mPaintBackground.setColor(ContextCompat.getColor(getContext(), R.color.qlip_main_dark_3));
        mPaintBound.setColor(ContextCompat.getColor(getContext(), R.color.white));
        mPaintBound.setAlpha(70);
        mPaintExtraText.setColor(ContextCompat.getColor(getContext(), R.color.qlip_main_text));
        mPaintExtraText.setTextSize(dp8);
        mPaintText.setTextSize(dp10);
        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isTouchMode = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isTouchMode) {
                            ((CalendarWidgetView) getParent()).setCurrentSelectedView(v);
                            isTouchMode = false;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isTouchMode = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            isTouchMode = false;
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        setPadding(30, 0, 30, 0);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2)) - 20;
        mPaint.setTextAlign(Paint.Align.CENTER);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mMillis);
        if (getRootView().findViewById(R.id.pager) instanceof ViewPager) {
            ViewGroup parent = (ViewPager) getRootView().findViewById(R.id.pager);
            CalendarWidgetItemView tagView = (CalendarWidgetItemView) parent.getTag();
            if (tagView != null && tagView.getTag() != null && tagView.getTag() instanceof Long) {
                long millis = (long) tagView.getTag();

                if (isSameDay(millis, this.mMillis)) {
                    canvas.drawRect(0, 0, getWidth(), getHeight(), mPaintBound);
                    canvas.drawRect(1, 1, getWidth() - 1, getHeight() - 1, mPaintBackground);
                    ((CalendarWidgetView) getParent()).onReceiveSelectedCalendarItem(millis, this, false);
                }
            } else {
                todayFlag = true;
            }
        }
        if (!isThisMonth) {
            this.setAlpha(0.3f);
        }

        if (isToday(mMillis) && todayFlag) {

            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaintBound);
            canvas.drawRect(1, 1, getWidth() - 1, getHeight() - 1, mPaintBackground);
            checkToday = true;
            ((CalendarWidgetView) getParent()).onReceiveSelectedCalendarItem(mMillis, this, false);
        }

        // 날짜 표시
        mPaint.setColor(ContextCompat.getColor(getContext(), setPositionColor(position)));
        canvas.drawText(calendar.get(Calendar.DATE) + "", xPos / 3 + 5, yPos / 3 + 10, mPaint);

        // 데이터 표시
        if (isSpent) {
            LOGI(getClass().getSimpleName(), spent.size() + " Calendar date dataSize ");
            int height = getHeight() / 5;
            int dataYPos = yPos * 2 / 3;
            xPos = 5;
            int spentSum = 0;
            int incomeSum = 0;
            int spentSize = spent.size();
            if (checkSum != 0 && spentSize > 3) {
                //10의 자리를 넘어갈때 여분 텍스트가 좀 앞으로 와야하기 때문에
                setExtraText(canvas,spentSize,yPos);
            }

            for (int i = 0; i < spentSize; i++) {


                switch (checkSum) {
                    case 0:

                        if (spent.get(i).dwType == 1) {
                            spentSum += spent.get(i).spentMoney;
                        } else {
                            incomeSum += spent.get(i).spentMoney;
                        }
                        break;
                    case 1:
                        if (i == 3) break;
                        mPaintText.setColor(ContextCompat.getColor(getContext(), setDwColor(spent.get(i).dwType)));
                        if (spent.get(i).dwType == 1) {
                            canvas.drawText(Common.comma_won((-1) * (int) spent.get(i).spentMoney), xPos, dataYPos + height, mPaintText);
                        } else {
                            canvas.drawText("+" + Common.comma_won((int) spent.get(i).spentMoney), xPos, dataYPos + height, mPaintText);
                        }
                        break;
                    case 2:
                        if (i == 3) break;
                        mPaintText.setColor(ContextCompat.getColor(getContext(), setDwColor(spent.get(i).dwType)));
                        canvas.drawText(spent.get(i).keyword + "", xPos, dataYPos + height, mPaintText);
                        break;

                }

                height += dp12;
            }
            height = getHeight() / 5;
            if (checkSum == 0) {
                if (spentSum != 0 && spentSum > 0) {
                    mPaintText.setColor(ContextCompat.getColor(getContext(), R.color.qlip_main_green));
                    canvas.drawText(Common.comma_won((-1) * spentSum) + "", xPos, dataYPos + height, mPaintText);
                    height += dp12;
                }
                mPaintText.setColor(ContextCompat.getColor(getContext(), R.color.qlip_red));
                if (incomeSum != 0 && incomeSum > 0) {
                    canvas.drawText("+" + Common.comma_won(incomeSum), xPos, dataYPos + height, mPaintText);
                }
            }

        }
    }

    public void addText(ArrayList<UserTransactionData> spentmoney) {
        this.spent = spentmoney;
        isSpent = true;
    }

    public boolean isToday(long millis) {
        Calendar cal1 = Calendar.getInstance();
        return isSameDay(cal1.getTimeInMillis(), millis);
    }

    public void setDate(long millis) {
        this.mMillis = millis;
        setTag(millis);
    }

    public int setPositionColor(int position) {
        if (position % 7 == 0) {
            return R.color.qlip_red;
        } else {
            return R.color.white;
        }
    }

    public int setDwColor(int dwType) {
        if (dwType == 1) {
            return R.color.qlip_main_green;
        } else {
            return R.color.qlip_red;
        }
    }

    public View getPosition(int position) {
        if (this.position ==position){
            return this;
        }
        return null;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public boolean isSameDay(long millis1, long millis2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(millis1);
        cal2.setTimeInMillis(millis2);
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
    }


    public void setIsThisMonth(boolean isThisMonth) {
        this.isThisMonth = isThisMonth;
    }

    public void checkSumType(int checkSum) {
        this.checkSum = checkSum;
    }

    public void setExtraText(Canvas canvas,int spentSize, int yPos) {
        if (spent.size() - 3 < 10) {
            canvas.drawText("+" + (spentSize - 3), getWidth() * 4 / 5, yPos / 3, mPaintExtraText);
        } else {
            canvas.drawText("+" + (spentSize - 3), getWidth() * 4 / 6, yPos / 3, mPaintExtraText);
        }
    }
}