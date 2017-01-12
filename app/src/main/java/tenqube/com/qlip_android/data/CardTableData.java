package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class CardTableData implements Serializable {

    public int cardId;
    public String cardName;
    public String changeCardName;
    public String totalCardName;

    public int cardType;
    public int changeCardType;
    public int totalCardType;

    public int cardSubType;
    public int changeCardSubType;
    public int totalCardSubType;

    public int exceptType;
    public boolean flag;
    public int billingDate;
    public double totalSum;
    public double monthSum;
    public String memo;
    public String cardImgPath;
    public int priority;
//    public String totalSumName;
    public int deleteType;

    public int isCustom;

    public int serverSuccess;

    public int isRep;
    public long time;

    //    cardName : String,
//    cardType: int,
//    changeCardName : String,
//    changeCardType: int,
//    exceptType,
//    billingDate,(추가 청구기준일),
//    totalSum
//    memo,(추가)


    @Override
    public String toString() {
        return "CardTableData{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", changeCardName='" + changeCardName + '\'' +
                ", totalCardName='" + totalCardName + '\'' +
                ", cardType=" + cardType +
                ", changeCardType=" + changeCardType +
                ", totalCardType=" + totalCardType +
                ", cardSubType=" + cardSubType +
                ", changeCardSubType=" + changeCardSubType +
                ", totalCardSubType=" + totalCardSubType +
                ", exceptType=" + exceptType +
                ", flag=" + flag +
                ", billingDate=" + billingDate +
                ", totalSum=" + totalSum +
                ", monthSum=" + monthSum +
                ", memo='" + memo + '\'' +
                ", cardImgPath='" + cardImgPath + '\'' +
                ", priority=" + priority +
                ", deleteType=" + deleteType +
                ", isCustom=" + isCustom +
                ", serverSuccess=" + serverSuccess +
                ", isRep=" + isRep +
                ", time=" + time +
                '}';
    }
}


