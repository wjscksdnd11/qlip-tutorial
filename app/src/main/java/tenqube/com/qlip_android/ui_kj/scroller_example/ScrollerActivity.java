package tenqube.com.qlip_android.ui_kj.scroller_example;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollerActivity extends AppCompatActivity {
    LinearLayout demoSubview1, demoSubview2, demoViewGroup;
    private Scroller mScrollerViewGroup;
    private Scroller mScrollerView;
//  private Scroller mScroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScrollerViewGroup = new Scroller(this);
        mScrollerView = new Scroller(this);
        demoSubview1 = new DemoView(this);
        demoSubview2 = new DemoView(this);

        demoSubview1.setBackgroundColor(this.getResources().getColor(
                android.R.color.darker_gray));
        demoSubview2.setBackgroundColor(this.getResources().getColor(
                android.R.color.white));
        demoViewGroup = new DemoViewGroup(this);
        demoViewGroup.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        this.setContentView(demoViewGroup, p0);

        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        p1.weight = 1;
        demoViewGroup.addView(demoSubview1, p1);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        p2.weight = 1;
        demoViewGroup.addView(demoSubview2, p2);
        DemoButton btn1 = new DemoButton(this);
        btn1.setText("run  Scroller in viewGroup");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mScrollerViewGroup.startScroll(-10, -100,- 200, -300, 1000);
                new Thread(){
                    public void run() {
                        while(mScrollerViewGroup.computeScrollOffset())// 만약 mScroller 안 호출 startScroll 여기 다시 false 것이다.
                        {
                            Log.i("scroller", "getCurrX()= "+mScrollerViewGroup.getCurrX()+"     getCurrY()="+mScrollerViewGroup.getCurrY());
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    };

                }.start();
            }
        });

        demoSubview1.addView(btn1);
        DemoButton btn2 = new DemoButton(this);
        btn2.setText("run  Scroller in view");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mScrollerView.startScroll(-10, -100,- 200, -300, 1000);
                new Thread(){
                    public void run() {
                        while(mScrollerView.computeScrollOffset())// 만약 mScroller 안 호출 startScroll 여기 다시 false 것이다.
                        {
                            Log.i("scroller", "getCurrX()= "+mScrollerView.getCurrX()+"     getCurrY()="+mScrollerView.getCurrY());
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    };

                }.start();
            }
        });

        demoSubview2.addView(btn2);

    }

    class DemoButton extends Button {
        public DemoButton(Context ctx) {
            super(ctx);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.i("DemoButton",  "------ onDraw------");
        }

        public void computeScroll() {
            Log.i("DemoButton", " --------------------computeScroll-----------");
            //  Log.i(TAG, "getCurrX = " + mScroller.getCurrX());
            if (mScrollerView.computeScrollOffset())// 만약 mScroller 안 호출 startScroll 여기 다시 false 것이다.
            {
                // computeScroll 때문에 호출 함수 것은 MyLinearLayout 인스턴스,
                // 그래서 호출 scrollTo 이동할 것이다. 이 인스턴스 아이, 즉 MyButton 인스턴스
                scrollTo(mScrollerView.getCurrX(), 0);
                Log.i("DemoButton", "getCurrX = " + mScrollerView.getCurrX());

                // 계속 그렇게 시스템 다시 가져오기
                invalidate();
            }
        }
    }

    class DemoView extends LinearLayout {
        public DemoView(Context ctx) {
            super(ctx);
        }

        @Override
        public void computeScroll() {
            Log.i("DemoView", " DemoView --------------------computeScroll-----------");
            if (mScrollerViewGroup.computeScrollOffset())// 만약 mScroller 안 호출 startScroll 여기 다시 false 것이다.
            {
                // computeScroll 때문에 호출 함수 것은 MyLinearLayout 인스턴스,
                // 그래서 호출 scrollTo 이동할 것이다. 이 인스턴스 아이, 즉 MyButton 인스턴스
                scrollTo(mScrollerViewGroup.getCurrX(), 0);
                Log.i("DemoView", "getCurrX = " + mScrollerViewGroup.getCurrX());
                // 계속 그렇게 시스템 다시 가져오기
                getChildAt(0).invalidate();
            }
        }
    }

    class DemoViewGroup extends LinearLayout {
        public DemoViewGroup(Context ctx) {
            super(ctx);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            Log.i("DemoViewGroup", "contentview dispatchDraw");
            super.dispatchDraw(canvas);
        }
    }
}