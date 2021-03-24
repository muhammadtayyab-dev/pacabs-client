package com.techlogix.pacaps.activities

import android.os.Bundle
import com.techlogix.pacaps.R
import com.techlogix.pacaps.adapters.ActivityRecyclerAdapterGeneric
import com.techlogix.pacaps.models.offerAndDisountModels.OffersAndDiscoutModel
import com.techlogix.pacaps.utility.GenericCallback
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.activity_offers_and_discount.*

class OffersAndDiscountActivity : BaseActivity(), GenericCallback<Any> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers_and_discount)
        initViews()
    }

    private fun initViews() {
        backArrowImg.setOnClickListener {
            super.onBackPressed()
        }
        val listOffers = arrayListOf<OffersAndDiscoutModel>()
        listOffers.add(OffersAndDiscoutModel(getString(R.string.date_15_nov_2020),
            getString(R.string.flat_50_off_upto_rs25_on_all_rides),
            "5 Rides , 10 Days",
            "₹ 10",
            "₹ 20"))
        listOffers.add(OffersAndDiscoutModel(getString(R.string.date_15_nov_2020),
            getString(R.string.flat_50_off_upto_rs25_on_all_rides),
            "1 Rides , 15 Days",
            "₹ 5",
            "₹ 10"))
        listOffers.add(OffersAndDiscoutModel(getString(R.string.date_15_nov_2020),
            getString(R.string.flat_50_off_upto_rs25_on_all_rides),
            "2 Rides , 5 Days",
            "₹ 10",
            "₹ 20"))
        myOffersRecycelrView.adapter =
            ActivityRecyclerAdapterGeneric(Utility.OFFERS_DISCOUNT, listOffers, this)
    }

    override fun GenericCallType(T: Any) {
        TODO("Not yet implemented")
    }
}