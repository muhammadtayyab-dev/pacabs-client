package com.techlogix.pacaps.fragments.settingsFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.SettingsActivity
import com.techlogix.pacaps.adapters.ActivityRecyclerAdapterGeneric
import com.techlogix.pacaps.dialogs.AddLocationToFavDialog
import com.techlogix.pacaps.models.GenericResponseModel
import com.techlogix.pacaps.models.favoritesModels.CreateFavLoctionsRequestModel
import com.techlogix.pacaps.models.favoritesModels.DeleteMyFavLocRequestModel
import com.techlogix.pacaps.models.favoritesModels.MyFavoritesResponseModel
import com.techlogix.pacaps.network.APIManager
import com.techlogix.pacaps.utility.GenericCallback
import com.techlogix.pacaps.utility.SharePrefData
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_my_favorites.*

class MyFavoritesFragment<T> : Fragment(), GenericCallback<Any>, View.OnClickListener,
    APIManager.CallbackGenric<Any> {
    var myFavAdapter: ActivityRecyclerAdapterGeneric<T>? = null
    var addToFavDialog: AddLocationToFavDialog? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        rootLayoutAddToFav.setOnClickListener(this)
        (requireActivity() as SettingsActivity<T>).setText("Settings-Favorites")
        APIManager.getInstance().getAllFavLocs(SharePrefData.getInstance().userId, this)
    }

    override fun GenericCallType(T: Any) {
        if(T is MyFavoritesResponseModel){
            val deleteReqModel=DeleteMyFavLocRequestModel(T.customerid,T.name,T.location,T.latitude,T.longitude)
            APIManager.getInstance().deleteMyFavLov(T.id,deleteReqModel,this)
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.rootLayoutAddToFav) {
            addToFavDialog = AddLocationToFavDialog(requireContext(),
                object : AddLocationToFavDialog.addLocAsFavCallback {
                    override fun addlocCallback(place: Place, locType: String) {
                        if (place != null && locType != null) {
                            addLocInFav(locType, place)
                        }
                    }
                })
            addToFavDialog?.show()
        }
    }

    private fun addLocInFav(locType: String, place: Place) {
        val createFavLoctionsRequestModel =
            CreateFavLoctionsRequestModel(SharePrefData.getInstance().userId,
                locType,
                place.address!!,
                place.latLng?.latitude!!,
                place.latLng?.longitude!!)
        APIManager.getInstance().createFavLov(createFavLoctionsRequestModel, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utility.PLACES_CODE) {
            val places = Autocomplete.getPlaceFromIntent(data!!)
            if (addToFavDialog?.isShowing!!) {
                addToFavDialog?.setLocation(places)
            }

        }
    }

    override fun onResult(response: GenericResponseModel<Any>?, requestCode: Int) {
        if (requestCode == 0) {
            myFavAdapter?.updateList(response?.result as T)
        } else if (requestCode == 1) {
            setFavData(response)
        }else if (requestCode == 2) {
            myFavAdapter?.notifiItemRemove()
        }
    }

    private fun setFavData(response: GenericResponseModel<Any>?) {
       var list= response?.result as ArrayList<MyFavoritesResponseModel>
        myFavAdapter=ActivityRecyclerAdapterGeneric<T>(Utility.MY_FAVORITES, list as ArrayList<T>, this)
//        MyFavoritesRecycler.adapter =
//            ActivityRecyclerAdapterGeneric(Utility.MY_FAVORITES, list, this)
        MyFavoritesRecycler.adapter=myFavAdapter

    }


}