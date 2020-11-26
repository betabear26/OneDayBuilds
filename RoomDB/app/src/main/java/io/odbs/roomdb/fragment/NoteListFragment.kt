package io.odbs.roomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.odbs.roomdb.R
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.fragment.listeners.NoteListFragmentInteractionListener
import io.odbs.roomdb.utils.DataManager
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : Fragment() {

    private val pinnedList: MutableList<Note> by lazy { ArrayList() }
    private val otherList: MutableList<Note> by lazy { ArrayList() }
    private val fragmentInteractionListener by lazy { context as NoteListFragmentInteractionListener}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initLayout()
        fillData()

        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    private fun initData(){
        val list = context?.let { DataManager.instance().getAllNotes(it) }

        list?.forEach{
            if(it.isPinned) pinnedList.add(it) else otherList.add(it)
        }
    }

    private fun initLayout(){
        //init the search bar

        //init the add button
        button_add.setOnClickListener {
            fragmentInteractionListener.onAddButtonClick()
        }
    }

    private fun fillData(){
        if(pinnedList.isEmpty() && otherList.isEmpty()){
            no_note.visibility = View.VISIBLE
            pinned_notes_container.visibility = View.GONE
            other_notes_container.visibility = View.GONE
            return
        }

        no_note.visibility = View.VISIBLE

        if(pinnedList.isNotEmpty()){
            pinned_notes_container.visibility = View.VISIBLE
            pinned_notes_list.layoutManager = LinearLayoutManager(context)
            //Write the adapter
        }

        if(otherList.isNotEmpty()){
            other_notes_container.visibility = View.VISIBLE
            other_notes_list.layoutManager = LinearLayoutManager(context)
            //Write the adapter
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NoteListFragment()
    }
}