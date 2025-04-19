package com.example.mygeniusgastroproyec.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "user_session"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"
    private const val KEY_LOGGED_IN = "is_logged_in"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, username: String, password: String) {
        getPrefs(context).edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun getUsername(context: Context): String? = getPrefs(context).getString(KEY_USERNAME, null)
    fun getPassword(context: Context): String? = getPrefs(context).getString(KEY_PASSWORD, null)

    fun setLoggedIn(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LOGGED_IN, value).apply()
    }

    fun isLoggedIn(context: Context): Boolean = getPrefs(context).getBoolean(KEY_LOGGED_IN, false)

    fun logout(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
