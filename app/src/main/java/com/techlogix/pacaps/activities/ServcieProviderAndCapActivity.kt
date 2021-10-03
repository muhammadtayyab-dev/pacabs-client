package com.techlogix.pacaps.activities

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.techlogix.pacaps.R
import com.techlogix.pacaps.fragments.tripInformationFragments.ProvidersAndCabsTripInformationFragment
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel
import kotlinx.android.synthetic.main.activity_service_cap.*

class ServcieProviderAndCapActivity : BaseActivity() {
    var driverInfo: GetNearestAvailbleVehiclesResponseModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_cap)
        if(intent.extras!=null){
            driverInfo=intent.getSerializableExtra("driverInfo") as GetNearestAvailbleVehiclesResponseModel
        }
        backArrowImg.setOnClickListener {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment =
            (supportFragmentManager.fragments[0] as NavHostFragment).childFragmentManager.fragments.get(
                0)
        if(fragment is ProvidersAndCabsTripInformationFragment<*>){
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}