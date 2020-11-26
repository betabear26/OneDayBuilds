package io.odbs.roomdb.db

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteTable")
    fun getAllNotes() : List<Note>?

    @Query("SELECT * FROM notetable WHERE title LIKE :title")
    fun findByTitle(title: String): Note

    @Insert
    fun insert(vararg note : Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(vararg note: Note)


}