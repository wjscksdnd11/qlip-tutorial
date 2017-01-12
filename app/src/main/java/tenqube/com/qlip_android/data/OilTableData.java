package tenqube.com.qlip_android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */

public class OilTableData implements Serializable {

    public int oilId;
    public String companyName;
    public String date;
    public int amount;
    public int oilCnt;


    public double liter;
    public double oilPrice;
    public long identifier;
    public String oilCompanyAddress;
    public String deviceId;

    public double cityAvg;
    public double totalAvg;
    public double locationAvg;

    public int isMatched;
    public ArrayList<OilNearByCompanyTableData> closestCompanyList=new ArrayList<>();


    @Override
    public String toString() {
        return "OilTableData{" +
                "oilId=" + oilId +
                ", companyName='" + companyName + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", oilCnt=" + oilCnt +
                ", liter=" + liter +
                ", oilPrice=" + oilPrice +
                ", identifier=" + identifier +
                ", oilCompanyAddress='" + oilCompanyAddress + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", cityAvg=" + cityAvg +
                ", totalAvg=" + totalAvg +
                ", locationAvg=" + locationAvg +
                ", isMatched=" + isMatched +
                ", closestCompanyList=" + closestCompanyList +
                '}';
    }
}
