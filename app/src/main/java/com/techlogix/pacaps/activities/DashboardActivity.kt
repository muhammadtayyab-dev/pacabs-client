package com.techlogix.pacaps.activities

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.techlogix.pacaps.R
import com.techlogix.pacaps.adapters.ActivityRecyclerAdapterGeneric
import com.techlogix.pacaps.fragments.dashboardFragments.DashboardFragmentStep1
import com.techlogix.pacaps.fragments.dashboardFragments.DashboardMapsFragment
import com.techlogix.pacaps.models.NavMenuModel
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel
import com.techlogix.pacaps.utility.GenericCallback
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity<T> : BaseActivity(), View.OnClickListener, GenericCallback<Any> {
    var drawerLayout: DrawerLayout? = null
    var taxiDriverList:ArrayList<GetNearestAvailbleVehiclesResponseModel>?=null
    var cityId: Int? = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initViews()
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerImg.setOnClickListener(this)
        val navArray = arrayListOf<NavMenuModel>()
        navArray.add(NavMenuModel(R.drawable.payment_bg, getString(R.string.payment), false))
        navArray.add(NavMenuModel(R.drawable.my_rides_bg, getString(R.string.my_rides), false))
        navArray.add(NavMenuModel(R.drawable.invite_friends_bg,
            getString(R.string.invites_firends),
            false))
        navArray.add(NavMenuModel(R.drawable.discounts_bg,
            getString(R.string.offer_discount),
            false))
        navArray.add(NavMenuModel(R.drawable.notifiacaitons_bg,
            getString(R.string.notications),
            false))
        navArray.add(NavMenuModel(R.drawable.settings_bg, getString(R.string.settings), false))
        navArray.add(NavMenuModel(R.drawable.support_bg, getString(R.string.support), false))
        navItemsRecycler.adapter = ActivityRecyclerAdapterGeneric(Utility.NAV_ITEMS, navArray, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1111) {
            val fragment =
                (supportFragmentManager.fragments[0] as NavHostFragment).childFragmentManager.fragments.get(
                    0)
            if (fragment is DashboardFragmentStep1<*> || fragment is DashboardMapsFragment) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }

        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.drawerImg) {
            if (drawerLayout!!.isOpen) {
                drawerLayout!!.closeDrawer(Gravity.LEFT)
            } else {
                drawerLayout?.open()
            }
        }
    }


    override fun GenericCallType(t: Any) {
        if (drawerLayout!!.isOpen) {
            drawerLayout!!.closeDrawer(Gravity.LEFT)
        }

        Handler().postDelayed(Runnable {

            if (t is Int) {
                // For Header Item Click Callback
                if (t == 0) {
                    //For Payment Item click
                    openActivity(PaymentActivity::class.java, null)

                } else if (t == 1) {
                    //For MyRides Item click
                    openActivity(MyRidesActivity::class.java, null)


                } else if (t == 2) {
                    //For InviteFirends Item click
                    openActivity(InviteFriendsActivity::class.java, null)


                } else if (t == 3) {

                    openActivity(OffersAndDiscountActivity::class.java, null)


                } else if (t == 4) {
                    //For Notification Item click
                    showToast("Notifications")


                } else if (t == 5) {
                    //For Settings Item click

                    openActivity(SettingsActivity::class.java, null)



                } else if (t == 6) {
                    //For Support Item click


                }

            }
        }, 1000)
    }
}