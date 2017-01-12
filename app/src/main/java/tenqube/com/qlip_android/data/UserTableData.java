package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class UserTableData implements Serializable {

    public int userId;
    public String userEmail;
    public int userSex;
    public int userBirth;
    public int installment;
    public int googleCalendar;
    public int startDate;
    public int alarmBudget;
    public int alarmOneDay;
    public int alarmSpending;
    public int webComic;

    @Override
    public String toString() {
        return "UserTableData{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userSex=" + userSex +
                ", userBirth=" + userBirth +
                ", installment=" + installment +
                ", googleCalendar=" + googleCalendar +
                ", startDate=" + startDate +
                ", alarmBudget=" + alarmBudget +
                ", alarmOneDay=" + alarmOneDay +
                ", alarmSpending=" + alarmSpending +
                ", webComic=" + webComic +
                '}';
    }
}
