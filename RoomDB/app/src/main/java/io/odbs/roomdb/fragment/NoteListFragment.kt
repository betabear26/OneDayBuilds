package io.odbs.roomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.contentcapture.DataShareWriteAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import io.odbs.roomdb.R
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.fragment.listeners.NoteListFragmentInteractionListener
import io.odbs.roomdb.utils.DataManager
import io.odbs.roomdb.utils.NoteRVAdapter
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlin.concurrent.thread

class NoteListFragment : Fragment() {

    private val pinnedList: MutableList<Note> by lazy { ArrayList() }
    private val otherList: MutableList<Note> by lazy { ArrayList() }
    private val fragmentInteractionListener by lazy { context as NoteListFragmentInteractionListener }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        fillData()
    }

    private fun initData(){
        context?.let { DataManager.instance().init(it) }
        Thread.sleep(1000)
        val list = DataManager.instance().getAllNotes()
        list.forEach{
            if(it.isPinned) pinnedList.add(it) else otherList.add(it)
        }
    }

    private fun initLayout() {
        //init the search bar

        //init the add button
        button_add.setOnClickListener {
            fragmentInteractionListener.onAddButtonClick()
        }
    }

    private fun fillData() {
        if (pinnedList.isEmpty() && otherList.isEmpty()) {
            no_note.visibility = View.VISIBLE
            pinned_notes_container.visibility = View.GONE
            other_notes_container.visibility = View.GONE
            return
        }

        no_note.visibility = View.VISIBLE

        if (pinnedList.isNotEmpty()) {
            pinned_notes_container.visibility = View.VISIBLE
            pinned_notes_list.layoutManager = LinearLayoutManager(context)
            pinned_notes_list.adapter =
                NoteRVAdapter(noteList = pinnedList, listener = fragmentInteractionListener)
        } else {
            pinned_notes_container.visibility = View.GONE
            pinned_notes_list.layoutManager = null
            pinned_notes_list.adapter = null
        }

        if (otherList.isNotEmpty()) {
            other_notes_container.visibility = View.VISIBLE
            other_notes_list.layoutManager = LinearLayoutManager(context)
            other_notes_list.adapter =
                NoteRVAdapter(noteList = otherList, listener = fragmentInteractionListener)
        } else {
            other_notes_container.visibility = View.GONE
            other_notes_list.layoutManager = null
            other_notes_list.adapter = null
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NoteListFragment()
    }
}