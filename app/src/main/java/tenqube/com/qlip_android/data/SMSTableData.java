package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class SMSTableData implements Serializable {

    public int smsId;
    public int androidId;
    public String smsDate;
    public String smsMsg;
    public String sender;
    public int smsType;
    public int sendServer;
    public long identifier;
    public boolean flag;
    public String title;

    @Override
    public String toString() {
        return "SMSTableData{" +
                "smsId=" + smsId +
                ", androidId=" + androidId +
                ", smsDate='" + smsDate + '\'' +
                ", smsMsg='" + smsMsg + '\'' +
                ", sender='" + sender + '\'' +
                ", smsType=" + smsType +
                ", sendServer=" + sendServer +
                ", identifier=" + identifier +
                ", flag=" + flag +
                '}';
    }
}
