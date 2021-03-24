package com.techlogix.pacaps.fragments.dashboardFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.techlogix.pacaps.R
import com.techlogix.pacaps.adapters.DriverDetailsRecyclerAdapter
import com.techlogix.pacaps.models.cabAndDriverInformationModels.DriverDetailModel
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_dashboard_list_fragmnet.*

class DashboardListFragmnet : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_list_fragmnet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
        val list = arrayListOf<DriverDetailModel>()
        list.add(DriverDetailModel(
            "Rahul",
            4,
            "(8) - 3KM",
            "Taxi Service - Dehli, India",
            Utility.HEADER_TYPE))
        list.add(DriverDetailModel(
            "Ajay",
            3,
            "(7) - 8KM",
            "Taxi Service - Mumbai, India",
            Utility.SERVICE_PROVIDER_TYPE))
        list.add(DriverDetailModel(
            "Kapoor",
            3,
            "(8) - 3KM",
            "Taxi Service - Rajkot, India",
            Utility.SERVICE_PROVIDER_TYPE))
        list.add(DriverDetailModel(
            "Kahan",
            2,
            "(4) - 7KM",
            "Taxi Service - Nagar, India",
            Utility.SERVICE_PROVIDER_TYPE))
        userListRecyclerView.adapter = DriverDetailsRecyclerAdapter(list)
    }

}