package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class UserCategoryConfigTableData implements Serializable {

    public String largeCategory;
    public String mediumCategoryList;
    public int categoryConfigId;
    public String categoryCode;
    public int exceptType;
    public int priority;
    public int mainType;

    public boolean check;


    @Override
    public String toString() {
        return "UserCategoryConfigTableData{" +
                "categoryConfigId=" + categoryConfigId +
                ", categoryCode='" + categoryCode + '\'' +
                ", exceptType=" + exceptType +
                ", priority=" + priority +
                ", mainType=" + mainType +
                '}';
    }
}

