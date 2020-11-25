package io.odbs.roomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.odbs.roomdb.R

class NoteListFragment : Fragment() {

    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NoteListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}