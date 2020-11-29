package io.odbs.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.odbs.navigationcomponent.R
import kotlinx.android.synthetic.main.fragment_choose_recipient.*

class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        next_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChooseRecipientFragment()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.next_btn -> navController.navigate(R.id.action_chooseReceipientFragment_to_speifyAmountFragment)
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}