package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class TagTableData implements Serializable {

    public String tagName;
    public boolean check;
    public int serverSuccess;
    public long tagId;
    public int exceptType;
    public int tagType;
    public int mainType;
    public int priority;
    public String changeTag;

    public boolean isShown;


    @Override
    public String toString() {
        return "TagTableData{" +
                ", tagName='" + tagName + '\'' +
                ", check=" + check +
                ", serverSuccess=" + serverSuccess +
                ", tagId=" + tagId +
                ", exceptType=" + exceptType +
                ", tagType=" + tagType +
                ", mainType=" + mainType +
                '}';
    }
}
