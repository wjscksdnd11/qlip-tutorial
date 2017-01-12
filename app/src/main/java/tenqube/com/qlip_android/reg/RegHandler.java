package tenqube.com.qlip_android.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tenqube.com.qlip_android.data.RegData;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;
import static tenqube.com.qlip_android.util.SmsUtils.replaceWebStr;
import static tenqube.com.qlip_android.util.SmsUtils.senderFilter;

/**
 * Created by tenqube on 2016. 10. 4..
 */

public class RegHandler {

    private static final String TAG = RegHandler.class.getSimpleName();

    /**
     * 1 카드타입;(0:체크 , 1:신용 2:계좌 3:현금)
       2 카드이름(가로는 바로추가);
       3 사용금액;
       4 사용날짜;
       5 사용처;
       6 할부;
       7 수입지출; 0:수입, 1:지출
       8 취소;
       9 나라;
       10 잔액;
       11 카드서브타입;0:일반 1:법인 2:가족
       12 계좌번호

     해당값이 없는경우 빈칸으로 놓음.

     \s*(\d{2}/\d{2}\s*\d{2}:\d{2})\s*\w*[0-9]*[-][0-9]*[-]*[0-9*]*[-]*[0-9]*\s*([현금]*)\w*\s*([0-9,]*)\s*(.*)

     3;KB,(,1,);4;1;;;0;;;;0;2
     */


//            입금 1 30 000원//501-******-51707/잔액271 622원
//			입금 150 000원//501-******-51707/잔액332 772원
//			입금취소 260,000원/차옥순/263-******-40707/잔액7,558,098원
//			출금취소 260,000원/차옥순/263-******-40707/잔액7,558,098원
//        \s*([출금입금취소]*)\s*(\w*[0-9,]*|\w*[0-9,]*\s[0-9,]*\s[0-9,]*)원\/(|\S*\s*)\/\w*[0-9]*[-][*]*[-]*[0-9]*\/\s*(.*)


//        (8*5*)박*우님 09/10 22:51/해외승인/1500원/TRAVEL RESERVATION KORSOUTH KOREA
//        cs*(\S\*\S\s*)\님\s*(\d{2}/\d{2}\s*\d{2}:\d{2})\/(\S*[승인])\/\s*([0-9,]*)원\/\s*(.*)
//			체크카드(4*3*)김*미님 09/22 17:10/일시불/승인/24,740원 P120점/G마켓(무이자)
//        (\S*[카드])(\(\S*\s*\))(\S\*\S\s*)\님\s*(\d{2}/\d{2}\s*\d{2}:\d{2})\/(\S*[승인])\/\s*([0-9,]*)원\s*(.*)
//
//        (7*3*)이*희님 09/26 13:10/일시불/승인/6,500원/라온케�누적이용금액 277,028원
//        (\(\S*\s*\))(\S\*\S\s*)\님\s*(\d{2}/\d{2}\s*\d{2}:\d{2})\/(\s*[개월일시불신용전환]*)\/(\S*[승인])\/\s*([0-9,]*)원\s*(.*)
//
//        체크카드(3*3*)최*재님 09/26 21:06/일시불/승인/19,940원 P2,997점/11번가
//        (\S*[카드])(\(\S*\s*\))(\S\*\S\s*)\님\s*(\d{2}/\d{2}\s*\d{2}:\d{2})\/(\S*[승인])\/\s*([0-9,]*)원\s*(.*)
//			(8*1*) 이*철님 09/27 02:50/장기카드대출/승인/1,500,000원 신용등급 상향비, 대출진행비 등 요구하면 100% 사기!
//        (\(\S*\s*\))\s*(\S\*\S\s*)\님\s*(\d{2}/\d{2}\s*\d{2}:\d{2})\/(\s*[장기카드대출]*)\/(\S*[승인])\/\s*([0-9,]*)원\s*(.*)





    public RegHandler() {

    }


    public  void regPractice(){

        String regExpression;
        String msg;
        String sender;
        RegData regData;




        msg = "10/06 13:12 819-24-0***-409 현금입금 43,440 잔액671,961";
//        regExpression = "\\s*([0-9])";

        regExpression = "\\s*(\\d{2}/\\d{2}\\s*\\d{2}:\\d{2})\\s*([0-9*-]*)\\s*([현금]*)\\w*\\s*([0-9,]*)\\s*.*";
        sender = "18001111";
        regData = doParsing(msg,sender, regExpression);
        LOGI(TAG,regExpression);
        if(regData != null){
            LOGI(TAG, regData.toString());
        }


        //
//     sender:
//      18001111





    }



    public RegData doParsing(String msg, String sender, String regExpression){

        RegData regData = null;


        if(senderFilter(msg, sender)){

            try{
                Pattern p1 =    Pattern.compile(regExpression, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE | Pattern.UNICODE_CASE);

                Matcher matcher = p1.matcher(replaceWebStr(msg));
                if(matcher.matches()){
                    regData = new RegData();
                    LOGI(TAG,"pos 0:"+matcher.group(0));
                    LOGI(TAG,"pos 1:"+matcher.group(1));
                    LOGI(TAG,"pos 2:"+matcher.group(2));
                    LOGI(TAG,"pos 3:"+matcher.group(3));
                    LOGI(TAG,"pos 4:"+matcher.group(4));
                    LOGI(TAG,"pos 5:"+matcher.group(5));
                    LOGI(TAG,"pos 6:"+matcher.group(6));
                    LOGI(TAG,"pos 7:"+matcher.group(7));




                }else {
                    LOGI(TAG,"not matches");
                }


            }catch(Exception e){

            }
        }



        return  regData;

    }
}
