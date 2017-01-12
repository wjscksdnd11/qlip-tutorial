package tenqube.com.qlip_android.ui_kj.calendarviewsample;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.data.CardTableData;
import tenqube.com.qlip_android.data.SearchData;
import tenqube.com.qlip_android.data.SearchStringData;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.data.UserCategoryConfigTableData;
import tenqube.com.qlip_android.data.UserTransactionData;
import tenqube.com.qlip_android.db.query.CalendarHelper;
import tenqube.com.qlip_android.db.query.QueryHelper;
import tenqube.com.qlip_android.db.table.CardTable;
import tenqube.com.qlip_android.db.table.TransactionsTable;
import tenqube.com.qlip_android.util.DecimalFormatUtil;

import static tenqube.com.qlip_android.R.string.keyword;
import static tenqube.com.qlip_android.db.query.QueryHelper.AND;
import static tenqube.com.qlip_android.db.query.QueryHelper.OR;
import static tenqube.com.qlip_android.util.LogUtil.LOGI;

class CalViewModel {
    private CalendarHelper calendarHelper;


    CalViewModel(Context context) {

        calendarHelper = new CalendarHelper(context);
    }

    HashMap<String, ArrayList<UserTransactionData>> loadSpentDataMap(String start_date, String end_date, int spinnerType, int sumType, ArrayList<SearchStringData> searchStringDataList) {

        return calendarHelper.loadMonthUserCalendarDataMap(start_date, end_date, spinnerType, sumType, searchStringDataList);
    }

