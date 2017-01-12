package tenqube.com.qlip_android.util;
import android.content.Context;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;


public class CategoryUtil {

    public static int getIconImage(int categoryCode){

        switch (categoryCode){
            case Constants.CategoryCode.DEPOSIT_MOVING_ASSET:
                return  R.drawable.ic_deposit_moving_asset;
            case Constants.CategoryCode.WITHDRAW_MOVING_ASSET:
                return  R.drawable.ic_withdraw_moving_asset;
            case Constants.CategoryCode.DEPOSIT_WHITHDRAW:
                return  R.drawable.ic_income;
            case Constants.CategoryCode.INCOME_MAIN:
                return  R.drawable.ic_income_main;
            case Constants.CategoryCode.INCOME_SUB:
                return  R.drawable.ic_income_sub;
            case Constants.CategoryCode.DEPOSIT_LOAN:
                return  R.drawable.ic_loan;
            case Constants.CategoryCode.DEPOSIT_ETC:
                return  R.drawable.ic_income_etc;
            case Constants.CategoryCode.WITHDRAW_CODE:
                return  R.drawable.ic_withdraw;
            case Constants.CategoryCode.BUDGET:
                return R.drawable.ic_budget;
            case Constants.CategoryCode.MONTHLY:
                return R.drawable.ic_monthly;
            case Constants.CategoryCode.WEEKLY:
                return R.drawable.ic_weekly;
            case Constants.CategoryCode.QLIP_TIP:
                return  R.drawable.ic_qlip_tip;
            case Constants.CategoryCode.ALCOHOL:
                return R.drawable.ic_alcohol;
            case Constants.CategoryCode.BEAUTY:
                return R.drawable.ic_beauty;
            case Constants.CategoryCode.CAFE:
                return R.drawable.ic_cafe;
            case Constants.CategoryCode.CULTURE:
                return R.drawable.ic_culture;
            case Constants.CategoryCode.EDUCATION:
                return R.drawable.ic_education;
            case Constants.CategoryCode.FAMILY_EVENT:
                return R.drawable.ic_family_event;
            case Constants.CategoryCode.FINANCE:
                return R.drawable.ic_finance;
            case Constants.CategoryCode.FOOD:
                return R.drawable.ic_food;
            case Constants.CategoryCode.HEALTHCARE:
                return R.drawable.ic_health_care;
            case Constants.CategoryCode.LEPORTS:
                return R.drawable.ic_leports;
            case Constants.CategoryCode.LIVINGS:
                return R.drawable.ic_livings;
            case Constants.CategoryCode.MART:
                return R.drawable.ic_mart;
            case Constants.CategoryCode.ONLINE:
                return R.drawable.ic_online;
            case Constants.CategoryCode.SHOPPING:
                return R.drawable.ic_shopping;
            case Constants.CategoryCode.TRANSPORT:
                return R.drawable.ic_transport;
            case Constants.CategoryCode.TRAVEL:
                return R.drawable.ic_travel;
            case Constants.CategoryCode.CARD:
                return  R.drawable.ic_card;
            case Constants.CategoryCode.HASHTAG:
                return  R.drawable.ic_lv1_tag;

            default:
                return R.drawable.ic_uncate;
        }
    }
    public static int getIconColor(int categoryCode){

        switch (categoryCode){
            case Constants.CategoryCode.DEPOSIT_MOVING_ASSET:
            case Constants.CategoryCode.WITHDRAW_MOVING_ASSET:
                return  R.color.moving_asset_main_color;
            case Constants.CategoryCode.INCOME_MAIN:
                return  R.color.income_main_color;
            case Constants.CategoryCode.INCOME_SUB:
                return  R.color.income_sub_color;
            case Constants.CategoryCode.DEPOSIT_LOAN:
                return  R.color.loan_color;
            case Constants.CategoryCode.DEPOSIT_ETC:
                return  R.color.income_etc_color;
            case Constants.CategoryCode.WITHDRAW_CODE:
                return  R.color.withdraw_color;
            case Constants.CategoryCode.BUDGET:
                return R.color.budget_color;
            case Constants.CategoryCode.MONTHLY:
                return R.color.monthly_color;
            case Constants.CategoryCode.WEEKLY:
                return R.color.weekly_color;
            case Constants.CategoryCode.QLIP_TIP:
                return  R.color.qlip_tip_color;
            case Constants.CategoryCode.ALCOHOL:
                return R.color.alcohol_color;
            case Constants.CategoryCode.BEAUTY:
                return R.color.beauty_color;
            case Constants.CategoryCode.CAFE:
                return R.color.cafe_color;
            case Constants.CategoryCode.CULTURE:
                return R.color.culture_color;
            case Constants.CategoryCode.EDUCATION:
                return R.color.education_color;
            case Constants.CategoryCode.FAMILY_EVENT:
                return R.color.budget_color;
            case Constants.CategoryCode.FINANCE:
                return R.color.finance_color;
            case Constants.CategoryCode.FOOD:
                return R.color.food_color;
            case Constants.CategoryCode.HEALTHCARE:
                return R.color.health_color;
            case Constants.CategoryCode.LEPORTS:
                return R.color.leports_color;
            case Constants.CategoryCode.LIVINGS:
                return R.color.living_color;
            case Constants.CategoryCode.MART:
                return R.color.mart_color;
            case Constants.CategoryCode.ONLINE:
                return R.color.online_color;
            case Constants.CategoryCode.SHOPPING:
                return R.color.shopping_color;
            case Constants.CategoryCode.TRANSPORT:
                return R.color.transport_color;
            case Constants.CategoryCode.TRAVEL:
                return R.color.travel_color;
            case Constants.CategoryCode.HASHTAG:
                return R.color.qlip_main_green;
            case Constants.CategoryCode.DEPOSIT_WHITHDRAW:
                return  R.color.deposit_withdraw;
            default:
                return R.color.uncate_color;
        }
    }

