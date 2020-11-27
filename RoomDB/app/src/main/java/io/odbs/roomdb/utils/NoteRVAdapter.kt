package io.odbs.roomdb.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.odbs.roomdb.R
import io.odbs.roomdb.db.Note
import io.odbs.roomdb.fragment.listeners.NoteListFragmentInteractionListener

internal class NoteRVAdapter(
        private var noteList: List<Note>,
        private var listener: NoteListFragmentInteractionListener
    ) : RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_note_list, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.noteContent
        holder.viewParent.setOnClickListener {
            listener.onNoteClick(note)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }



    internal inner class NoteViewHolder(
            itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        var viewParent: View = itemView.findViewById(R.id.parent)
        var titleTextView: TextView = itemView.findViewById(R.id.et_title)
        var contentTextView: TextView = itemView.findViewById(R.id.et_content)
    }

}