package com.techlogix.pacaps.fragments.tripInformationFragments

import android.os.Bundle
import android.text.Html.fromHtml
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController
import com.techlogix.pacaps.R
import com.techlogix.pacaps.adapters.ProviderAndCabsFragmentGeneinceRecyclerAdapter
import com.techlogix.pacaps.dialogs.AlertDialogCallback
import com.techlogix.pacaps.dialogs.BookRideDialog
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabDetailsInformationModel
import com.techlogix.pacaps.models.TimeInKmModel
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_taxi_trip_information.*

class ProvidersAndCabsTripInformationFragment : Fragment(), RadioGroup.OnCheckedChangeListener {


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

    private fun initViews(view: View) {
        tripInfoToggleGroup.selectButton(R.id.lcoalBtn)
        val text =
            fromHtml("Extra charges on exceeding packages.<font size=\"6\"\n" + "          face=\"verdana\"\n" + "          color=\"#051CC4\">View Details</font>")
        packagesInfoTv.setText(text)
        bookTypeRG.setOnCheckedChangeListener(this)
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
        timeInKmRecyclerView.adapter = ProviderAndCabsFragmentGeneinceRecyclerAdapter(arrayList,
            Utility.PROVIDER_CAB_TIME_IN_KM_RECYCLER_TYPE)
        val cabArray = arrayListOf<CabDetailsInformationModel>()
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
        cabTypeRecycler.adapter = ProviderAndCabsFragmentGeneinceRecyclerAdapter(cabArray,
            Utility.PROVIDER_CAB_CAR_TYPE_RECYCLER_TYPE)
    }

    override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
        val button = p0?.findViewById(checkedId) as RadioButton
        if (button.isPressed) {
            BookRideDialog(requireContext(), object : AlertDialogCallback {
                override fun onDissmiss() {
                    findNavController().navigate(ProvidersAndCabsTripInformationFragmentDirections.gotoRideConfirmationFragment())

                }
            }).show()
        }
    }
}
