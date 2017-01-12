package tenqube.com.qlip_android.util;

import android.content.res.Resources;

/**
 * Created by tenqube on 2016. 11. 23..
 */

public class Utils {

    /**
     * convert dp to px
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
