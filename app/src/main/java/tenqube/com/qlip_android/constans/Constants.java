package tenqube.com.qlip_android.constans;

import android.Manifest;

import tenqube.com.qlip_android.R;


public class Constants {
    public static final String NONE  =  "none";
    public static final int GALLERY_PICTURE  =  0;
    public static final int CAMERA_REQUEST  =  1;
    public static final int CROP_FROM_CAMERA  =  2;
    public static  final int NETWORK_ERROR  =  900;
    public static  final int TAG_MAX  =  50;
    public static  boolean SIDE_MENU;

    public static final int ANIM_START  =  0;
    public static final int ANIM_HIDE_END  =  1;
    public static final int ANIM_SHOW_END  =  -1;


    public static final int SHOW_FAB  =  0;
    public static final int HIDE_FAB  =  1;

    //    public static boolean isInit;
    public static boolean refresh;
    public static boolean lv2_refresh;

    public static boolean googleFlag;
    public static final int SERVER_SUCCESS  =  0;
    public static final int SERVER_FAIL  =  1;

    public static final int INSTALLMENT_DEFAULT  =  1;

    public static final int PERCENT  =  1;
    public static final int NUMBER  =  0;
    public static final int NO  =  0;
    public static final int YES  =  1;
    public static final int EXCEPT_YES  =  1;
    public static final int EXCEPT_NO  =  0;
    public static final String SPARE_STR  =  "   ";

    public static final int[] DAY_OF_WEEKS  =  {
            R.string.monday_short,
            R.string.tuesday_short,
            R.string.wednesday_short,
            R.string.thursday_short,
            R.string.friday_short,
            R.string.saturday_short,
            R.string.sunday_short

    };


    public static final int[] QLIP_TIME  =  {
            R.string.time_2,
            R.string.time_5,
            R.string.time_8,
            R.string.time_11,
            R.string.time_14,
            R.string.time_17,
            R.string.time_20,
            R.string.time_23,
            R.string.time_00

    };

    public static class RequestCode{
        public static final int APP_WIDGET = 999;
        public static final int LOGOUT = 1000;
        public static final int CHANGE_MONEY = 1001;
        public static final int SELECT_CATEGORY = 1002;
        public static final int SELECT_CARD = 1003;
        public static final int SELECT_HASH_TAG = 1004;
        public static final int START_DATE = 1005;
        public static final int SMS_REQUEST = 1007;
        public static final int LOCK_SCREEN = 1008;
        public static final int SEARCH = 1009;
        public static final int HASH_TAG_ACTIVITY = 1011;
        public static final int NOTIFICATION_CATCH = 1012;
        public static final int CHANGE_BUDGET = 1013;
        public static final int EXCEPT_KEYWORD = 1014;
        public static final int SEND_MONEY = 1015;
        public static final int DETAIL_CARD = 1016;
        public static final int SELECT_TRANSFER_CARD = 1017;
        public static final int HASH_TAG_MULTI = 1018;
        public static final int SHARE = 1019;
        public static final int LV2 = 1020;
    }

    public static class MASTER{
        public static final String MASTER_NUMBER_1 = "1063458704";
        public static final String MASTER_NUMBER_2 = "1092373696";
        public static final String MASTER_NUMBER_3 = "1033761602";
        public static final String MASTER_NUMBER_4 = "1028195359";
        public static final String MASTER_NUMBER_5 = "1054642942";

    }


    public static class IntentParameter {
        public static final String WHERE_NUM  =  "WHERE_NUM";
        public static final String SUB_WHERE_NUM  =  "SUB_WHERE_NUM";
        public static final String REPEAT_TRANSACTION_LIST  =  "REPEAT_TRANSACTION_LIST";
        public static final String REQUEST_CODE  =  "REQUEST_CODE";
        public static final String POSITION  =  "POSITION";
        public static final String TAB_POSITION  =  "TAB_POSITION";
        public static final String MENU_TYPE  =  "MENU_TYPE";


