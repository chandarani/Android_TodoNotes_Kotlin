package com.chanda.todonotesapp.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NotesDao {

    @Query("SELECT * FROM notesData")
    fun getAll(): List<Notes>


    @Insert(onConflict = REPLACE)
    fun insert(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)

    @Delete
    fun delete(notes: Notes)
    @Query("DELETE FROM notesData WHERE isTaskCOmpleted = :status")
    fun deleteNotes(status: Boolean)
}