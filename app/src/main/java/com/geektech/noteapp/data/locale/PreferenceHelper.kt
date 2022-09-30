package com.geektech.noteapp.data.locale

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private var sharedPreference: SharedPreferences =
            context.getSharedPreferences("onBoard", Context.MODE_PRIVATE)

    fun isOnBoardShowed(): Boolean {
        return sharedPreference.getBoolean("key", false)
    }

    fun putValue(boolean: Boolean) {
        sharedPreference.edit()?.putBoolean("key", boolean)?.apply()
    }
}