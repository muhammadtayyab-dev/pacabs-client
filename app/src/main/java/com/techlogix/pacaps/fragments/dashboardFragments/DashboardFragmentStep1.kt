package com.techlogix.pacaps.fragments.dashboardFragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import com.techlogix.pacaps.activities.DashboardActivity
import com.techlogix.pacaps.models.GenericResponseModel
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearAvailableVehiclesRequestModel
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel
import com.techlogix.pacaps.models.NearestVehiclesModels.VahiclesModel
import com.techlogix.pacaps.models.cityModel.GetCityFromLatLongResponseModel
import com.techlogix.pacaps.network.APIManager
import com.techlogix.pacaps.utility.PermissionUtils
import com.techlogix.pacaps.utility.SharePrefData
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_dashboard_step1.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class DashboardFragmentStep1<T> : Fragment(), OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener, APIManager.CallbackGenric<T> {
    var googleMap: GoogleMap? = null
    var mapFragment: SupportMapFragment? = null
    var googleAPIClient: GoogleApiClient? = null
    var mLocationRequest: LocationRequest? = null
    var mCurrentMarker: Marker? = null
    var scheduler: ScheduledExecutorService? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_step1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        if (PermissionUtils.hasLocationPermissionGranted(context)) {
            mapFragment =
                childFragmentManager.findFragmentById(R.id.dashboardMap) as SupportMapFragment;
            mapFragment?.getMapAsync(this)
            taxiProviderSingleToggle.selectButton(R.id.taxiBtn)
            taxiProviderSingleToggle.setOnSelectListener {}
            APIManager.getInstance().getDefaultSettings(this)

        } else {
            PermissionUtils.requestLocationPermissions(activity, 1111)
        }

        searchBtn.setOnClickListener {
            findNavController().navigate(DashboardFragmentStep1Directions.dashboardStep1Action())
        }

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        googleMap?.isMyLocationEnabled = true

        val locationButton =
            (mapFragment?.view?.findViewById<View>(Integer.parseInt("1"))?.parent as View).findViewById<ImageView>(
                Integer.parseInt("2"))

        /* locationButton?.background=ContextCompat.getDrawable(this,R.drawable.back_arrow)
         locationButton?.setBackgroundColor(ContextCompat.getColor(this,R.color.zong_pink))*/
        locationButton?.setImageResource(R.drawable.current_loc)

        val layoutParams: RelativeLayout.LayoutParams =
            locationButton?.getLayoutParams() as RelativeLayout.LayoutParams
        // position on right bottom
        // position on right bottom
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)

        this.googleMap?.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap?.getUiSettings()?.setZoomControlsEnabled(true);
        this.googleMap?.getUiSettings()?.setZoomGesturesEnabled(true);
        this.googleMap?.getUiSettings()?.setCompassEnabled(true);

        //Initialize Google Play Services
        if (PermissionUtils.hasLocationPermissionGranted(context)) {
            buildGoogleApiClient();
            this.googleMap?.setMyLocationEnabled(true);
        } else {
            buildGoogleApiClient();
            this.googleMap?.setMyLocationEnabled(true);
        }

    }

    protected fun buildGoogleApiClient() {
        googleAPIClient = GoogleApiClient.Builder(context).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this).addApi(LocationServices.API).build()
        googleAPIClient?.connect()
    }

    private fun getUpdatedTaxi() {


        scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler!!.scheduleAtFixedRate({
            getTexi()
        }, 5, 5, TimeUnit.SECONDS)


    }

    private fun getTexi() {
        if ((requireActivity() as DashboardActivity<*>).cityId != -1) {
            println("working")
            val request =
                GetNearAvailableVehiclesRequestModel((requireActivity() as DashboardActivity<*>).cityId!!,
                    Utility.currentUserLoc!!.latitude,
                    Utility.currentUserLoc!!.longitude,
                    1,
                    SharePrefData.getInstance().userId)
            APIManager.showDialog = false;
            APIManager.getInstance().getNearestAvailableVehicles(this, request)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1111) {
            mapFragment =
                childFragmentManager.findFragmentById(R.id.dashboardMap) as SupportMapFragment;
            mapFragment?.getMapAsync(this)
            buildGoogleApiClient()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(bundle: Bundle?) {
        mLocationRequest = LocationRequest();
        mLocationRequest?.interval = 1000
        mLocationRequest?.fastestInterval = 1000
        mLocationRequest?.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        if (PermissionUtils.hasLocationPermissionGranted(context)) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleAPIClient,
                mLocationRequest,
                this)
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(p0: Location) {
        try {
//            p0.latitude = 33.5651
//            p0.longitude = 73.0169
            Utility.currentUserLoc = LatLng(p0.latitude, p0.longitude)
            val markerOption = MarkerOptions()
            markerOption.position(LatLng(p0.latitude, p0.longitude))
            markerOption.icon(Utility.bitmapDescriptorFromVector(requireContext(),
                R.drawable.ic_pin))
            markerOption.title("You")
            googleMap?.clear()
            mCurrentMarker = googleMap?.addMarker(markerOption)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0.latitude,
                p0.longitude), 15.0f))
            APIManager.getInstance().getCityByLatLong(this, p0.latitude, p0.longitude)
            if (googleAPIClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleAPIClient, this)
            }
        } catch (e: Exception) {

        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onResult(response: GenericResponseModel<T>?, requestCode: Int) {
        if (requestCode == Utility.SETTINGS) {

        } else if (requestCode == Utility.GET_CITIES) {
            val cityResponseModel = response?.result as GetCityFromLatLongResponseModel
            (requireActivity() as DashboardActivity<*>).cityId = cityResponseModel.cityid;
            (requireActivity() as BaseActivity).pacap?.cityId = cityResponseModel.cityid
            val request = GetNearAvailableVehiclesRequestModel(cityResponseModel.cityid,
                Utility.currentUserLoc!!.latitude,
                Utility.currentUserLoc!!.longitude,
                1,
                SharePrefData.getInstance().userId)
            APIManager.showDialog = false;
            APIManager.getInstance().getNearestAvailableVehicles(this, request)
//            getUpdatedTaxi()
        } else if (requestCode == Utility.GET_VEHICLES) {
            val responseModel =
                response?.result as ArrayList<GetNearestAvailbleVehiclesResponseModel>
            (requireActivity() as DashboardActivity<*>).taxiDriverList = responseModel
            if (responseModel.size > 0) {
                googleMap?.clear()

                var markerOption = MarkerOptions()
                markerOption.position(LatLng(Utility.currentUserLoc!!.latitude,
                    Utility.currentUserLoc!!.longitude))
                markerOption.icon(Utility.bitmapDescriptorFromVector(requireContext(),
                    R.drawable.ic_pin))
                markerOption.title("You")
                googleMap?.addMarker(markerOption)
                for (mainVehicleResponse: GetNearestAvailbleVehiclesResponseModel in responseModel) {
                    for (model: VahiclesModel in mainVehicleResponse.vehicle) {
                        markerOption = MarkerOptions()
                        markerOption.position(LatLng(model.latitude, model.longitude))
                        markerOption.icon(Utility.bitmapDescriptorFromVector(requireContext(),
                            R.drawable.ic_cab))
                        markerOption.title(mainVehicleResponse.dist + " away")
                        googleMap?.addMarker(markerOption)
                    }
                }
            } else {
                (requireActivity() as BaseActivity).showToast("Sorry,No Vehicles available right now")
            }
            Handler().postDelayed(runnable, 5000)

        }
    }

    override fun onPause() {
        super.onPause()
        Handler().removeCallbacks(runnable)
    }

    val runnable = Runnable {
        getTexi()
    }

}