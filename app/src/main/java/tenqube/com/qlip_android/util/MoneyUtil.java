package tenqube.com.qlip_android.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyUtil {



    public  static String convertLv1Money(double value) {

        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        if (value == 0) {
            return "0 원";
        }  else if (value < 10000) {
            String price;

            price = decimalFormat.format(value);

            return price + "원";

        } else {
            double man = Math.floor(value / 10000);
            double won = value % 10000;
            String man2 = decimalFormat.format(man) + "만";



            return man2 + " " + decimalFormat.format(won)+"원";
        }

    }

    public  static String convertLv1MoneyAsPlus(double value) {

        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        if (value == 0) {
            return "0 원";
        }  else if (value < 10000) {
            String price;

            price = value>0?"+"+decimalFormat.format(value):decimalFormat.format(value);

            return price + "원";

        } else {
            double man = Math.floor(value / 10000);
            double won = value % 10000;
            String man2 = decimalFormat.format(man) + "만";


            String result=man2 + " " + decimalFormat.format(won)+"원";

            return value>0?"+"+result:result;
        }

    }
    public  static String convertLv0Comma(double value) {
        String pattern = "###,###.#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String price;
            if (value == 0) {
                return "0.0 만원";
            }else {

                double temp=value/10000;
                price = decimalFormat.format(temp)+"만원";


            }



        return price;
    }


    public  static String convertPercentComma(double value) {
        String pattern = "###,###.#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

            if (value == 0) {
                return "0.0 %";
            }else {
                return decimalFormat.format(value)+"%";
            }

    }

    public  static String plusThreeComma(double value) {
        if (value == 0) {
            return "0원";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String price ;
        price = nf.format((long)value)+"원";
        if(value>0)price="+"+price;
        return  price;

    }
    public  static String plusThreeCommaNoUnit(double value) {
        if (value == 0) {
            return "0";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String price ;
        price = nf.format((long)value)+"";
        if(value>0)price="+"+price;
        return  price;

    }
    public  static String ThreeComma(double value) {
        if (value == 0) {
            return "0원";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String price ;
        price = nf.format((long)value)+"원";
        return  price;

        }


    public  static String converThreeCommaNotUnitAsHeader(double value) {

        if (value == 0) {
            return "₩ 0";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String price;
        price = nf.format((long)value);
        return "₩ "+price;
    }

    public  static String converThreeCommaNotUnit(double value) {

        if (value == 0) {
            return "0";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String price;
        price = nf.format((long)value);
        return price + "";
    }
    public  static String converThreeCommaFirstNotUnit(double value) {
        String pattern = "###,###.#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        if (value == 0) {
            return "0";
        }
        String price;
        price = decimalFormat.format(value);
        return price ;
    }

    public  static String converTenThousandWon(double value) {
        String price;
        NumberFormat nf = NumberFormat.getInstance();
        if (value == 0) {
            return "0원";
        } else {
            if (value >= 10000) {
                price = nf.format(Math.floor((long)value / 10000)) + "만";
            } else {
                price = nf.format((long)value);
            }
            return price + "원";

        }


    }

    /**
     * 11월 30일기준
     * @param money
     * @param trans
     * @return
     */
    public static double getTransMoney(double money, String trans) {

        if ("USD".equals(trans)) {
            return (long)( money * 1166.86);
        } else if ("THB".equals(trans)) {
            return (long)(money * 32.77);
        } else if ("JPY".equals(trans)) {
            return  (long)(money * 10.39);
        } else if ("CNY".equals(trans)) {
            return  (long)(money * 169.58);
        } else if ("RMB".equals(trans)) {
            return  (long)(money * 169.58);
        } else if ("TWD".equals(trans)) {
            return  (long)(money * 36.68);
        } else if ("AUD".equals(trans)) {
            return  (long)(money * 873.33);
        } else if ("NZD".equals(trans)) {
            return  (long)(money * 834.61);
        } else if ("SGD ".equals(trans)) {
            return  (long)(money * 819.59);
        } else if ("HKD".equals(trans)) {
            return  (long)(money * 150.45);
        } else if ("PHP".equals(trans)) {
            return  (long)(money * 23.46);
        } else if ("IDR".equals(trans)) {
            return  (long)(money * 0.08);
        } else if ("INR ".equals(trans)) {
            return  (long)(money * 17.005);
        } else if ("MYR".equals(trans)) {
            return (long)( money * 261.49);
        } else if ("EUR".equals(trans)) {
            return  (long)(money * 1242.59);
        }else if("GBP".equals(trans)){
            return (money*1456.62);
        }else if("VND".equals(trans)){
            return (long)(money*0.05);
        }else if("CAD".equals(trans)){
            return (long)(money*868.81);
        }else if("DKK".equals(trans)){
            return (long)(money*167.005);
        }else{
            return  (long)money;
        }

    }

    public static String convertCountry(String trans){
         if("엔".equals(trans)){
            return "JPY";
        }else if("위안".equals(trans)){
            return  "CNY";
        }else if("유로".equals(trans)){
            return  "EUR";
        }else{
             return  trans;
         }
    }
}
