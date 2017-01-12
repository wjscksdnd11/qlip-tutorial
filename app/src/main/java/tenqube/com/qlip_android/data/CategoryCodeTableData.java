package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class CategoryCodeTableData implements Serializable {

    public int category_id;
    public String categoryCode;
    public String largeCategory;
    public String mediumCategory;
    public String smallCategory;
    public int exceptType;
    public boolean check;



    @Override
    public String toString() {
        return "CategoryCodeTableData{" +
                "category_id=" + category_id +
                ", categoryCode='" + categoryCode + '\'' +
                ", largeCategory='" + largeCategory + '\'' +
                ", mediumCategory='" + mediumCategory + '\'' +
                ", smallCategory='" + smallCategory + '\'' +
                ", exceptType=" + exceptType +
                '}';
    }
}

