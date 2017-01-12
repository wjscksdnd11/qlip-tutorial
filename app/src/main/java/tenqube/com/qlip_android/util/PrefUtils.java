

package tenqube.com.qlip_android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static tenqube.com.qlip_android.util.LogUtil.makeLogTag;
import android.graphics.Color;


/**
 * Utilities and constants related to app preferences.
 */
public class PrefUtils {

    private static final String TAG = makeLogTag("PrefUtils");

    private static  PrefUtils INSTANCE;
    private Context context;
    private static final Object lock = new Object();
    public  static PrefUtils getInstance(Context context){
        synchronized (lock) {
            if(INSTANCE == null){
                INSTANCE = new PrefUtils(context.getApplicationContext());
            }
        }
        return INSTANCE;
    }

    private PrefUtils(Context context){

        this.context = context;

    }


    public static final String QLIP_GOOGLE_CANEADAR_WITHDRAW_ID = "QLIP_GOOGLE_CANEADAR_WITHDRAW_ID";
    public static final String QLIP_GOOGLE_CANEADAR_DEPOSIT_ID = "QLIP_GOOGLE_CANEADAR_DEPOSIT_ID";
    public static final String QLIP_GOOGLE_CAL_WITHDRAW_ID = "QLIP_GOOGLE_CAL_WITHDRAW_ID";
    public static final String QLIP_GOOGLE_CAL_DEPOSIT_ID = "QLIP_GOOGLE_CAL_DEPOSIT_ID";
    public static final String QLIP_GOOGLE_CANEADAR_ID = "QLIP_GOOGLE_CANEADAR_ID";
    public static final String SMS_LOAD = "SMS_LOAD";

    public static final String ALARM_SMS = "receiveSms";
    public static final String QLIP_UPDATE = "QLIP_UPDATE";

    public static final String CHECK_LOGIN = "Login";
    public static final String NOTIFICATION_COUNT = "notiCount";
    public static final String USER_EMAIL_ID = "Email";

    public static final String USER_EMAIL_PK = "USER_EMAIL_PK";
    public static final String GCM_ID = "sp_reg_id";

    public static final String GOOGLE_AD_ID = "GOOGLE_AD_ID";


    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";


    public static final String DEVCE_ID = "DEVCE_ID";

    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String TRANSACTION_START_DAY = "start_day";

    public static final String SECURITY = "SECURITY";//google on off

    public static final String ADAPT_INSTALLMENT = "adapt_Installment";

    public static final String SYNC_DATA_CONFIG_VERSION = "SYNC_DATA_CONFIG_VERSION";
    public static final String SYNC_DATA_TRANSACTION_VERSION = "SYNC_DATA_TRANSACTION_VERSION";

    public static final String SYNC_DATA_CATEGORY_VERSION = "SYNC_DATA_CATEGORY_VERSION";
    public static final String SYNC_DATA_USER_CATEGORY_VERSION = "SYNC_DATA_USER_CATEGORY_VERSION";

    public static final String SYNC_DATA_BUDGET_VERSION = "SYNC_DATA_BUDGET_VERSION";

    public static final String SYNC_DATA_REG_VERSION = "SYNC_DATA_REG_VERSION";

    public static final String SYNC_DATA_NOTICE_VERSION = "noticeVersion";

    public static final String SYNC_DATA_HASH_TAG = "SYNC_DATA_HASH_TAG";

    public static final String SYNC_DATA_CARD = "SYNC_DATA_CARD";

    public static final String AES_KEY = "aesKEY";

    public static final String APP_PACKAGES = "APP_PACKAGES";

    public static final String SYNC_APP = "SYNC_APP";
    //
    public static final String HOME_KEY = "HOME_KEY";
    public static final String USER_BUDGET = "initBudget";
    public static final String VERSION_CODE = "VERSION_CODE";

    public static final String BUDGET_OVER_9 = "BUDGET_OVER_9";
    public static final String BUDGET_OVER_10 = "BUDGET_OVER_10";
    public static final String BUDGET_OVER_11 = "BUDGET_OVER_11";
    public static final String BUDGET_OVER_12 = "BUDGET_OVER_12";
    public static final String ONCE_A_MONTH = "ONCE_A_MONTH";

    public static final String IS_AUTH_UPDATE = "IS_AUTH_UPDATE_2";

    public static final String APP_WIDGET_ALPHA = "APP_WIDGET_ALPHA";

    public static final String REG_CALL_COUNT = "REG_CALL_COUNT";
    public static final String ALARM_OVER_SPEND = "isOverBudget";
    public static final String ALARM_ONCE_A_DAY = "ALARM_ONCE_A_DAY";
    public static final String ADAPT_WEBTOON = "ADAPT_WEBTOON";
    public static final String LOOK_BACK = "LOOK_BACK";
    public static final String NOTI_FORCE_ID = "NOTI_FORCE_ID";


    public static final String TRAN_IDENTIFIER_LIST = "TRAN_IDENTIFIER_LIST";
    public static final String DELETED_TAG_LIST = "DELETED_TAG_LIST";
    public static final String DELETED_CARD_LIST = "DELETED_CARD_LIST";
    public static final String DELETED_CONNECT_BANK_LIST = "DELETED_CONNECT_BANK_LIST";

    public static final String CREATE_DB = "CREATE_DB";
    public static final String GALAXY_CLICK = "GALAXY_CLICK";
    public static final String GOOGLE_PLAY_CLICK = "GOOGLE_PLAY_CLICK";
    public static final String APP_WIDGET_BG_COLOR = "APP_WIDGET_BG_COLOR";
    public static final String APP_WIDGET_TEXT_COLOR = "APP_WIDGET_TEXT_COLOR";

    public static final String IS_FAQ_CLICK = "IS_FAQ_CLICK";
    public static final String INSTALL_DATE = "INSTALL_DATE";
    public static final String IS_SMART_MANAGER = "IS_SMART_MANAGER";

    public boolean isEnabled(String key, boolean defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(key, defaultValue);
    }

    public void saveBoolean(String key, boolean flag){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(key, flag).apply();
    }

    public int loadIntValue(String key, int defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(key, defaultValue);
    }

    public void saveIntValue(String key, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(key, value).apply();
    }

    public float loadFloatValue(String key, float defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getFloat(key, defaultValue);
    }

    public void saveFloatValue(String key, float value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putFloat(key, value).apply();
    }

    public long loadLongValue(String key, long defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getLong(key, defaultValue);
    }

    public void saveLongValue(String key, long value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putLong(key, value).apply();
    }

    public String loadStringValue(String key, String defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }

    public void saveStringValue(String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).apply();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void clearAsNewUser() {

        String fcmStr = loadStringValue(GCM_ID, "none");
        String googleAId = loadStringValue(GOOGLE_AD_ID, "none");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().clear().apply();

        saveStringValue(GCM_ID, fcmStr);
        saveStringValue(GOOGLE_AD_ID, googleAId);

    }

    public void refreshOnceAMonth(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(BUDGET_OVER_9).apply();
        sp.edit().remove(BUDGET_OVER_10).apply();
        sp.edit().remove(BUDGET_OVER_11).apply();
        sp.edit().remove(BUDGET_OVER_12).apply();
    }

}

