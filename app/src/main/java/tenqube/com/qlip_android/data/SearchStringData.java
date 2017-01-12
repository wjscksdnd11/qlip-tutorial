package tenqube.com.qlip_android.data;


import java.io.Serializable;

import tenqube.com.qlip_android.constans.Constants;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class SearchStringData implements Serializable {

    public String searchName;
    public Constants.SearchType searchType;
    public String searchQueryStr;

    @Override
    public String toString() {
        return "SearchStringData{" +
                "searchName='" + searchName + '\'' +
                ", searchType=" + searchType +
                ", searchQueryStr='" + searchQueryStr + '\'' +
                '}';
    }
}
