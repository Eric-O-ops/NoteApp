package com.geektech.noteapp

import android.app.Application
import com.geektech.noteapp.data.locale.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //initPreference()

    }

    private fun initPreference(){
        val preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(this)

    }

}