    public static int getIconColor(int categoryCode,int order){

        switch (categoryCode){
            case Constants.CategoryCode.DEPOSIT_MOVING_ASSET:
            case Constants.CategoryCode.WITHDRAW_MOVING_ASSET:
                switch (order){
                    case 0:
                        return  R.color.income_main_color;

                    case 1:
                        return  R.color.income_main_color_1;

                    default:
                        return  R.color.income_main_color_2;
                }
            case Constants.CategoryCode.INCOME_MAIN:
                switch (order){
                    case 0:
                        return  R.color.income_main_color;

                    case 1:
                        return  R.color.income_main_color_1;

                    default:
                        return  R.color.income_main_color_2;
                }

            case Constants.CategoryCode.INCOME_SUB:

            switch (order){
                case 0:
                    return  R.color.income_sub_color;

                case 1:
                    return  R.color.income_sub_color_1;

                default:
                    return  R.color.income_sub_color_2;
            }
            case Constants.CategoryCode.DEPOSIT_LOAN:
                switch (order){
                    case 0:
                        return  R.color.loan_color;

                    case 1:
                        return  R.color.loan_color_1;

                    default:
                        return  R.color.loan_color_2;
                }
            case Constants.CategoryCode.DEPOSIT_ETC:
                switch (order){
                    case 0:
                        return  R.color.income_etc_color;

                    case 1:
                        return  R.color.income_etc_color_1;

                    default:
                        return  R.color.income_etc_color_2;
                }
            case Constants.CategoryCode.WITHDRAW_CODE:
                return  R.color.withdraw_color;

            case Constants.CategoryCode.BUDGET:
                return R.color.budget_color;
            case Constants.CategoryCode.MONTHLY:
                return R.color.monthly_color;
            case Constants.CategoryCode.WEEKLY:
                return R.color.weekly_color;
            case Constants.CategoryCode.QLIP_TIP:
                return  R.color.qlip_tip_color;
            case Constants.CategoryCode.ALCOHOL:
                switch (order){
                    case 0:
                        return  R.color.alcohol_color;

                    case 1:
                        return  R.color.alcohol_color_1;

                    default:
                        return  R.color.alcohol_color_2;
                }
            case Constants.CategoryCode.BEAUTY:
            switch (order){
                case 0:
                    return  R.color.beauty_color;

                case 1:
                    return  R.color.beauty_color_1;

                default:
                    return  R.color.beauty_color_2;
            }
            case Constants.CategoryCode.CAFE:
            switch (order){
                case 0:
                    return  R.color.cafe_color;

                case 1:
                    return  R.color.cafe_color_1;

                default:
                    return  R.color.cafe_color_2;
            }
            case Constants.CategoryCode.CULTURE:
            switch (order){
                case 0:
                    return  R.color.culture_color;

                case 1:
                    return  R.color.culture_color_1;

                default:
                    return  R.color.culture_color_2;
            }
            case Constants.CategoryCode.EDUCATION:
            switch (order){
                case 0:
                    return  R.color.education_color;

                case 1:
                    return  R.color.education_color_1;

                default:
                    return  R.color.education_color_2;
            }
            case Constants.CategoryCode.FAMILY_EVENT:
            switch (order){
                case 0:
                    return  R.color.family_event_color;

                case 1:
                    return  R.color.family_event_color_1;

                default:
                    return  R.color.family_event_color_2;
            }
            case Constants.CategoryCode.FINANCE:
            switch (order){
                case 0:
                    return  R.color.finance_color;

                case 1:
                    return  R.color.finance_color_1;

                default:
                    return  R.color.finance_color_2;
            }
            case Constants.CategoryCode.FOOD:
            switch (order){
                case 0:
                    return R.color.food_color;
                case 1:
                    return R.color.food_color_1;
                default:
                    return R.color.food_color_2;
            }
            case Constants.CategoryCode.HEALTHCARE:
            switch (order){
                case 0:
                    return R.color.health_color;
                case 1:
                    return R.color.health_color_1;
                default:
                    return R.color.health_color_2;
            }
            case Constants.CategoryCode.LEPORTS:
            switch (order){
                case 0:
                    return R.color.leports_color;
                case 1:
                    return R.color.leports_color_1;
                default:
                    return R.color.leports_color_2;
            }
            case Constants.CategoryCode.LIVINGS:
            switch (order){
                case 0:
                    return R.color.living_color;
                case 1:
                    return R.color.living_color_1;
                default:
                    return R.color.living_color_2;
            }
            case Constants.CategoryCode.MART:
            switch (order){
                case 0:
                    return R.color.mart_color;
                case 1:
                    return R.color.mart_color_1;
                default:
                    return R.color.mart_color_2;
            }
            case Constants.CategoryCode.ONLINE:
            switch (order){
                case 0:
                    return R.color.online_color;
                case 1:
                    return R.color.online_color_1;
                default:
                    return R.color.online_color_2;
            }
            case Constants.CategoryCode.SHOPPING:
            switch (order){
                case 0:
                    return R.color.shopping_color;
                case 1:
                    return R.color.shopping_color_1;
                default:
                    return R.color.shopping_color_2;
            }
            case Constants.CategoryCode.TRANSPORT:
            switch (order){
                case 0:
                    return R.color.transport_color;
                case 1:
                    return R.color.transport_color_1;
                default:
                    return R.color.transport_color_2;
            }
            case Constants.CategoryCode.TRAVEL:
            switch (order){
                case 0:
                    return R.color.travel_color;
                case 1:
                    return R.color.travel_color_1;
                default:
                    return R.color.travel_color_2;
            }
            case Constants.CategoryCode.HASHTAG:
                return R.color.tag_color;
            case Constants.CategoryCode.DEPOSIT_WHITHDRAW:
                return  R.color.deposit_withdraw;
            default:
                return R.color.uncate_color;

        }
    }




