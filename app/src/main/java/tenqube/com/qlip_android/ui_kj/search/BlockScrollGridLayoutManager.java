package tenqube.com.qlip_android.ui_kj.search;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * Created by tenqube on 2016. 2. 3..
 */
public class BlockScrollGridLayoutManager extends GridLayoutManager {

    public BlockScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public BlockScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public BlockScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
    @Override
    public boolean canScrollVertically() {
            return false;
        }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
        }
    }


}

