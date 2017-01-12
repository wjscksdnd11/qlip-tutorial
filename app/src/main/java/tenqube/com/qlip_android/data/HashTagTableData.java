package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class HashTagTableData implements Serializable {

    public String hash_name;
    public boolean check;
    public int serverSuccess;
    public long hashTagId;
    public int exceptType;
    public int hashType;
    public int mainType;
    public int priority;
    public String changeTag;


    @Override
    public String toString() {
        return "HashTagTableData{" +
                ", hash_name='" + hash_name + '\'' +
                ", check=" + check +
                ", serverSuccess=" + serverSuccess +
                ", hashTagId=" + hashTagId +
                ", exceptType=" + exceptType +
                ", hashType=" + hashType +
                ", mainType=" + mainType +
                '}';
    }
}
