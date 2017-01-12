package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * Created by tenqube on 16. 2. 25..
 */
public class RegData implements Serializable {

    public int cardType;
    public int cardSubType;
    public String cardName;
    public int dwType;
    public String currency;
    public int installmentCount;
    public String spentDate;
    public double spentMoney;

    public String memo;
    public String keyword;
    public double totalAmount;
    @Override
    public String toString() {
        return "RegData{" +
                "cardType=" + cardType +
                ", cardName='" + cardName + '\'' +
                ", dwType=" + dwType +
                ", currency='" + currency + '\'' +
                ", installmentCount=" + installmentCount +
                ", spentDate='" + spentDate + '\'' +
                ", spentMoney=" + spentMoney +
                ", totalAmount=" + totalAmount +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
