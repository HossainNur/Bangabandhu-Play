package com.durbar.bangabandhuplay.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {

    private static SharedPreferences.Editor editor = null;
    private static SharedPreferences prefs = null;
    public static final String MY_PREF_NAME = "OTT_SHARED_PREF";
    //Retrofit BASE URL
    public static final String BASE_URL = "http://159.223.86.243/api/v1/app/";
    public static final String BASE_URL_LIVE = "https://ott.durbar.live/api/v1/web/";
    public static final String CONTENT_SLUG = "CONTENT_SLUG";
    public static final String CONTENT_ID = "CONTENT_ID";
    public static final String CONTENT_UUID = "CONTENT_UUID";
    public static final String CONTENT_SECTION_TITLE = "CONTENT_SECTION_TITLE";
    public static final String CONTENT_IS_HOME = "CONTENT_IS_HOME";
    public static final String CONTENT_IS_MORE = "CONTENT_IS_MORE";
    public static final String APP_ID = "71e0f92db4ca450c9443831e9d24cb2c";
    public static final String TOKEN = "007eJxTYHgRwKw99zHjn+v8ShUhc8oNAjfd3flqb0PGjZp1U29trslWYDA3TDVIszRKSTJJTjQxNUi2NDExtjA2TLVMMTJJTjJKnp25LrUhkJHh91dxBkYoBPG5GPJKi5IzEvPyUnMYGACfWSQm";
    public static final Integer HOME = 0;
    public static final Integer MOVIES = 1;
    public static final Integer DOCUMENTARY = 2;

    public static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    public static final String MOVIES_FRAGMENT = "MOVIES_FRAGMENT";
    public static final String DOCUMENTARY_FRAGMENT = "DOCUMENTARY_FRAGMENT";
    public static Boolean IS_FROM_PLAYER = false;
    public static Boolean IS_MORE_CONTENT = false;
    public static Boolean IS_MORE_HOME = false;

    public static Boolean END_CALL_PRESSED = false;
    public static final int CHANNEL_PROFILE_LIVE_BROADCASTING = 1;


    //set string value to shared pref
    public static void setEditor(Context context, String key, String value) {
        if (editor == null) {
            editor = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE).edit();
        }
        editor.putString(key, value);
        editor.apply();
    }

    //set boolean value to shared pref
    public static void setEditor(Context context, String key, boolean value) {
        if (editor == null) {
            editor = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE).edit();
        }
        editor.putBoolean(key, value);
        editor.apply();
    }

    //get more activity slug to shared pref
    public static String getSharedPref(Context context, String key) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE);
        }
        return prefs.getString(key, null);
    }

    public static boolean getSharedPrefBoolean(Context context,String key){
        if (prefs == null) {
            prefs = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE);
        }
        return prefs.getBoolean(key,false);
    }

}
