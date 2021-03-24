package com.techlogix.pacaps.fragments.settingsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.fragment_my_settings.*

class MySettingsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFavLayout.setOnClickListener {
            findNavController().navigate(MySettingsFragmentDirections.gotoMyFavFrag())
        }

        myProfileLayout.setOnClickListener {
            findNavController().navigate(MySettingsFragmentDirections.gotoMyProfileFrag())
        }

    }


}