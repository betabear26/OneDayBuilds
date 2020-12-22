package io.odbs.movie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import io.odbs.movie.R
import io.odbs.movie.databinding.FragmentInitBinding
import io.odbs.movie.databinding.FragmentMovieDetailBinding

class MovieListFragment : Fragment() {

    private lateinit var navController: NavController
    private var _binding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }
}