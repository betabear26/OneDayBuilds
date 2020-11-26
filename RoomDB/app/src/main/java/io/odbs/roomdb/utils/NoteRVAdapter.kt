package io.odbs.roomdb.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.odbs.roomdb.R
import io.odbs.roomdb.db.Note

class NoteRVAdapter(noteList : List<Note>): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private var noteList = noteList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_note_list, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return noteList.size
    }



    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}