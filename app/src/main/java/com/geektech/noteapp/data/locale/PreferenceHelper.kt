package com.geektech.noteapp.data.locale

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private var sharedPreference: SharedPreferences =
            context.getSharedPreferences("onBoard", Context.MODE_PRIVATE)

    fun isOnBoardShowed(nameKey: String): Boolean {
        return sharedPreference.getBoolean(nameKey, false)
    }
    fun isSingUpShowed(nameKey: String): Boolean{
        return sharedPreference.getBoolean(nameKey, false)
    }

    fun putValue(nameKey: String, boolean: Boolean) {
        sharedPreference.edit()?.putBoolean(nameKey, boolean)?.apply()
    }

}