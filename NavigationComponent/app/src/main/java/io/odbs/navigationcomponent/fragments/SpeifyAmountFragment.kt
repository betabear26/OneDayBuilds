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
import kotlinx.android.synthetic.main.fragment_choose_recipient.cancel_btn
import kotlinx.android.synthetic.main.fragment_speify_amount.*

class SpeifyAmountFragment : Fragment(), View.OnClickListener {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpeifyAmountFragment()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> navController.navigate(R.id.action_speifyAmountFragment_to_confirmationFragment)
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}