package com.durbar.bangabandhuplay.utils

import android.content.Context
import android.content.SharedPreferences

object Constants {
    private var editor: SharedPreferences.Editor? = null
    private var prefs: SharedPreferences? = null
    const val MY_PREF_NAME = "OTT_SHARED_PREF"

    //Retrofit BASE URL
   // const val BASE_URL = "http://159.223.86.243/api/v1/app/"       // live
    const val BASE_URL = "http://13.250.125.189/api/v1/app/"  // debug test

    const val BASE_URL_LIVE = "https://ott.durbar.live/api/v1/web/"
    const val CONTENT_SLUG = "CONTENT_SLUG"
    const val CONTENT_ID = "CONTENT_ID"
    const val CONTENT_UUID = "CONTENT_UUID"
    const val CONTENT_SECTION_TITLE = "CONTENT_SECTION_TITLE"
    const val CONTENT_IS_HOME = "CONTENT_IS_HOME"
    const val CONTENT_IS_MORE = "CONTENT_IS_MORE"
    const val APP_ID = "71e0f92db4ca450c9443831e9d24cb2c"
    const val TOKEN =
        "007eJxTYHgRwKw99zHjn+v8ShUhc8oNAjfd3flqb0PGjZp1U29trslWYDA3TDVIszRKSTJJTjQxNUi2NDExtjA2TLVMMTJJTjJKnp25LrUhkJHh91dxBkYoBPG5GPJKi5IzEvPyUnMYGACfWSQm"
    const val HOME = 0
    const val MOVIES = 1
    const val DOCUMENTARY = 2
    const val HOME_FRAGMENT = "HOME_FRAGMENT"
    const val MOVIES_FRAGMENT = "MOVIES_FRAGMENT"
    const val DOCUMENTARY_FRAGMENT = "DOCUMENTARY_FRAGMENT"
    const val TEST_FCM = false   // make true and test notification for debug
    var IS_FROM_PLAYER = false
    var IS_MORE_CONTENT = false
    var IS_MORE_HOME = false
    var END_CALL_PRESSED = false
    const val CHANNEL_PROFILE_LIVE_BROADCASTING = 1
    var IS_TUNES = false

    //set string value to shared pref
    fun setEditor(context: Context, key: String?, value: String?) {
        if (editor == null) {
            editor = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE).edit()
        }
        editor!!.putString(key, value)
        editor!!.apply()
    }

    //set boolean value to shared pref
    fun setEditor(context: Context, key: String?, value: Boolean) {
        if (editor == null) {
            editor = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE).edit()
        }
        editor!!.putBoolean(key, value)
        editor!!.apply()
    }

    //get more activity slug to shared pref
    fun getSharedPref(context: Context, key: String?): String? {
        if (prefs == null) {
            prefs = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE)
        }
        return prefs!!.getString(key, null)
    }

    fun getSharedPrefBoolean(context: Context, key: String?): Boolean {
        if (prefs == null) {
            prefs = context.getSharedPreferences(MY_PREF_NAME, Context.MODE_PRIVATE)
        }
        return prefs!!.getBoolean(key, false)
    }
}
