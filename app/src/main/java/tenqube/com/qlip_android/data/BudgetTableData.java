package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class BudgetTableData implements Serializable {

    public int budgetId;
    public double budget;
    public String categoryCode;
    public int budgetYear;
    public int budgetMonth;

    public int serverSuccess;


    @Override
    public String toString() {
        return "BudgetTableData{" +
                "budgetId=" + budgetId +
                ", budget=" + budget +
                ", categoryCode='" + categoryCode + '\'' +
                ", budgetYear=" + budgetYear +
                ", budgetMonth=" + budgetMonth +
                ", serverSuccess=" + serverSuccess +
                '}';
    }
}
