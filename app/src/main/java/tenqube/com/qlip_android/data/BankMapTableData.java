package tenqube.com.qlip_android.data;

import java.io.Serializable;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class BankMapTableData implements Serializable {

    public int _id;
    public int repId;
    public int subId;
    public int deleteType;


    @Override
    public String toString() {
        return "BankMapTableData{" +
                "repId=" + repId +
                ", subId=" + subId +
                '}';
    }
}
