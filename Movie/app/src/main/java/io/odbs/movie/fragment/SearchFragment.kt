package io.odbs.movie.fragment

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import io.odbs.movie.R
import io.odbs.movie.databinding.FragmentInitBinding
import io.odbs.movie.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {


    private lateinit var navController: NavController
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchView.setIconifiedByDefault(false)

        if(binding.searchView.requestFocus()) {
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
        //binding.searchView.requestFocus()
        return binding.root
    }
}