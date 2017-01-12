package tenqube.com.qlip_android.ui_kj.calendarsample;

/**
 * Created by tenqube on 2016. 10. 25..
 */

public class SingletonCheckeditem {

    private static SingletonCheckeditem INSTANCE;

    private SingletonCheckeditem() {
    }
    public static  SingletonCheckeditem getINSTANCE(){
        if(INSTANCE ==null){
            INSTANCE = new SingletonCheckeditem();
        }

        return INSTANCE;
    }
    public String thisMonth;
     public String checkedDate;
    public boolean doubleClicked;
    public int parent_pos;
    public  int child_pos;
    public int prev_parent_pos;
    public int prev_parent_pos2;
    public int prev_child_pos;

}
