package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;

import tenqube.com.qlip_android.data.SearchData;
import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.UserTransactionData;

interface CalViewPresenter {

    void settingToolbar(Toolbar toolbar);

    /**
     * 옵션이 바뀔때마다 call
     *
     * @param spinnerType 액션바 스피너 메뉴 아이템 인덱스
     * @param sumType     세팅메뉴 아이템 인덱스
     */
    void changeOption(int spinnerType, int sumType);

    /**
     * 처음 실행 될 때 default setting
     *
     * @param pager       ViewPager
     * @param spinnerType defalut 값 혹은 이전에 저장한 값
     * @param sumType     defalut 값 혹은 이전에 저장한 값
     */
    void initControl(ViewPager pager, int spinnerType, int sumType);

    HashMap<String, ArrayList<UserTransactionData>> spentDataMap(String startDate, String endDate, int spinnerType, int sumType, ArrayList<SearchStringData> searchStringDataList);

    /**
     * @param timeByMillis query에서 시작날과 마지막날을 계산해서 String[]반환
     * @return 0번째 index 시작날 ,1번째 index 마지막날
     */
    String[] queryStartDate(long timeByMillis);

    /**
     * @param searchData SearchData를 받아서 SearchStringData로 변환하는 메소드
     * @return searchData를 searchStringDataList 리턴
     */
    ArrayList<SearchStringData> changeSearchData(SearchData searchData);

    /**
     * @param searchStringDatas 액티비티에서 받은 searchData를 searchStringDataList변환후 프래그먼트로 전달,프래그먼트에 쿼리매개변수 전달목적.
     */
    void setSearchData(ArrayList<SearchStringData> searchStringDatas);

    /**
     * SearchActivity 실행후 finish 됬을때, data 새로 add하고, view refresh
     */
    void refreshView();

    /**
     * @param refresh actionbar mode변경을 위한 flag  설정
     */
    void setTag(boolean refresh);

    /**
     * @return actionbar mode 상태 받아오기
     * true면 삭제모드 false면 기본 상태
     */
    boolean refreshTag();

    /**
     * @param position 선택된 item 캐쉬.
     */
    void selectedIdentifierList(int position);

    /**
     * 캐쉬된 item삭제 메소드
     */
    void removeIdentifier();

    /**
     *
     * @param mList 해당프래그먼트에서 불러온 transaction data를 캐싱한다
     */
    void setUserTransactionDataList(ArrayList<UserTransactionData> mList);

    /**
     * 삭제 모드로 새로 들어갈때마다 클리어를 한다.
     */
    void clearIdentifier();

    /**
     *
     * @param identifier 선택했던 identifier를 체크하기 위한 메소드
     * @return true명 선택한것 , false면 선택 안된 것.
     */

    boolean checkIdentifier(long identifier);

}
