package io.odbs.roomdb.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
data class Note(
        @ColumnInfo(name = "title") var title : String,

        @ColumnInfo(name = "content") var noteContent : String,

        @ColumnInfo(name = "pinned") var isPinned : Boolean

) {
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}