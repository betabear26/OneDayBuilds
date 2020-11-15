package io.odbs.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.odbs.roomdb.fragment.NoteListFragment
import io.odbs.roomdb.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitialFragment()
    }

    private fun setInitialFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_holder, NoteListFragment.newInstance(), Constants.NOTE_LIST_FRAGMENT_TAG).commitAllowingStateLoss()
    }
}