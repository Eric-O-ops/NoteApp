package com.geektech.noteapp.data.locale.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.geektech.noteapp.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(noteModel: NoteModel)

    @Query("SELECT * FROM note_table")
    fun getAllModels(): LiveData<List<NoteModel>>

}