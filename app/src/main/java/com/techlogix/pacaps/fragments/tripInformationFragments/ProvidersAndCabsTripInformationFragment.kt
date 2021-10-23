package com.techlogix.pacaps.fragments.tripInformationFragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html.fromHtml
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.techlogix.hirecabs.models.bookingsModels.TempBookingRequestModel
import com.techlogix.hirecabs.models.bookingsModels.TempBookingResponseModel
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.BaseActivity
import com.techlogix.pacaps.activities.DashboardActivity
import com.techlogix.pacaps.activities.ServcieProviderAndCapActivity
import com.techlogix.pacaps.adapters.ProviderAndCabsFragmentGeneinceRecyclerAdapter
import com.techlogix.pacaps.dialogs.AlertDialogCallback
import com.techlogix.pacaps.dialogs.BookRideDialog
import com.techlogix.pacaps.models.GenericResponseModel
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearAvailableVehiclesRequestModel
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabDetailsInformationModel
import com.techlogix.pacaps.models.TimeInKmModel
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabsAndTheirFareResponseModel
import com.techlogix.pacaps.models.cityModel.GetCityFromLatLongResponseModel
import com.techlogix.pacaps.network.APIManager
import com.techlogix.pacaps.utility.GenericCallBackWithType
import com.techlogix.pacaps.utility.SharePrefData
import com.techlogix.pacaps.utility.Utility
import com.techlogix.pacaps.utility.Utility.Companion.SHOW_AVAILABLE_CAB
import kotlinx.android.synthetic.main.fragment_taxi_trip_information.*

