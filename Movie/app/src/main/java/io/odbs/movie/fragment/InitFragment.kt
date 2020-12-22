package io.odbs.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import io.odbs.movie.R
import io.odbs.movie.databinding.FragmentInitBinding
import io.odbs.movie.util.NetworkUtil

class InitFragment : Fragment() {

    private lateinit var navController: NavController
    private var _binding: FragmentInitBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.searchParent.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.searchParent to "searchAnimation"
            )
            navController.navigate(R.id.action_initFragment_to_searchFragment, null, null, extras)
        }
        binding.retry.setOnClickListener { checkOnline() }

        checkOnline()
    }

    private fun checkOnline(){
        if(!NetworkUtil.isInternetConnected(requireContext())){
            if(binding.offlineParent.isVisible){
                Toast.makeText(requireContext(), getString(R.string.still_offline), Toast.LENGTH_SHORT).show()
                return
            }
            showOffline()
        } else{
            showOnline()
        }
    }

    private fun showOffline(){
        binding.offlineParent.visibility = View.VISIBLE
        binding.searchParent.visibility = View.GONE
    }

    private fun showOnline(){
        binding.offlineParent.visibility = View.GONE
        binding.searchParent.visibility = View.VISIBLE
    }

}