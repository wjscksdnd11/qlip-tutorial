package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static tenqube.com.qlip_android.util.LogUtil.LOGE;


class BlockScrollLinearLayoutManager extends LinearLayoutManager {
    BlockScrollLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            LOGE("probe", "meet a IOOBE in RecyclerView");
        }
    }
}
