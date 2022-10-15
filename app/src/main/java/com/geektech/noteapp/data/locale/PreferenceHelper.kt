package com.geektech.noteapp.data.locale

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private var sharedPreference: SharedPreferences =
            context.getSharedPreferences("onBoard", Context.MODE_PRIVATE)

    fun removeValue(nameKey: String){
        sharedPreference.edit()?.remove(nameKey)?.apply()
    }

    fun isChangeNote(): Boolean{
        return sharedPreference.getBoolean("changeNote",false)
    }

    fun isSingUpShowed(nameKey: String): Boolean{
        return sharedPreference.getBoolean(nameKey, false)
    }

    fun isLinearLayout(nameKey: String): Boolean {
        return sharedPreference.getBoolean(nameKey, true)

    }

    fun putValue(nameKey: String, boolean: Boolean) {
        sharedPreference.edit()?.putBoolean(nameKey, boolean)?.apply()
    }

}