package io.odbs.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase(){

    abstract fun noteDao(): NoteDao

}