package io.odbs.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.db.NoteDb
import io.odbs.roomdb.fragment.NoteListFragment
import io.odbs.roomdb.fragment.listeners.NoteListFragmentInteractionListener
import io.odbs.roomdb.utils.Constants
import io.odbs.roomdb.utils.DataManager
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), NoteListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFirstRun()
        setInitialFragment()
    }

    private fun setupFirstRun(){
        val isFirstRun : Boolean = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE).getBoolean(Constants.FIRST_RUN_KEY, true)
        if(isFirstRun){

            //Adding example note 1
            var note = Note(
                "Title1",
                    Constants.SINGLE_LINE_NOTE,
                    false
            )

            thread {
                NoteDb.getDatabase(context = this).noteDao().insert(note)
            }

            //Adding example note 2
            var note2 = Note(
                    "Title2",
                    Constants.BIG_NOTE,
                    false
            )

            thread {
                NoteDb.getDatabase(context = this).noteDao().insert(note2)
            }
            getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE).edit().putBoolean(Constants.FIRST_RUN_KEY, false).apply()
        }
    }

    private fun setInitialFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_holder, NoteListFragment.newInstance(), Constants.NOTE_LIST_FRAGMENT_TAG).commitAllowingStateLoss()
    }

    //****************** Fragment interaction methods**************************//

    override fun onAddButtonClick() {
        //Go to note Edit Fragment
    }

    override fun onNoteClick(note: Note) {
        //Go to note Edit Fragment
    }
}