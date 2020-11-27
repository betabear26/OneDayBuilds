package io.odbs.roomdb.utils

import android.content.Context
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.db.NoteDb
import kotlin.concurrent.thread

class DataManager {

    @Volatile
    private lateinit var noteList: List<Note>

    @Synchronized
    fun init(context: Context){
        thread {
            noteList = NoteDb.getDatabase(context = context).noteDao().getAllNotes() ?: ArrayList()
        }
    }

    fun getAllNotes() : List<Note> {
        //noteList = NoteDb.getDatabase(context).noteDao().getAllNotes() ?: ArrayList()
        return noteList
    }

    companion object {

        @Volatile
        private var INSTANCE: DataManager? = null
        fun instance(): DataManager {
            return INSTANCE ?: synchronized(this) {
                val instance = DataManager()
                INSTANCE = instance
                instance
            }
        }

    }

}