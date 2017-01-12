package tenqube.com.qlip_android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class UserTransactionData implements Serializable {


    public CardTableData cardTableData;
    public String cardName;
    public int cardType;
    public int cardSubType;

    public String spentDate;
    public double spentMoney;
    public String keyword;
    public int repeatType;
    public int installmentCount;
    public String currency;
    public long identifier;
    public int tranType;

    public double spentLatitude;
    public double spentLongitude;
    public int exceptCard;
    public String categoryCode;
    public long cardId;
    public long categoryConfigId;
    public long smsId;
    public long userId;
    public String fran;
    public String companyAddress;
    public int serverSuccess;
    public int dwType;
    public boolean isDuplicate;
    public String memo;
    public int isUpdateAll;
    public int isUserUpdate;
    public long referencedIdentifier;

    public String largeCategory;
    public String mediumCategory;

    public ArrayList<String> tagList = new ArrayList<>();


    public String fullSms;
    public int smsType;
    public String smsDate;
    public String sender;
    public String originSender;

    public double totalSum;

    public int isOffset;
    public int isCustom;

    public boolean isChanged;
    public boolean isExcepted;

    @Override
    public String toString() {
        return "UserTransactionData{" +
                "cardName='" + cardName + '\'' +
                ", cardType=" + cardType +
                ", cardSubType=" + cardSubType +
                ", spentDate='" + spentDate + '\'' +
                ", spentMoney=" + spentMoney +
                ", keyword='" + keyword + '\'' +
                ", repeatType=" + repeatType +
                ", installmentCount=" + installmentCount +
                ", currency='" + currency + '\'' +
                ", identifier=" + identifier +
                ", tranType=" + tranType +
                ", spentLatitude=" + spentLatitude +
                ", spentLongitude=" + spentLongitude +
                ", exceptCard=" + exceptCard +
                ", categoryCode='" + categoryCode + '\'' +
                ", cardId=" + cardId +
                ", categoryConfigId=" + categoryConfigId +
                ", smsId=" + smsId +
                ", userId=" + userId +
                ", fran='" + fran + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", serverSuccess=" + serverSuccess +
                ", dwType=" + dwType +
                ", isDuplicate=" + isDuplicate +
                ", memo='" + memo + '\'' +
                ", isUpdateAll=" + isUpdateAll +
                ", isUserUpdate=" + isUserUpdate +
                ", referencedIdentifier=" + referencedIdentifier +
                ", largeCategory='" + largeCategory + '\'' +
                ", mediumCategory='" + mediumCategory + '\'' +
                ", tagList=" + tagList +
                ", fullSms='" + fullSms + '\'' +
                ", smsType=" + smsType +
                ", smsDate='" + smsDate + '\'' +
                ", sender='" + sender + '\'' +
                ", originSender='" + originSender + '\'' +
                ", totalSum=" + totalSum +
                ", isOffset=" + isOffset +
                ", isCustom=" + isCustom +
                ", isChanged=" + isChanged +
                ", isExcepted=" + isExcepted +
                '}';
    }
}
