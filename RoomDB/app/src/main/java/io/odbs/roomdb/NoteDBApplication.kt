package io.odbs.roomdb

import android.app.Application
import io.odbs.roomdb.db.NoteDb

class NoteDBApplication : Application() {
    val database by lazy { NoteDb.getDatabase(this) }
}