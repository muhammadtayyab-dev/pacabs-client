package com.techlogix.pacaps.fragments.registrationFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.PACAP.Companion.INSTANCE
import com.techlogix.pacaps.R
import com.techlogix.pacaps.dialogs.PacapDialog
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {
    var navHostFragment: NavHostFragment? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupBtn.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.splashToLoginSignupAction(0))
        }
        loginBtn.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.splashToLoginSignupAction(1))
        }


    }

}