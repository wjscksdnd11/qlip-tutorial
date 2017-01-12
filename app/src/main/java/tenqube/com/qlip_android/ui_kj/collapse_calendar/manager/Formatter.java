package tenqube.com.qlip_android.ui_kj.collapse_calendar.manager;

import android.support.annotation.NonNull;

import org.joda.time.LocalDate;

/**
 * Created by tenqube on 2016. 12. 29..
 */
public interface Formatter {

    String getDayName(@NonNull LocalDate date);
    String getHeaderText(int type,  @NonNull LocalDate from, @NonNull LocalDate to);

}