    public static String[] getLv0BgName(Context mContext, String categoryCode) {


        if(mContext.getString(R.string.food_code).equals(categoryCode.substring(0,2))){
            if(mContext.getString(R.string.food_code).equals(categoryCode)) {
                return new String[]{"lv0_food_else_1",
                        "lv0_food_else_2"};
            }else if(mContext.getString(R.string.med_Meet).equals(categoryCode)){
                return new String[]{"lv0_mid_food_gogi"};
            }else if(mContext.getString(R.string.med_Batch).equals(categoryCode)){
                return new String[]{"lv0_mid_food_boonsik"};
            }else if(mContext.getString(R.string.med_Buffet).equals(categoryCode)){
                return new String[]{"lv0_mid_food_buffet"};
            }else if(mContext.getString(R.string.med_Asian_Food).equals(categoryCode)){
                return new String[]{"lv0_mid_food_oriental_vietnamese",
                        "lv0_mid_food_oriental_indian","lv0_mid_food_oriental"};
            }else if(mContext.getString(R.string.med_Midnight_Snack).equals(categoryCode)){
                return new String[]{"lv0_mid_food_late_night"};
            }else if(mContext.getString(R.string.med_Food).equals(categoryCode)){
                return new String[]{"lv0_mid_food_western",
                        "lv0_mid_food_western_pizza"};
            }else if(mContext.getString(R.string.med_General_restaurant).equals(categoryCode)){
                return new String[]{"lv0_mid_food_dining_place"};
            }else if(mContext.getString(R.string.med_Japanese).equals(categoryCode)){
                return new String[]{"lv0_mid_food_japanese"};
            }else if(mContext.getString(R.string.med_Chinese).equals(categoryCode)){
                return new String[]{"lv0_mid_food_chinese"};
            }else if(mContext.getString(R.string.med_Chicken).equals(categoryCode)){
                return new String[]{"lv0_mid_chicken"};
            }else if(mContext.getString(R.string.med_Family_Restaurant).equals(categoryCode)){
                return new String[]{"lv0_mid_food_fam_re"};
            }else if(mContext.getString(R.string.med_Fastfood).equals(categoryCode)){
                return new String[]{"lv0_mid_food_fastfood"};
            }else if(mContext.getString(R.string.med_Korean).equals(categoryCode)){
                return new String[]{"lv0_mid_food_korean","lv0_mid_food_korean_1",
                        "lv0_mid_food_korean_dosirak","lv0_mid_food_korean_seafood"};
            }else{
                return  new String[]{"lv0_food_else_1",
                        "lv0_food_else_2"};
            }

        }

        else if(mContext.getString(R.string.alcohol_code).equals(categoryCode.substring(0,2))){
            if(mContext.getString(R.string.alcohol_code).equals(categoryCode)){
                return new String[]{"lv0_alcohol_else"};
            }else if(mContext.getString(R.string.med_Bar).equals(categoryCode)){
                return new String[]{
                        "lv0_mid_alcohol_soolzip",
                        "lv0_mid_alcohol_soolzip_beer",
                        "lv0_mid_alcohol_soolzip_cocktail1",
                        "lv0_mid_alcohol_soolzip_cocktail2",
                        "lv0_mid_alcohol_soolzip_hof",
                        "lv0_mid_alcohol_soolzip_smallbeer",
                        "lv0_mid_alcohol_soolzip_wine"
                };
            }else if(mContext.getString(R.string.med_Leisure_facilities).equals(categoryCode)){
                return new String[]{"lv0_mid_alcohol_entertainment"};
            }else if(mContext.getString(R.string.med_Nightlife).equals(categoryCode)){
                return new String[]{"lv0_alcohol_else"};
            }else{
                return new String[]{"lv0_alcohol_else"};
            }
        }else if(mContext.getString(R.string.alcohol_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.beauty_code).equals(categoryCode)){
                return new String[]{"lv0_beauty_else_1","lv0_beauty_else_2"};
            }else if(mContext.getString(R.string.med_Nail).equals(categoryCode)){
                return new String[]{"lv0_mid_beauty_nail"};
            }else if(mContext.getString(R.string.med_Skin_Body_Management).equals(categoryCode)){
                return new String[]{"lv0_mid_beauty_skin"};
            }else if(mContext.getString(R.string.med_Cosmetic).equals(categoryCode)){
                return new String[]{"lv0_mid_beauty_makeup","lv0_mid_beauty_makeup_drugstore"};
            }else{
                return new String[]{"lv0_beauty_else_1","lv0_beauty_else_2"};
            }
        }else if(mContext.getString(R.string.cafe_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.cafe_code).equals(categoryCode)){
                return new String[]{"lv0_cafe_else_1","lv0_cafe_else_2"};
            }else if(mContext.getString(R.string.med_Donut_Ice_Creamt).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_donut"};
            }else if(mContext.getString(R.string.med_Dessert_bread).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_dessert"};
            }else if(mContext.getString(R.string.med_Bakery).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_bakery"};
            }else if(mContext.getString(R.string.med_Sandwiches_hotdogs).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_sandwich"};
            }else if(mContext.getString(R.string.med_Tea_juice_ice_water).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_tea","lv0_mid_cafe_tea_bingsu"};
            }else if(mContext.getString(R.string.med_coffee).equals(categoryCode)){
                return new String[]{"lv0_mid_cafe_coffee"};
            }else{
                return new String[]{"lv0_cafe_else_1","lv0_cafe_else_2"};
            }
        }else if(mContext.getString(R.string.culture_code).equals(categoryCode.substring(0,2))) {

            if(mContext.getString(R.string.culture_code).equals(categoryCode)){
                return new String[]{"lv0_culture_else"};
            }else if(mContext.getString(R.string.med_book).equals(categoryCode)){
                return new String[]{"lv0_mid_culture_books","lv0_mid_books"};
            }else if(mContext.getString(R.string.med_movie).equals(categoryCode)){
                return new String[]{"lv0_mid_culture_movies","lv0_mid_culture_movies_2"};
            }else if(mContext.getString(R.string.med_Exhibition).equals(categoryCode)){
                return new String[]{"lv0_mid_culture_museum"};
            }else if(mContext.getString(R.string.med_Performance).equals(categoryCode)){
                return new String[]{"lv0_mid_culture_concert"};
            }else if(mContext.getString(R.string.med_hobby).equals(categoryCode)){
                return new String[]{"lv0_mid_culture_hobby"};
            }
            else{
                return new String[]{"lv0_culture_else"};
            }
        }else if(mContext.getString(R.string.mart_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.mart_code).equals(categoryCode)){
                return new String[]{"lv0_mart_else"};
            }else if(mContext.getString(R.string.med_Appliances_Furniture).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_furniture","lv0_mid_mart_furniture_wood"};
            }else if(mContext.getString(R.string.med_Mart).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_grocery"};
            }else if(mContext.getString(R.string.med_Pets).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_pets"};
            }else if(mContext.getString(R.string.med_infant).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_kids_1","lv0_mid_mart_kids_2"};
            }else if(mContext.getString(R.string.med_Everyday_goods).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_dailyitems"};
            }else if(mContext.getString(R.string.med_Housework).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_housework"};
            }else if(mContext.getString(R.string.med_Convenience).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_conveni"};
            }else{
                return new String[]{"lv0_mart_else"};
            }
        }else if(mContext.getString(R.string.mart_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.shopping_code).equals(categoryCode)){
                return new String[]{"lv0_shopping_else"};
            }else if(mContext.getString(R.string.med_Department_store).equals(categoryCode)){
                return new String[]{"lv0_shopping_else"};
            }else if(mContext.getString(R.string.Sports_clothing).equals(categoryCode)){
                return new String[]{"lv0_mid_shopping_sportbrand"};
            }else if(mContext.getString(R.string.med_Outlet).equals(categoryCode)){
                return new String[]{"lv0_mid_shopping_outlet"};
            }else if(mContext.getString(R.string.med_Fashion).equals(categoryCode)){
                return new String[]{"lv0_mid_shopping_fashion"};
            }else if(mContext.getString(R.string.med_Miscell).equals(categoryCode)){
                return new String[]{"lv0_mid_shopping_miscel"};
            }else{
                return new String[]{"lv0_shopping_else"};
            }
        }else if(mContext.getString(R.string.education_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.education_code).equals(categoryCode)){
                return new String[]{"lv0_education_else"};
            }else if(mContext.getString(R.string.med_School).equals(categoryCode)){
                return new String[]{"lv0_mid_education_school"};
            }else if(mContext.getString(R.string.med_Learning_Materials).equals(categoryCode)){
                return new String[]{"lv0_mid_education_edubook"};
            }else if(mContext.getString(R.string.med_Learning_Facilities).equals(categoryCode)){
                return new String[]{"lv0_mid_education_facility"};
            }else if(mContext.getString(R.string.med_Academy).equals(categoryCode)){
                return new String[]{"lv0_mid_education_academy"};
            }else{
                return new String[]{"lv0_education_else"};
            }

        }else if(mContext.getString(R.string.finance_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.finance_code).equals(categoryCode)){
                return new String[]{"lv0_finance_else"};
            }else if(mContext.getString(R.string.med_finance).equals(categoryCode)){
                return new String[]{"lv0_mid_finance_capital"};
            }else if(mContext.getString(R.string.med_Insurance).equals(categoryCode)){
                return new String[]{"lv0_mid_finance_insurance"};
            }else if(mContext.getString(R.string.med_Tax).equals(categoryCode)){
                return new String[]{"lv0_mid_finance_tax"};
            }else{
                return new String[]{"lv0_finance_else"};
            }
        }else if(mContext.getString(R.string.health_care_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.health_care_code).equals(categoryCode)){
                return new String[]{"lv0_healthcare_else_2"};
            }else if(mContext.getString(R.string.med_Supplements).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_healthproduct"};
            }else if(mContext.getString(R.string.med_Medical_treatment).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_recovery"};
            }else if(mContext.getString(R.string.med_Medical_Equipment).equals(categoryCode)){
                return new String[]{"lv0_healthcare_else_2"};
            }else if(mContext.getString(R.string.med_pharmacy).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_pharmacy"};
            }else if(mContext.getString(R.string.med_Medical).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_clinic"};
            }else if(mContext.getString(R.string.med_Dentist).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_dentist"};
            }else if(mContext.getString(R.string.med_Clinic).equals(categoryCode)){
                return new String[]{"lv0_mid_healthcare_herbalclinic"};
            }else{
                return new String[]{"lv0_healthcare_else_2"};
            }
        }else if(mContext.getString(R.string.livings_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.livings_code).equals(categoryCode)){
                return new String[]{"lv0_livings_else"};
            }else if(mContext.getString(R.string.med_Administrative_expenses).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_utility"};
            }else if(mContext.getString(R.string.med_Real_Estate_Services).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_realtor"};
            }else if(mContext.getString(R.string.med_Monthly_Taxes).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_rent"};
            }else if(mContext.getString(R.string.med_move).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_moving"};
            }else if(mContext.getString(R.string.med_Communication).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_telecom","lv0_mid_livings_telecom_2"};
            }else if(mContext.getString(R.string.med_Bills).equals(categoryCode)){
                return new String[]{"lv0_mid_livings_bills"};
            }else{
                return new String[]{"lv0_livings_else"};
            }
        }else if(mContext.getString(R.string.leports_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.leports_code).equals(categoryCode)){
                return new String[]{"lv0_leports_else"};
            }else if(mContext.getString(R.string.med_Leisure).equals(categoryCode)){
                return new String[]{"lv0_mid_leports_leisure"};
            }else if(mContext.getString(R.string.med_Martial_Arts).equals(categoryCode)){
                return new String[]{"lv0_mid_leports_martial"};
            }else if(mContext.getString(R.string.med_Spa).equals(categoryCode)){
                return new String[]{"lv0_leports_else"};
            }else if(mContext.getString(R.string.med_Sports).equals(categoryCode)){
                return new String[]{"lv0_leports_else"};
            }else if(mContext.getString(R.string.med_yoga).equals(categoryCode)){
                return new String[]{"lv0_mid_leports_fitness_yoga","lv0_mid_leports_fitness_training"};
            }else{
                return new String[]{"lv0_leports_else"};
            }
        }else if(mContext.getString(R.string.transport_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.transport_code).equals(categoryCode)){
                return new String[]{"lv0_transportation_else"};
            }else if(mContext.getString(R.string.med_Public_transportation).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_masstransit"};
            }else if(mContext.getString(R.string.med_Car).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_car"};
            }else if(mContext.getString(R.string.med_Oil).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_gas"};
            }else if(mContext.getString(R.string.med_Taxi).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_taxi"};
            }else if(mContext.getString(R.string.med_Agent).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_taxi"};
            }else if(mContext.getString(R.string.med_Air).equals(categoryCode)){
                return new String[]{"lv0_mid_transportation_airline"};
            }else{
                return new String[]{"lv0_transportation_else"};
            }
        }else if(mContext.getString(R.string.travel_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.travel_code).equals(categoryCode)){
                return new String[]{"lv0_travel_else"};
            }else if(mContext.getString(R.string.med_Sightseeing).equals(categoryCode)){
                return new String[]{"lv0_mid_travel_sightseeing"};
            }else if(mContext.getString(R.string.med_park).equals(categoryCode)){
                return new String[]{"lv0_mid_travel_themepark"};
            }else if(mContext.getString(R.string.med_Souvenir).equals(categoryCode)){
                return new String[]{"lv0_travel_else"};
            }else if(mContext.getString(R.string.med_Accommodation).equals(categoryCode)){
                return new String[]{"lv0_travel_else"};
            }else if(mContext.getString(R.string.med_Travel).equals(categoryCode)){
                return new String[]{"lv0_mid_travel_travel"};
            }else if(mContext.getString(R.string.med_Border_payments).equals(categoryCode)){
                return new String[]{"lv0_travel_else"};
            }else{
                return new String[]{"lv0_travel_else"};
            }
        }else if(mContext.getString(R.string.online_code).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.online_code).equals(categoryCode)){
                return new String[]{"lv0_online_else"};
            }else if(mContext.getString(R.string.med_Internet_Shopping).equals(categoryCode)){
                return new String[]{"lv0_mid_online_internetshop1","lv0_mid_online_internetshop2"};
            }else if(mContext.getString(R.string.med_Home_Shopping).equals(categoryCode)){
                return new String[]{"lv0_mid_online_homeshop"};
            }else if(mContext.getString(R.string.med_Card_Point).equals(categoryCode)){
                return new String[]{"lv0_mid_online_cardpoint"};
            }else{
                return new String[]{"lv0_online_else"};
            }
        }else if(mContext.getString(R.string.family_event).equals(categoryCode.substring(0,2))) {
            if(mContext.getString(R.string.med_family_ect).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_events_flower"};
            } else if(mContext.getString(R.string.med_dues).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_events_flower"};
            }else if(mContext.getString(R.string.med_allowance).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_events_flower"};
            }else if(mContext.getString(R.string.med_offering).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_events_flower"};
            }else if(mContext.getString(R.string.med_donate).equals(categoryCode)){
                return new String[]{"lv0_mid_mart_events_flower"};
            }
            else{
                return new String[]{"lv0_mid_mart_events_flower"};
            }

        }else{//출금
            return new String[]{"lv0_mid_mart_events_flower"};
        }

    }


}
