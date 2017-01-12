package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class TagMapTableData implements Serializable {

    public long identifier;
    public long tagId;


    @Override
    public String toString() {
        return "TagMapTableData{" +
                ", identifier=" + identifier +
                ", tagId=" + tagId +
                '}';
    }
}
