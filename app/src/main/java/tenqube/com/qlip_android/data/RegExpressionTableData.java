package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * Created by tenqube on 16. 2. 25..
 */
public class RegExpressionTableData implements Serializable {

    public long regId;
    public String regExpression;
    public String sender;
    public String createDate;
    public String mappingOrder;
    public int regType;

    @Override
    public String toString() {
        return "RegExpressionTableData{" +
                "regId=" + regId +
                ", regExpression='" + regExpression + '\'' +
                ", sender='" + sender + '\'' +
                ", createDate='" + createDate + '\'' +
                ", mappingOrder='" + mappingOrder + '\'' +
                ", regType=" + regType +
                '}';
    }
}