        public static final String CATEGORY  =  "CATEGORY";
        public static final String CARD_TYPE  =  "CARD_TYPE";
        public static final String BILLING_DATE  =  "BILLING_DATE";

        public static final String EXCEPT_TYPE  =  "EXCEPT_TYPE";
        public static final String LINKTO  =  "LINKTO";
        public static final String IMG_URL  =  "IMG_URL";
        public static final String GOOGLE_CALENDAR = "calendarDate";
        public static final String GOOGLE_CALENDAR_ID = "calendarDateID";
        public static final String SPENT_MONEY  =  "SPENT_MONEY";
        public static final String CATEGORY_CODE  =  "CATEGORY_CODE";
        public static final String IDENTIFIER  =  "IDENTIFIER";
        public static final String DW_TYPE_CODE  =  "DW_TYPE_CODE";
        public static final String WEB_URL  =  "WEB_URL";
        public static final String SEARCH_DATA  =  "SEARCH_DATA";


        public static final String HASH_TAG_LIST = "HASH_TAG_LIST";
        public static final String HASH_TAG = "HASH_TAG";

        public static final String REPEAT_TRAN = "REPEAT_TRAN";
        public static final String CARD_DATA  =  "CARD_DATA";
        public static final String DISCONNECT  =  "DISCONNECT";


        public static final String QUERY  =  "QUERY";

        public static final String MOVING_ASSET  =  "CARD_DAMOVING_ASSETTA";

        public static final String TEL  =  "TEL";

        public static final String MSG  =  "MSG";

        public static final String DATE  =  "DATE";



    }

