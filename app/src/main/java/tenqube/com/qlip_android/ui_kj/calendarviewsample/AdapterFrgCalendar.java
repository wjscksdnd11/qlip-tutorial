package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import tenqube.com.qlip_android.data.SearchStringData;

import static tenqube.com.qlip_android.util.LogUtil.LOGI;


class AdapterFrgCalendar extends FragmentStatePagerAdapter {
    private ArrayList<Long> listMonthByMillis = new ArrayList<>();
    private int numOfMonth;
    private int spinnerType;
    private int sumType;
    private ArrayList<SearchStringData> searchStringDataList;

     AdapterFrgCalendar(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {

        return CalViewFragment.newInstance(position,listMonthByMillis.get(position), spinnerType, sumType, searchStringDataList);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getMonthDisplayed(position);
    }

    @Override
    public int getCount() {
        return listMonthByMillis.size();
    }

    void setNumOfMonth(int numOfMonth) {
        this.numOfMonth = numOfMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -numOfMonth);
        calendar.set(Calendar.DATE, 1);
        for (int i = 0; i < numOfMonth * 2 + 1; i++) {
            listMonthByMillis.add(calendar.getTimeInMillis());
            calendar.add(Calendar.MONTH, 1);
        }

        notifyDataSetChanged();
    }

    void addNext() {
        long lastMonthMillis = listMonthByMillis.get(listMonthByMillis.size() - 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastMonthMillis);
        for (int i = 0; i < numOfMonth; i++) {
            calendar.add(Calendar.MONTH, 1);
            listMonthByMillis.add(calendar.getTimeInMillis());
        }
        LOGI(getClass().getSimpleName(), listMonthByMillis.size() + "");
    }

    void addPrev() {
        long lastMonthMillis = listMonthByMillis.get(0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastMonthMillis);
        calendar.set(Calendar.DATE, 1);
        for (int i = numOfMonth; i > 0; i--) {
            calendar.add(Calendar.MONTH, -1);
            listMonthByMillis.add(0, calendar.getTimeInMillis());
        }
        LOGI(getClass().getSimpleName(), listMonthByMillis.size() + " prev");
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    private String getMonthDisplayed(int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(listMonthByMillis.get(position));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM", Locale.KOREA);
        Date date = new Date();
        date.setTime(listMonthByMillis.get(position));

        return "   " + sdf.format(date) + "   ";
    }

    void setOption(int spinnerType, int sumType) {
        this.spinnerType = spinnerType;
        this.sumType = sumType;
        notifyDataSetChanged();

    }

    void setSearchData(ArrayList<SearchStringData> searchStringDatas ) {
        this.searchStringDataList = searchStringDatas;
        notifyDataSetChanged();
    }
}
