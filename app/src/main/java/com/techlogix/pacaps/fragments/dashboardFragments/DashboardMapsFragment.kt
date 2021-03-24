package com.techlogix.pacaps.fragments.dashboardFragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
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
import com.techlogix.pacaps.utility.PermissionUtils
import com.techlogix.pacaps.utility.Utility
import java.lang.Exception

class DashboardMapsFragment : Fragment(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {
    var googleMap: GoogleMap? = null
    var mapFragment: SupportMapFragment? = null
    var googleAPIClient: GoogleApiClient? = null
    var mLocationRequest: LocationRequest? = null
    var mCurrentMarker: Marker? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        if (PermissionUtils.hasLocationPermissionGranted(context)) {
            mapFragment =
                childFragmentManager.findFragmentById(R.id.usersMaps) as SupportMapFragment;
            mapFragment?.getMapAsync(this)

        } else {
            PermissionUtils.requestLocationPermissions(activity, 1111)
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


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1111) {
            mapFragment =
                childFragmentManager.findFragmentById(R.id.dashboardMap) as SupportMapFragment;
            mapFragment?.getMapAsync(this)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
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
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(p0: Location) {
            try {
                val markerOption = MarkerOptions()
                markerOption.position(LatLng(p0.latitude, p0.longitude))
                markerOption.icon(Utility.bitmapDescriptorFromVector(requireActivity(),
                    R.drawable.ic_pin))

                mCurrentMarker = googleMap?.addMarker(markerOption)
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0.latitude,
                    p0.longitude), 15.0f))
                if (googleAPIClient != null) {
                    LocationServices.FusedLocationApi.removeLocationUpdates(googleAPIClient, this)
                }
            }catch (e:Exception){

            }

    }

}