package com.geektech.noteapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.geektech.noteapp.data.locale.PreferenceHelper
import com.geektech.noteapp.data.locale.db.room.AppDatabase

class App : Application() {

    companion object {

        private var context: Context? = null
        private var appDatabase: AppDatabase? = null
        lateinit var preferenceHelper: PreferenceHelper

        fun getDataIntense(): AppDatabase?{
            if (appDatabase == null) {
                appDatabase = context?.let {
                    Room.databaseBuilder(
                        it,
                        AppDatabase::class.java,
                        "note_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return appDatabase
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initPreference()
        getDataIntense()
    }

    private fun initPreference() {
        preferenceHelper = PreferenceHelper(this)
    }
}