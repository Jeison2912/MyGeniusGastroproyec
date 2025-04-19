package com.example.mygeniusgastroproyec.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "user_session"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"
    private const val KEY_EMAIL = "email"
    private const val KEY_DESC = "descripcion"
    private const val KEY_LOGGED_IN = "is_logged_in"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Guardar todo en el registro
    fun saveUser(context: Context, username: String, password: String) {
        getPrefs(context).edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    // Guardar datos individuales
    fun saveUsuario(context: Context, username: String) {
        getPrefs(context).edit().putString(KEY_USERNAME, username).apply()
    }

    fun savePassword(context: Context, password: String) {
        getPrefs(context).edit().putString(KEY_PASSWORD, password).apply()
    }

    fun saveEmail(context: Context, email: String) {
        getPrefs(context).edit().putString(KEY_EMAIL, email).apply()
    }

    fun saveDescripcion(context: Context, descripcion: String) {
        getPrefs(context).edit().putString(KEY_DESC, descripcion).apply()
    }

    // Obtener datos
    fun getUsuario(context: Context): String {
        return getPrefs(context).getString(KEY_USERNAME, "") ?: ""
    }

    fun getPassword(context: Context): String {
        return getPrefs(context).getString(KEY_PASSWORD, "") ?: ""
    }

    fun getEmail(context: Context): String {
        return getPrefs(context).getString(KEY_EMAIL, "") ?: ""
    }

    fun getDescripcion(context: Context): String {
        return getPrefs(context).getString(KEY_DESC, "") ?: ""
    }

    // Sesión activa
    fun setLoggedIn(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LOGGED_IN, value).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_LOGGED_IN, false)
    }

    // Cerrar sesión
    fun logout(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
