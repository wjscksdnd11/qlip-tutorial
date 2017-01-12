package tenqube.com.qlip_android.ui_kj.caledarwidget;

import android.content.Context;

import java.text.DecimalFormat;

public class Common {
    public static float dp2px(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static String comma_won(int comma) {
        String temp  = String.valueOf(comma);
        String result_int =temp;
        if (comma<1000000 &&comma>-1000000) {
            DecimalFormat Commas = new DecimalFormat("#,###");
            result_int = Commas.format(comma);
        }
        else if (comma >= 1000000 && comma<100000000||comma<=-1000000 &&comma>-100000000) {
            int len = temp.length();
            result_int = temp.substring(0,len-4)+"만원";
        } else if(comma >=100000000 ||comma<= -100000000){
            int len = temp.length();
            result_int = temp.substring(0,len-8)+"억";
        }
        return result_int;
    }
}
