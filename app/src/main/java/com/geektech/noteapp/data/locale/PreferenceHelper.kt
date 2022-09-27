package com.geektech.noteapp.data.locale

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private var sharedPreference: SharedPreferences? = null


    fun unit(context: Context) {
        sharedPreference =
            context.getSharedPreferences("onBoard", Context.MODE_PRIVATE)
    }

    fun isOnBoardShowed(): Boolean {

        return sharedPreference!!.getBoolean("isOnBoardShowed", false)
    }

    fun putValue(boolean: Boolean){
        val editor = sharedPreference?.edit()
        editor?.putBoolean("isOnBoardShowed",boolean)
        editor?.apply()
    }
}