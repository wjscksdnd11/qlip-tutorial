package tenqube.com.qlip_android.util;

public class KeywordFilterUtil {



    public static String replaceKeyword(String keyword){

        keyword = keyword.replace("'","").replace("`","").trim();
        return keyword;
    }

    public static String replaceKeywordWithN(String keyword){

        keyword = keyword.replace("'","").replace("`","").trim();
        if(keyword.length()>2){
            if("Ｎ＿".equals(keyword.substring(0,2))||
                    "N_".equals(keyword.substring(0,2))){

                keyword = keyword.substring(2);
            }
        }
        return keyword;
    }




}
