package com.geektech.noteapp.data.locale.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.noteapp.data.locale.db.room.dao.NoteDao
import com.geektech.noteapp.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getNoteDao():NoteDao


}