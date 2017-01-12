package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class FullSmsTableData implements Serializable {

    public int fullId;
    public String fullSms;
    public long identifier;
    public int smsType;
    public String sender;
    public String originSender;

    @Override
    public String toString() {
        return "FullSmsTableData{" +
                "fullId=" + fullId +
                ", fullSms='" + fullSms + '\'' +
                ", identifier=" + identifier +
                ", smsType=" + smsType +
                ", sender='" + sender + '\'' +
                ", originSender='" + originSender + '\'' +

                '}';
    }
}

