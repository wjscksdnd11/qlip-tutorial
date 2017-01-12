package tenqube.com.qlip_android.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 변경 사항 작성
 * 내용/변경자/날짜
 * ex) line 233 파라미터 추가 /사광진/2015-05-20
 *
 */
public class ChartData implements Serializable {

    //카테고리
    public ArrayList<String> xVals = new ArrayList<String>();
    //지출 or 수입 or 입출금 내역
    public ArrayList<Integer> yVals = new ArrayList<Integer>();
    public ArrayList<Integer> yVals2 = new ArrayList<Integer>();


}
