package io.odbs.roomdb.utils

import android.content.Context
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.db.NoteDb

class DataManager {

    private lateinit var noteList: List<Note>

    fun getAllNotes(context: Context) : List<Note> {
        noteList = NoteDb.getDatabase(context).noteDao().getAllNotes() ?: ArrayList()
        return noteList
    }

    companion object{

        @Volatile
        private var INSTANCE : DataManager? = null
        fun instance(): DataManager {
            return  INSTANCE ?: synchronized(this){
                val instance = DataManager()
                INSTANCE = instance
                instance
            }
        }

    }

}