    public static class Permission{
        public static String[] PERMISSIONS_SMS  =  {Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_MMS, Manifest.permission.RECEIVE_SMS};
        public static String[] PERMISSIONS_LOCATION  =  {Manifest.permission.ACCESS_COARSE_LOCATION};
        public static String[] PERMISSIONS_ACCOUNT  =  {Manifest.permission.GET_ACCOUNTS};
        public static String[] PERMISSIONS_STORAGE  =  {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        public static String[] PERMISSIONS_CALENDAR  =  {Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR};
        public static String[] PERMISSIONS_ACCOUNT_CALENDAR  =  {Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR};
        public static String[] PERMISSIONS_CAMERA  =  {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        public static String[] PERMISSIONS_FINGERPRINT  =  { com.tenqube.qlip.Manifest.permission.INTERACT_ACROSS_USERS};

        public static final int REQUEST_SMS  =  0;
        public static final int REQUEST_LOCATION  =  1;
        public static final int REQUEST_ACCOUNT  =  2;
        public static final int REQUEST_STORAGE  =  3;
        public static final int REQUEST_CALENDAR  =  4;
        public static final int REQUEST_ACCOUNT_CALENDAR  =  5;
        public static final int REQUEST_CAMERA  =  6;
        public static final int REQUEST_FINGERPRINT  =  7;



    }





    public static class Alarms {
        public static final int WHERE_REPEAT  =  101;
        public static final int REPEAT_DETAIL = 18;
        public static final int WHERE_LAST_WEEKLY  =  108;
        public static final int WHERE_CURRENT_WEEKLY  =  -108;
        public static final int WHERE_CURRENT_MONTHLY  = 109;
        public static final int DETAIL_TRAN  =  110;

    }



    public static class GoogleConfig
    {
        public static final String SENDER_ID  =  "763440420462";
    }
    public static class Sender
    {
        public static final String MASTER_1  =  "63458704";
    }


    public static class Loading{

        public static final int INIT  =  1000;
        public static final int REINSTALL  =  1001;
        public static final int RELOAD  =  1002;
        public static final int TEST  =  1004;
        public static final int IMPORT  =  1005;
        public static final int TOTAL  =  6;
        public static final int RESEND  =  7;
        public static final int LIBRARY  =  8;
        public static final int CASH_TRAN  =  9;
        public static final int CATEGORY  =  10;
        public static final int MOVING_TRAN  =  1006;
        public static final int BUHA  =  1007;
    }

    public  enum Repeat{
        REPEAT_NO,
        REPEAT_EVERY_MONTH,
        REPEAT_EVERY_WEEK
    }
    public enum ProgressParam{
        PROGRESSING,
        PROGRESS95,
        PROGRESS_END,
        PROGRESS_WITH_TEXT,
        PROGRESS_FAIL_END,
        PROGRESS_80,
        NONE,
        OIL,
        PROGRESS_UPDATE, Constants,
    }

    public enum CardType{
        CHECK,
        CARD,
        BANK_ACCOUNT,
        CASH
    }
    public enum  CardSubType{
        NORMAL,
        CORPORATION,
        FAMILY
    }

    public enum  MenuType{
        MENU_WITHDRAW_DEPOSIT,
        MENU_FORECAST,
        MENU_EXCEPTED,
        MENU_WITHDRAW,
        MENU_DEPOSIT
    }

    public enum DWType{
        DEPOSIT,
        WITHDRAW
    }


    public enum ParsingType{
        SMS,
        MMS,
        NOTI

    }



    public enum SmsType{
        SMS_TYPE_NOTI,
        SMS_TYPE_NOTI_PAY,
        SMS_TYPE_SMS,
        SMS_TYPE_USER

    }

    public enum  SearchType{
        COUNTRY,
        KEYWORD,
        CARD,
        CATEGORY,
        HASHTAG,
        INSTALLMENT,
        START_DAY,
        REPEAT,
        DATE_TYPE


    }
    public enum SyncType{
        CARD,
        HASHTAG,
        USER_CATEGORY,
        TRANSACTION,
        CONFIG,
        BUDGET,
        TOTAL,
        RESEND,
        CARD_TRAN

    }

    public enum  ViewType{
        MONTHLY_PIECHART,
        MONTHLY_RECENT_TRANSACTION,
        MONTHLY_ADVERTISEMENT,
        MONTHLY_NORMAL,
        MONTHLY_FB_ADVERTISEMENT,
        MONTHLY_REFRESH,
        DEPOSIT_WITHDRAW_LINECHART,
        BUDGET_HEADER_CHART,
        WEEKLY_HEADER_CHART,
        BUDGET_EXPECTED_RECENT_TRANSACTION,
        COMMON_CATEGORY_PIE_CHART,
        COMMON_CATEGORY_RECENT_TRAN

    }


    public static class TranType{
        public static final int NORMAL = 1;
        public static final int DELETED = -1;
    }


    public static class DateType{
        public static final int ONE_DAY = 0;
        public static final int YEAR = 12;
        public static final int THREE_MONTH = 3;
        public static final int TWELVE_WEEKS = 13;
        public static final int SIX_MONTH = 6;
        public static final int EACH_ONE = 1;
        public static final int EXPECTED = 2;
        public static final int JUST_YEAR = 5;
    }

    public static class CategoryCode{//카테고리 미리지정
        public static final int BUDGET = 1;
        public static final int WEEKLY = 2;
        public static final int MONTHLY = 111010;
        public static final int QLIP_TIP = 4;
        public static final int ADVERTISEMENT = 5;
        public static final int ONE_DAY = 6;
        public static final int HIGHLIGHT = 7;
        public static final int HASHTAG = 10000;
        public static final int DEPOSIT_WHITHDRAW = 10001;
        public static final int WITHDRAW_CODE = 84;
        public static final int WITHDRAW_MOVING_ASSET = 88;
        public static final String WITHDRAW_CODE_STR = "84";

        public static final int INCOME_MAIN = 92;
        public static final int INCOME_SUB = 94;
        public static final int DEPOSIT_LOAN = 96;
        public static final int DEPOSIT_ETC = 90;
        public static final int DEPOSIT_MOVING_ASSET = 98;


        public static final int FOOD = 22;
        public static final int FASTFOOD = 12;




        public static final int ALCOHOL = 26;
        public static final int SULZIP = 13;


        public static final int CAFE = 24;
        public static final int COFFEE = 11;



        public static final int EDUCATION = 54;


        public static final int TRANSPORT = 62;
        public static final int OIL = 12;
        public static final int TAXI = 16;


        public static final int FINANCE = 42;



        public static final int CULTURE = 56;
        public static final int MOVIE = 11;
        public static final int BOOK = 12;



        public static final int LIVINGS = 52;



        public static final int MART = 32;
        public static final int MED_MART = 16;
        public static final int CONVENIENCE = 11;



        public static final int BEAUTY = 46;
        public static final int NAIL = 14;
        public static final int HARISHOP = 11;


        public static final int SHOPPING = 36;



        public static final int HEALTHCARE = 44;

        public static final int PHARMACY = 14;
        public static final int HOSPITAL = 13;
        public static final int DENTIST = 12;
        public static final int CLINIC = 11;


        public static final int TRAVEL = 66;



        public static final int LEPORTS = 64;




        public static final int ONLINE = 34;



        public static final int FAMILY_EVENT = 82;


        public static final int UNCATE = 10;
        public static final String UNCATEGORY_STR = "101010";
        public static final String DEPOSIT_ETC_STR = "901010";

        public static final int CARD = -11;
        public static final int USD = -33;

    }

    public static class LinkTo{
        public static final int LAST_MONTH = 1;
        public static final int CURRENT_MONTH = 0;
        public static final int THREE_MONTH = 2;
        public static final int YEAR = 12;
        public static final int LAST_WEEKS = 1;
        public static final int CURRENT_WEEKS = 0;
        public static final int PLAYSTORE = 100;
        public static final int APP_SYNC = 101;
        public static final int NOTIFICATION = 102;
        public static final int FACEBOOK = 103;
        public static final int NONE = -1;
        public static final int SIDE_MENU = 104;
        public static final int LV2 = 105;
        public static final int BUDGET = 106;
        public static final int SETTINGS = 107;
        public static final int REQUEST_SMS = 108;
        public static final int START_DAY = 109;

        public static final int SAMSUNG_APPS = 110;
    }
    //    public static class ServerCode{
//        public static final int NOT_AUTHORIZE_401 = 401;
//        public static final int SUCCESS_CODE_200 = 200;
//        public static final int SUCCESS_CODE_201 = 201;
//        public static final int SUCCESS_CODE_202 = 202;
//        public static final int SUCCESS_CODE_203 = 203;
//        public static final int SUCCESS_CODE_204 = 204;
//        public static final int SUCCESS_CODE_205 = 205;
//        public static final int SUCCESS_CODE_206 = 206;
//        public static final int SUCCESS_CODE_207 = 207;
//        public static final int SUCCESS_CODE_208 = 208;
//        public static final int SUCCESS_CODE_209 = 209;
//
//        public static final int CLIENT_FAIL_CODE_400 = 400;
//        public static final int CLIENT_FAIL_CODE_401 = 401;
//        public static final int CLIENT_FAIL_CODE_402 = 402;
//        public static final int CLIENT_FAIL_CODE_403 = 403;
//        public static final int CLIENT_FAIL_CODE_404 = 404;
//        public static final int CLIENT_FAIL_CODE_405 = 405;
//        public static final int CLIENT_FAIL_CODE_406 = 406;
//        public static final int CLIENT_FAIL_CODE_407 = 407;
//        public static final int CLIENT_FAIL_CODE_408 = 408;
//        public static final int CLIENT_FAIL_CODE_409 = 409;
//
//
//        public static final int SERVER_FAIL_CODE_500 = 500;
//        public static final int SERVER_FAIL_CODE_501 = 501;
//        public static final int SERVER_FAIL_CODE_502 = 502;
//        public static final int SERVER_FAIL_CODE_503 = 503;
//        public static final int SERVER_FAIL_CODE_504 = 504;
//        public static final int SERVER_FAIL_CODE_505 = 505;
//        public static final int SERVER_FAIL_CODE_506 = 506;
//        public static final int SERVER_FAIL_CODE_507 = 507;
//        public static final int SERVER_FAIL_CODE_508 = 508;
//        public static final int SERVER_FAIL_CODE_509 = 509;
//
//    }
    public static final String[] LV0_MONTHLY_BG = new String[]{
            "lv0_monthly_bills",
            "lv0_monthly_installment",
            "lv0_monthly_installment_mid",
            "lv0_monthly_eatanddrink_2",
            "lv0_monthly_up",
            "lv0_monthly_large_date",
            "lv0_monthly_down",
            "lv0_monthly_installment_mid_1end"
    };

    public static final String[] LV0_MONTHLY_BG_12 = new String[]{

            "lv0_monthly_christmas",
            "lv0_monthly_christmas_2"
    };

    public static final String[] LV0_MONTHLY_BG_1 = new String[]{

            "lv0_monthly_year_end"
    };

    public static final String[] LV0_BUDGET_BG = new String[]{
            "lv0_budget_lastmonth_about_right",
            "lv0_budget_lastmonth_over_10",
            "lv0_budget_lastmonth_over_20",
            "lv0_budget_lastmonth_under_10",
            "lv0_budget_lastmonth_under_20",
            "lv0_budget_this_forecast_1",
            "lv0_budget_this_over20_volcano",
            "lv0_budget_thismonth_good",
            "lv0_budget_this_over00_volcano"
    };


    public static final String[] LV0_WEEKLY_BG = new String[]{
            "lv0_weekly_guidance_1",
            "lv0_weekly_guidance_2",
            "lv0_weekly_summary",
            "lv0_weekly_yesterday_expenses",
            "lv0_weekly_yesterday_muchspending"
    };

    public static final String[] LV0_FOOD_BG = new String[]{
            "lv0_food_else_1",
            "lv0_food_else_2",
            "lv0_mid_food_boonsik",
            "lv0_mid_food_buffet",
            "lv0_mid_food_chinese",
            "lv0_food_fastfood",
            "lv0_mid_food_chicken",
            "lv0_mid_food_dining_place",
            "lv0_mid_food_fam_re",
            "lv0_mid_food_gogi",
            "lv0_mid_food_korean_1"

    };

    public static final String[] LV0_ALCOHOL_BG = new String[]{
            "lv0_alcohol_else",
            "lv0_alcohol_soolzip_yesterday",
            "lv0_alcohol_type_healthy_f",
            "lv0_alcohol_type_lover",
            "lv0_alcohol_type_no_alcohol_2_f",
            "lv0_alcohol_type_prefer_al",
            "lv0_mid_alcohol_entertainment"


    };

    public static final String[] LV0_BEAUTY_BG = new String[]{
            "lv0_beauty_else_1",
            "lv0_beauty_else_2",
            "lv0_mid_beauty_hairshop",
            "lv0_mid_beauty_makeup",
            "lv0_mid_beauty_makeup_drugstore",
            "lv0_mid_beauty_nail",
            "lv0_mid_beauty_skin"

    };

    public static final String[] LV0_CAFE_BG = new String[]{
            "lv0_cafe_coffee_morning",
            "lv0_cafe_else_1",
            "lv0_cafe_else_2",
            "lv0_cafe_franchise",
            "lv0_cafe_type_country",
            "lv0_mid_cafe_coffee",
            "lv0_mid_cafe_donut"

    };

    public static final String[] LV0_CULTURE_BG = new String[]{
            "lv0_culture_bookreader",
            "lv0_culture_else",
            "lv0_mid_culture_books",
            "lv0_mid_culture_concert",
            "lv0_mid_culture_movies",
            "lv0_mid_culture_movies_2",
            "lv0_mid_culture_museum"

    };

    public static final String[] LV0_MART_BG = new String[]{
            "lv0_mart_else",
            "lv0_mid_mart_conveni",
            "lv0_mid_mart_dailyitems",
            "lv0_mid_mart_dailyitems_2",
            "lv0_mid_mart_events_flower",
            "lv0_mid_mart_grocery",
            "lv0_mid_mart_pets"

    };

    public static final String[] LV0_SHOPPING_BG = new String[]{
            "lv0_shopping_else",
            "lv0_mid_shopping_fashion",
            "lv0_mid_shopping_miscel",
            "lv0_mid_shopping_outlet",
            "lv0_mid_shopping_sportbrand"

    };

    public static final String[] LV0_FAMILY_EVENT_BG = new String[]{
            "lv0_culture_else"

    };

    public static final String[] LV0_EDU_BG = new String[]{
            "lv0_education_else",
            "lv0_mid_education_academy",
            "lv0_mid_education_edubook",
            "lv0_mid_education_facility",
            "lv0_mid_education_school",
    };

    public static final String[] LV0_FINANCE_BG = new String[]{
            "lv0_finance_else",
            "lv0_mid_finance_capital",
            "lv0_mid_finance_insurance",
            "lv0_mid_finance_tax",
            "lv0_mid_food_oriental_indian"

    };

    public static final String[] LV0_HEALTH_BG = new String[]{
            "lv0_healthcare_else_2",
            "lv0_mid_healthcare_clinic",
            "lv0_mid_healthcare_dentist",
            "lv0_mid_healthcare_healthproduct",
            "lv0_mid_healthcare_herbalclinic",
            "lv0_mid_healthcare_recovery",
            "lv0_mid_healthcare_pharmacy"


    };

    public static final String[] LV0_LIVING_BG = new String[]{
            "lv0_livings_else",
            "lv0_mid_livings_bills",
            "lv0_mid_livings_moving",
            "lv0_mid_livings_realtor",
            "lv0_mid_livings_rent",
            "lv0_mid_livings_telecom",
            "lv0_mid_livings_telecom_2",
            "lv0_mid_livings_utility",


    };

    public static final String[] LV0_LEPORTS_BG = new String[]{
            "lv0_mid_leports_sports",
            "lv0_leports_else",
            "lv0_mid_leports_fitness_training",
            "lv0_mid_leports_fitness_yoga",
            "lv0_mid_leports_leisure",
            "lv0_mid_leports_martial"


    };

    public static final String[] LV0_TRANSPORTATION_BG = new String[]{
            "lv0_transportation_else",
            "lv0_transportation_gas_3month",
            "lv0_transportation_gas_distance",
            "lv0_transportation_gas_lastmonth",
            "lv0_transportation_liter1",
            "lv0_transportation_liter2",
            "lv0_transportation_taxi_morning_late",
            "lv0_mid_transportation_airline",
            "lv0_mid_transportation_gas",
            "lv0_mid_transportation_masstransit"


    };

    public static final String[] LV0_TRAVLE_BG = new String[]{
            "lv0_travel_else",
            "lv0_travel_last_year",
            "lv0_mid_travel_hotel",
            "lv0_mid_travel_sightseeing",
            "lv0_mid_travel_themepark",
            "lv0_mid_travel_travel"


    };

    public static final String[] LV0_ONLINE_BG = new String[]{
            "lv0_online_else",
            "lv0_mid_online_cardpoint",
            "lv0_mid_online_homeshop",
            "lv0_mid_online_internetshop1",
            "lv0_mid_online_internetshop2"


    };

    public static final String[] LV0_QLIP_TIP_BG = new String[]{
            "lv0_tip_cardapp",
            "lv0_tips_appnotice_linkage_1",
            "lv0_tips_banksms_no_analysis",
            "lv0_tips_google",
            "lv0_tips_moneybook",
            "lv0_tips_nosms",
            "lv0_tips_plan_future",
            "lv0_tips_security_1",
            "lv0_tips_security_2"

    };


    public static final int[] DEPOSIT_CATEGORY_LIST = new int[]{

            901010,
            921010,
            941010,
            961010,
            981010

    };

    public static final int[] CATEGORY_LIST = new int[]{
            101010,
            221010,
            241010,
            261010,
            321010,
            341010,
            361010,
            421010,
            441010,
            461010,
            521010,
            541010,
            561010,
            621010,
            641010,
            661010,
            821010,
            841010,
            881010,


    };





}
