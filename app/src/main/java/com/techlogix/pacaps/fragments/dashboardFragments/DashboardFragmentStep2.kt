package com.techlogix.pacaps.fragments.dashboardFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.studentparenttutorapplication.adapters.FragmentsViewPagerAdapter
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import kotlinx.android.synthetic.main.fragment_dashboard_step2.*

class DashboardFragmentStep2 : Fragment() {
    var baseActivity: BaseActivity? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        baseActivity = activity as BaseActivity?
        listMapTabLayout.setupWithViewPager(listMapViewpager)
        setupViewpager(listMapViewpager)
        taxiProviderSingleToggle.selectButton(R.id.taxiBtn)
        taxiProviderSingleToggle.setOnSelectListener {
        }


    }

    private fun setupViewpager(listMapViewpager: ViewPager?) {
        val fragsList = arrayListOf<Fragment>()
        val fragsTitleList = arrayListOf<String>()

        fragsList.add(DashboardListFragmnet())
        fragsList.add(DashboardMapsFragment())

        fragsTitleList.add(getString(R.string.list))
        fragsTitleList.add(getString(R.string.map))

        val fragViewAdapter = context?.let {
            FragmentsViewPagerAdapter(it, childFragmentManager, fragsList, fragsTitleList)
        }

        listMapViewpager?.adapter = fragViewAdapter
    }

}