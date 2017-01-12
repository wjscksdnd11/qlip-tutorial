package tenqube.com.qlip_android.util;

import tenqube.com.qlip_android.constans.Constants;

/**
 * Created by tenqube on 2016. 10. 4..
 */

public class SmsUtils {

    public static String replaceWebStr(String msg){
        if(msg == null)msg = Constants.NONE;

        msg = msg.replace("[FW]", "");
        msg = msg.replace("FW>", "");
        msg = msg.replace("듀>","");
        msg = msg.replace("[재전송]","");
        msg = msg.replace("[투]","");
        msg = msg.replace("[Web발신]\n", "");
        msg = msg.replace("[Web발신]", "");
        msg = msg.replace("[Web 발신]", "");
        msg = msg.replace("[WEB발신]", "");
        msg = msg.replace("[WEB 발신]", "");
        msg = msg.replace("(재전송)", "");
        msg = msg.replace("[", " ");
        msg = msg.replace("]", " ");

        if(msg.length()>4&&
                msg.substring(0,4).contains("FW")){
            msg = msg.replace("FW", "");
        }
        return msg.replace("\n", " ");
    }

    public static boolean senderFilter(String msg,String sender){

        if(msg == null) return false;
        if(sender == null) return false;


        return (
                msg.contains("증권")||
                        sender.contains("647460607")||
                        sender.contains("15778000")||
                        sender.contains("15884500")||
                        sender.contains("15882588")||
                        sender.contains("16611111")||
                        sender.contains("15887000")||
                        sender.contains("15661000")||
                        sender.contains("69589000")||
                        sender.contains("15995000")||
                        sender.contains("15885000")||
                        sender.contains("15889955")||
                        sender.contains("15884000")||
                        sender.contains("9508510")||
                        sender.contains("15882588")||
                        sender.contains("15776000")||
                        sender.contains("15446000")||
                        sender.contains("16446112")||
                        sender.contains("15776200")||
                        sender.contains("15884560")||
                        sender.contains("15886611")||
                        sender.contains("15991155")||
                        sender.contains("15889500")||
                        sender.contains("15886700")||
                        sender.contains("15883200")||
                        sender.contains("15886700")||
                        sender.contains("15991111")||
                        sender.contains("15443000")||
                        sender.contains("18001111")||
                        sender.contains("69426478")||
                        sender.contains("15440000")||
                        sender.contains("15998800")||
                        sender.contains("15888100")||
                        sender.contains("15999000")||
                        sender.contains("15889999")||
                        sender.contains("15881688")||
                        sender.contains("16449999")||
                        sender.contains("15881788")||
                        sender.contains("15442114")||
                        sender.contains("15662566")||
                        sender.contains("15448800")||
                        sender.contains("15447474")||
                        sender.contains("15880365")||
                        sender.contains("1571418245")||
                        sender.contains("15447000")||
                        sender.contains("20008100")||
                        sender.contains("15888710")||
                        sender.contains("15888700")||
                        sender.contains("15443300")||
                        sender.contains("15888900")||
                        sender.contains("808518282")||
                        sender.contains("622395000")||
                        sender.contains("15886622")||
                        sender.contains("15881515")||
                        sender.contains("15884200")||
                        sender.contains("15661717")||
                        sender.contains("15881900")||
                        sender.contains("37041004")||
                        sender.contains("16444000")||
                        sender.contains("15881600")||
                        sender.contains("15882100")||
                        sender.contains("415499228")||
                        sender.contains("15884500")||
                        sender.contains("15882323")||
                        sender.contains("15889200")||
                        sender.contains("16008585")||
                        sender.contains("15663100")||
                        sender.contains("15881500")||
                        sender.contains("15886655")||
                        sender.contains("522363111")||
                        sender.contains("15660505")||
                        sender.contains("15665050")||
                        sender.contains("15883322")||
                        sender.contains("15886200")||
                        sender.contains("15446200")||
                        sender.contains("15881599")||
                        sender.contains("647200200")||
                        sender.contains("63549110")||
                        sender.contains("15882600")||
                        sender.contains("15660400")||
                        sender.contains("15447200")||
                        sender.contains("15448000")||
                        sender.contains("15666000")||
                        sender.contains("15445000")||
                        sender.contains("438327586")||
                        sender.contains("16610001")||
                        sender.contains("15884488")||
                        sender.contains("15449000")||
                        sender.contains("15883111")||
                        sender.contains("15883400")||
                        sender.contains("647227242")||
                        sender.contains("647460607")||
                        sender.contains("15662210")||
                        sender.contains("15444000")||
                        sender.contains("15666202")||
                        sender.contains("7397560")||

                        sender.contains(Constants.MASTER.MASTER_NUMBER_1)||
                        sender.contains(Constants.MASTER.MASTER_NUMBER_2)||
                        sender.contains(Constants.MASTER.MASTER_NUMBER_3)||
                        sender.contains(Constants.MASTER.MASTER_NUMBER_4)||
                        sender.contains(Constants.MASTER.MASTER_NUMBER_5)||
                        sender.contains("15448800")

        );
    }

}
