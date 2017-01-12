package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class GoogleCalendarTableData implements Serializable {

    public int GID;
    public String googleCodeStr;
    public String googleDateStr;
    public String googleEmailStr;
    public int googleType;
    public String calendarIdStr;
    public int dwType;


    @Override
    public String toString() {
        return "GoogleCalendarTableData{" +
                "GID=" + GID +
                ", googleCodeStr='" + googleCodeStr + '\'' +
                ", googleDateStr='" + googleDateStr + '\'' +
                ", googleEmailStr='" + googleEmailStr + '\'' +
                ", googleType=" + googleType +
                ", calendarIdStr='" + calendarIdStr + '\'' +
                ", dwType=" + dwType +
                '}';
    }
}
