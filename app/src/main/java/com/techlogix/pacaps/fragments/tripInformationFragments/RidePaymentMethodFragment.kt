package com.techlogix.pacaps.fragments.tripInformationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.fragment_ride_payment_method.*

class RidePaymentMethodFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        paymentMethodToggleGroup.setOnSelectListener {
            if (it.isSelected) {
                findNavController().navigate(RidePaymentMethodFragmentDirections.gotoPaymentConfirmationFrag())
            }
        }
    }
}