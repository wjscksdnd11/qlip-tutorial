package tenqube.com.qlip_android.ui_kj.caledarwidget;


import org.joda.time.LocalDate;


/**
 * Created by tenqube on 2016. 12. 29..
 */
public class CalendarManager {

    private State mState;


    private LocalDate mActiveMonth;


    public CalendarManager( LocalDate selected,  State state, LocalDate minDate,
                            LocalDate maxDate  ) {
        mState = state;


        init(selected, minDate, maxDate);
    }



    /**
     *
     * @return index of month to focus to
     */
    public void toggleView() {

        if(mState == CalendarManager.State.MONTH) {
            toggleFromMonth();
        } else {
            toggleFromWeek();
        }

    }

    private void toggleFromMonth() {

        // if same month as selected
        mState = CalendarManager.State.WEEK;
    }

    void toggleToWeek() {

    }


    private void toggleFromWeek() {

        mState = CalendarManager.State.MONTH;
    }

    public State getState() {
        return mState;
    }



    public LocalDate getActiveMonth() {
        return mActiveMonth;
    }

    private void setActiveMonth( ) {

    }




    public int getWeekOfMonth() {
     return 1;
    }

    public void init( LocalDate date,  LocalDate minDate,  LocalDate maxDate) {
        setActiveMonth();
    }


    public enum State {
        MONTH,
        WEEK
    }

}