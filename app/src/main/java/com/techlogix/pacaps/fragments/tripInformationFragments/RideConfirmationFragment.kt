package com.techlogix.pacaps.fragments.tripInformationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.R
import com.techlogix.pacaps.dialogs.AlertDialogCallback
import com.techlogix.pacaps.dialogs.RideCancelDialog
import com.techlogix.pacaps.dialogs.RideCancelledDialog
import kotlinx.android.synthetic.main.fragment_ride_confirmation.*

class RideConfirmationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        btn_ok.setOnClickListener {
            findNavController().navigate(RideConfirmationFragmentDirections.gotoRidePaymentMethodFrag())
        }
        cancelBtn.setOnClickListener {
            RideCancelDialog(requireContext(), object : AlertDialogCallback {
                override fun onDissmiss() {
                    RideCancelledDialog(requireContext(), object : AlertDialogCallback {
                        override fun onDissmiss() {
                            activity?.finish()
                        }
                    }).show()
                }
            }).show()
        }
    }

}