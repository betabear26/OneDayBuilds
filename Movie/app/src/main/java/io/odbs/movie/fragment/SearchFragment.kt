package io.odbs.movie.fragment

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import io.odbs.movie.databinding.FragmentSearchBinding
import io.odbs.movie.viewmodel.SearchResultViewModel

class SearchFragment : Fragment() {


    private lateinit var navController: NavController
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var searchResultViewModel: SearchResultViewModel

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

        searchResultViewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        searchResultViewModel.observer.observe(viewLifecycleOwner,
            { t -> println(t?.totalResults) })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchResultViewModel.callSearchApi(query!!, 1)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return binding.root
    }
}