class ProvidersAndCabsTripInformationFragment<T> : Fragment(), RadioGroup.OnCheckedChangeListener,
    GenericCallBackWithType<T>, APIManager.CallbackGenric<T> {

    var baseActivity: BaseActivity? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_taxi_trip_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(view: View) {
        baseActivity = requireActivity() as BaseActivity
        tripInfoToggleGroup.selectButton(R.id.lcoalBtn)
        val text =
            fromHtml("Extra charges on exceeding packages.<font size=\"6\"\n" + "          face=\"verdana\"\n" + "          color=\"#051CC4\">View Details</font>")
        packagesInfoTv.setText(text)
        userNameTv.text =
            (activity as ServcieProviderAndCapActivity).driverInfo?.vehicle?.get(0)?.drivername
        rateTv.text =
            (activity as ServcieProviderAndCapActivity).driverInfo?.rating.toString() ?: "1.0"
        distanceTv.text = "-" + (activity as ServcieProviderAndCapActivity).driverInfo?.dist ?: "--"
        rateBar.rating = (activity as ServcieProviderAndCapActivity).driverInfo?.rating ?: 1.0f
        serviceTypeTv.text = "Taxi-" + Utility.getAddress(requireContext(),
            (activity as ServcieProviderAndCapActivity).driverInfo?.vehicle?.get(0)?.latitude!!,
            (activity as ServcieProviderAndCapActivity).driverInfo?.vehicle?.get(0)?.longitude!!)
            .get(0).subLocality + "-" + Utility.getAddress(requireContext(),
            (activity as ServcieProviderAndCapActivity).driverInfo?.vehicle?.get(0)?.latitude!!,
            (activity as ServcieProviderAndCapActivity).driverInfo?.vehicle?.get(0)?.longitude!!)
            .get(0).locality

        addressTv.text = Utility.getAddress(requireContext(),
            Utility.currentUserLoc?.latitude ?: 0.0,
            Utility.currentUserLoc?.longitude ?: 0.0).get(0).getAddressLine(0).toString()
        addressTv.tag = Place.builder().setLatLng(Utility.currentUserLoc).build()

        bookTypeRG.setOnCheckedChangeListener(this)
        addressTv.setOnClickListener {
            searchResult(Utility.START_PLACES_CODE)

        }
        locTv.setOnClickListener {
            searchResult(Utility.DESTINATION_PLACES_CODE)
        }
        initDummyData()

    }

    private fun initDummyData() {
        val arrayList = arrayListOf<TimeInKmModel>()
        arrayList.add(TimeInKmModel("1 Hr", "10 Km", false))
        arrayList.add(TimeInKmModel("2 Hr", "20 Km", false))
        arrayList.add(TimeInKmModel("3 Hr", "30 Km", false))
        arrayList.add(TimeInKmModel("4 Hr", "40 Km", false))
        arrayList.add(TimeInKmModel("5 Hr", "50 Km", false))
        arrayList.add(TimeInKmModel("6 Hr", "60 Km", false))
        timeInKmRecyclerView.adapter =
            ProviderAndCabsFragmentGeneinceRecyclerAdapter(arrayList as ArrayList<T>,
                Utility.PROVIDER_CAB_TIME_IN_KM_RECYCLER_TYPE,
                this)
        /*val cabArray = arrayListOf<CabDetailsInformationModel>()
        cabArray.add(CabDetailsInformationModel("Mini",
            "Sasti or Aram de sawari",
            "3 Mins",
            "₹100",
            false))
        cabArray.add(CabDetailsInformationModel("Sedan", "Top Sedan", "5 Mins", "₹150", false))
        cabArray.add(CabDetailsInformationModel("Prime SUV",
            "Spacious SUVs",
            "2 Mins",
            "₹250",
            false))
        cabArray.add(CabDetailsInformationModel("Luxury",
            "Plush, bussiness class ride",
            "3 Mins",
            "₹500",
            false))
        cabTypeRecycler.adapter =
            ProviderAndCabsFragmentGeneinceRecyclerAdapter(cabArray as ArrayList<T>,
                Utility.PROVIDER_CAB_CAR_TYPE_RECYCLER_TYPE,
                this)*/
    }

    override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
        val button = p0?.findViewById(checkedId) as RadioButton

        if (checkedId == R.id.bookNowRb && button.isPressed) {
            if ((cabTypeRecycler.adapter != null)) {
                if (((cabTypeRecycler.adapter as ProviderAndCabsFragmentGeneinceRecyclerAdapter<*>).
                    getSelectedItem() != null)) {
                    if (validate()) setTempBooking()
                } else {
                    baseActivity?.showToast("Please Select Vehicle first to proceed")
                    button.isChecked = false
                }
            } else {
                baseActivity?.showToast("Please Select Vehicle first to proceed")
                button.isChecked = false
            }
        } else if (checkedId == R.id.bookLaterRb && button.isPressed) {
            if ((cabTypeRecycler.adapter != null) && ((cabTypeRecycler.adapter as ProviderAndCabsFragmentGeneinceRecyclerAdapter<*>).getSelectedItem() != null)) {
                BookRideDialog(requireContext(), object : AlertDialogCallback {
                    override fun onDissmiss() {
                        findNavController().navigate(
                            ProvidersAndCabsTripInformationFragmentDirections.gotoRideConfirmationFragment())

                    }
                }).show()
            } else {
                baseActivity?.showToast("Please Select Vehicle first to proceed")
                button.isChecked = false
            }
        }
    }

    private fun validate(): Boolean {

        if (locTv.text.isNullOrEmpty()) {
            baseActivity?.showToast("Please select destination location")
            return false
        } else return true

    }

    override fun returnCallback(t: T, level: Int) {
        if (t is TimeInKmModel) {
            APIManager.showDialog = false
            APIManager.instance.getCabsAndTheirFare(this,
                (baseActivity)?.pacap?.cityId.toString() ?: "-1",
                "1")
        }
    }

    override fun onResult(response: GenericResponseModel<T>?, requestCode: Int) {
        if (requestCode == SHOW_AVAILABLE_CAB) {
            APIManager.showDialog = true
            val responseModel = response?.result as ArrayList<CabsAndTheirFareResponseModel>
            if (responseModel.size > 0) {
                cabTypeRecycler.adapter = ProviderAndCabsFragmentGeneinceRecyclerAdapter(
                    responseModel as ArrayList<T>,
                    Utility.PROVIDER_CAB_CAR_TYPE_RECYCLER_TYPE,
                    this)
            }
        } else if (requestCode == Utility.GET_CITIES) {
            val cityResponseModel = response?.result as GetCityFromLatLongResponseModel
            (requireActivity() as BaseActivity).pacap?.cityId = cityResponseModel.cityid

        } else if (requestCode == Utility.TEMPBOOKING) {
            if (response?.status ?: false) {
                val bodyResponse = response?.result as TempBookingResponseModel
                BookRideDialog(requireContext(), object : AlertDialogCallback {
                    override fun onDissmiss() {
                        findNavController().navigate(
                            ProvidersAndCabsTripInformationFragmentDirections.gotoRideConfirmationFragment())

                    }
                }).show()
            }
        }
    }

    fun searchResult(code: Int) {

        val fields =
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val latLongBoud = Utility.getBounds()?.let { RectangularBounds.newInstance(it) }
        if (latLongBoud != null) {

            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                /*.setCountry("pk").setLocationBias(latLongBoud)*/.build(requireContext())
            //            PACAP.INSTANCE?.getBaseActivity()?.startActivityForResult(intent, Utility.PLACES_CODE)
            startActivityForResult(intent, code)

        } else Toast.makeText(requireContext(), "Enable GPS Please", Toast.LENGTH_SHORT).show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utility.DESTINATION_PLACES_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {

                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        locTv.setText(place.address)
                        locTv.tag = place

                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i("Error", status.statusMessage)
                    }!!
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }

        } else if (requestCode == Utility.START_PLACES_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {

                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        addressTv.setText(place.address)
                        addressTv.tag = place
                        APIManager.showDialog = false
                        APIManager.getInstance().getCityByLatLong(this,
                            place.latLng?.latitude ?: 0.0,
                            place.latLng?.longitude ?: 0.0)


                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i("Error", status.statusMessage)
                    }!!
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }

        }
    }

    private fun setTempBooking() {
        val request = TempBookingRequestModel(0,
            Utility.getCurrentDateTime(),
            baseActivity?.pacap?.cityId ?: 1,
            locTv.text.toString(),
            (locTv.tag as Place).latLng?.latitude!!,
            (locTv.tag as Place).latLng?.longitude!!,
            0.0f,
            "Not-Define-Here",
            Utility.getCurrentDateTime(),
            addressTv.text.toString(),
            (addressTv.tag as Place).latLng?.latitude!!,
            (addressTv.tag as Place).latLng?.longitude!!,
            "Unbook",
            0,
            0,
            baseActivity?.pacap?.cityId ?: 1,
            "" + ((cabTypeRecycler.adapter as ProviderAndCabsFragmentGeneinceRecyclerAdapter<T>).getSelectedItem() as CabsAndTheirFareResponseModel).basefare,
            1,
            "",
            SharePrefData.getInstance().userId,
            SharePrefData.getInstance().userNum,
            SharePrefData.getInstance().userName,
            "",
            ((cabTypeRecycler.adapter as ProviderAndCabsFragmentGeneinceRecyclerAdapter<T>).getSelectedItem() as CabsAndTheirFareResponseModel).vehiclecategory.id)
        APIManager.showDialog = true;
        APIManager.instance.tempBooking(this, request)
    }
}
