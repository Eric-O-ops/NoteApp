package com.geektech.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteModel(
    @ColumnInfo
    var title: String,

    @ColumnInfo
    var description: String,

    @ColumnInfo
    var date: String,

    @ColumnInfo
    var time: String

    ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}