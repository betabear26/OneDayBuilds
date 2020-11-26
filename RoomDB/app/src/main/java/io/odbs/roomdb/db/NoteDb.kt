package io.odbs.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.odbs.roomdb.utils.Constants
import java.security.AccessControlContext
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDb? = null


        fun getDatabase(context: Context): NoteDb {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                NoteDb::class.java,
                Constants.DATABASE_NAME).build()
                INSTANCE = instance
                instance
            }
        }

    }


}