package io.odbs.roomdb.fragment.listeners

import io.odbs.roomdb.db.Note

interface NoteListFragmentInteractionListener {

    fun onAddButtonClick()

    fun onNoteClick(note: Note)
}