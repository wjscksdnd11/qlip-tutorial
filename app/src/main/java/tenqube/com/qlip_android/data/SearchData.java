package tenqube.com.qlip_android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class SearchData implements Serializable {

    public ArrayList<UserCategoryConfigTableData> categoryList=new ArrayList<>();
    public ArrayList<CardTableData> cardList=new ArrayList<>();
    public ArrayList<TagTableData> hashList=new ArrayList<>();
    public int usd;
    public int domestic;
    public int installment;
    public int repeat;
    public String keyword;
    public int startDay;
    public int dateType;//미래 또는 과거
    @Override
    public String toString() {
        return "SearchData{" +
                "categoryList=" + categoryList +
                ", cardList=" + cardList +
                ", hashList=" + hashList +
                ", usd=" + usd +
                ", domestic=" + domestic +
                ", installment=" + installment +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