    String[] queryStartDate(long timeByMillis) {
        String[] query = new String[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeByMillis);
        calendar.set(Calendar.DATE, 1);
        Calendar prevCalendar = Calendar.getInstance();
        prevCalendar.setTimeInMillis(timeByMillis);
        prevCalendar.add(Calendar.MONTH, -1);
        Calendar nextCalendar = Calendar.getInstance();
        nextCalendar.setTimeInMillis(timeByMillis);
        nextCalendar.add(Calendar.MONTH, 1);
        int prevLastDate = prevCalendar.getActualMaximum(Calendar.DATE);
        // 1일의 요일
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //이달의 마지막 날
        int maxDateOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int nextMonthLimit = 42 - (maxDateOfMonth + (dayOfWeek - 1));
        nextCalendar.set(Calendar.DATE, nextMonthLimit);
        int realStartDate = 1;
        int year;
        int month;
        int date;
        if (dayOfWeek == 1) {
            year = calendar.get(Calendar.YEAR);
            month = (calendar.get(Calendar.MONTH));
            date = realStartDate;
        } else {
            realStartDate = prevLastDate - (dayOfWeek - 2);
            year = prevCalendar.get(Calendar.YEAR);
            month = (prevCalendar.get(Calendar.MONTH));
            date = realStartDate;
        }
        calendar.set(year, month, date);
        query[0] = calendar.get(Calendar.YEAR) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.MONTH) + 1) + "-" + DecimalFormatUtil.decimalFormat.format(calendar.get(Calendar.DATE));
        query[1] = nextCalendar.get(Calendar.YEAR) + "-" + DecimalFormatUtil.decimalFormat.format(nextCalendar.get(Calendar.MONTH) + 1) + "-" + DecimalFormatUtil.decimalFormat.format(nextCalendar.get(Calendar.DATE));


        return query;
    }


    ArrayList<SearchStringData> setSearchStringData(SearchData searchData) {

        ArrayList<SearchStringData> searchDataList = new ArrayList<>();

        if (searchData != null) {
            ArrayList<UserCategoryConfigTableData> categoryList = searchData.categoryList;
            ArrayList<TagTableData> hashList = searchData.hashList;
            ArrayList<CardTableData> cardList = searchData.cardList;
            int installment = searchData.installment;
            if (searchData.usd == 1 && searchData.domestic == 1) {
                LOGI(getClass().getSimpleName(), "domestic =1, usd =1");
            } else {
                if (searchData.usd == 1) {
                    SearchStringData searchStringData = new SearchStringData();
                    searchStringData.searchName = "해외결제";
                    searchStringData.searchQueryStr = AND + TransactionsTable.COLUMN_CURRENCY + "!='none'";
                    searchStringData.searchType = Constants.SearchType.COUNTRY;
                    searchDataList.add(searchStringData);
                }
                if (searchData.domestic == 1) {
                    SearchStringData searchStringData = new SearchStringData();
                    searchStringData.searchName = "국내결제";
                    searchStringData.searchQueryStr = AND + TransactionsTable.COLUMN_CURRENCY + "='none'";
                    searchStringData.searchType = Constants.SearchType.COUNTRY;
                    searchDataList.add(searchStringData);
                }
            }
            if (!TextUtils.isEmpty(searchData.keyword)) {
                SearchStringData searchStringData = new SearchStringData();
                searchStringData.searchQueryStr = AND +
                        "(" +
                        TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_KEYWORD + " like '%" + searchData.keyword + "%'" +
                        OR +
                        TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_MEMO + " like '%" + keyword + "%'" +
                        OR +
                        TransactionsTable.TABLE_NAME + "." + TransactionsTable.COLUMN_FRANCHISE + " like '%" + keyword + "%'" + ")";
                searchStringData.searchName = searchData.keyword;
                searchStringData.searchType = Constants.SearchType.KEYWORD;
                searchDataList.add(searchStringData);
            }

            if (searchData.repeat == 1) {
                SearchStringData searchStringData = new SearchStringData();
                searchStringData.searchType = Constants.SearchType.REPEAT;
                searchStringData.searchQueryStr = AND + TransactionsTable.COLUMN_REPEAT_TYPE + "!=0";
                searchStringData.searchName = "반복";
                searchDataList.add(searchStringData);
            }

            if (installment != 0) {
                SearchStringData searchStringData = new SearchStringData();
                searchStringData.searchQueryStr = AND + TransactionsTable.COLUMN_INSTALLMENT_COUNT + " > " + Constants.INSTALLMENT_DEFAULT;
                searchStringData.searchType = Constants.SearchType.INSTALLMENT;
                searchStringData.searchName = "할부";
                searchDataList.add(searchStringData);
            }

            if (categoryList.size() > 0) {
                for (UserCategoryConfigTableData data : categoryList) {
                    SearchStringData searchStringData = new SearchStringData();
                    searchStringData.searchName = data.largeCategory;
                    searchStringData.searchType = Constants.SearchType.CATEGORY;
                    searchStringData.searchQueryStr = data.categoryCode;
                    searchDataList.add(searchStringData);
                }
            }

            if (hashList.size() > 0) {
                for (TagTableData data : hashList) {
                    SearchStringData searchStringData = new SearchStringData();
                    searchStringData.searchName = data.tagName;
                    searchStringData.searchType = Constants.SearchType.HASHTAG;
                    searchStringData.searchQueryStr = data.tagId + "";
                    searchDataList.add(searchStringData);
                }
            }
            if (cardList.size() > 0) {
                for (CardTableData data : cardList) {
                    SearchStringData searchStringData = new SearchStringData();
                    searchStringData.searchName = data.changeCardName;
                    searchStringData.searchType = Constants.SearchType.CARD;
                    searchStringData.searchQueryStr = "(" + CardTable.COLUMN_CARD_CHANGE_NAME + "='" + data.changeCardName +
                            "'" + QueryHelper.AND + CardTable.COLUMN_CARD_CHANGE_TYPE + "=" + data.changeCardType + QueryHelper.AND +
                            CardTable.COLUMN_CARD_CHANGE_SUB_TYPE + "=" + data.changeCardSubType + ") ";
                    searchDataList.add(searchStringData);
                }
            }
        }

        return searchDataList;

    }

    void removeIdentifireList(ArrayList<Long> selectedIdentifireList) {
        calendarHelper.removeIdentifireList(selectedIdentifireList);
    }

}
