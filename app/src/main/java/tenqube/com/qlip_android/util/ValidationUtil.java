package tenqube.com.qlip_android.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Pattern;


public class ValidationUtil {



    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if(source != null&&
                    !TextUtils.isEmpty(source.toString())&&
                    (
                    source.toString().contains("'")||
                    source.toString().contains("`")||
                    source.toString().contains(";")||
                    source.toString().contains("\n")||
                    source.toString().contains("\"")
                    )
                    ){
                return  "";
            }

            return null;
        }
    };

    public static InputFilter filterNum = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {

            Pattern ps = Pattern.compile("^[0-9-]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
}
