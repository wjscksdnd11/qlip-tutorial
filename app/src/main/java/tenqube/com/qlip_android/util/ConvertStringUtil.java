package tenqube.com.qlip_android.util;


/**
 * Created by sagwangjin on 2015. 12. 30..
 */
public class ConvertStringUtil {




    public static String getDateDay(int dayNum) {//date input('2014-12-04)  수요일 리턴


        String day = "";

        switch (dayNum) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";

                break;
            case 7:
                day = "토";
        }

        return day;

    }

    public static String getAm(int am){
        if (am==0){
            return "오전";
        }else{
            return "오후";

        }
    }


}
