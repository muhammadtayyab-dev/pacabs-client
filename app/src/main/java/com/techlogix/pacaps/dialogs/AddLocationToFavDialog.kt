package com.techlogix.pacaps.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.techlogix.pacaps.PACAP
import com.techlogix.pacaps.R
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.add_loc_fav_dialog_layout.*

class AddLocationToFavDialog(context: Context, callback: addLocAsFavCallback) : Dialog(context),
    View.OnClickListener {
    var altertDialogCallback: addLocAsFavCallback? = null
    var place: Place? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        setContentView(R.layout.add_loc_fav_dialog_layout)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = lp
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        this.altertDialogCallback = callback
        searchLocTv.setOnClickListener(this)
        favBtn.setOnClickListener(this)
    }

    override fun dismiss() {
        super.dismiss()
        altertDialogCallback?.addlocCallback(place, locTypeTv.text.toString())
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.searchLocTv) {
            PACAP.context?.let { Places.initialize(it, context.getString(R.string.places_key_tayyab)) }
            val fields =
                listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val latLongBoud = RectangularBounds.newInstance(Utility.getBounds())
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                /*.setCountry("pk").setLocationBias(latLongBoud)*/.build(context)
            PACAP.INSTANCE?.getBaseActivity()?.startActivityForResult(intent, Utility.PLACES_CODE)
        } else if (view?.id == R.id.favBtn) {
            if (searchLocTv.text.isEmpty()) {
                searchLocTv.setError("Please add location first")
                return
            } else if (locTypeTv.text.isEmpty()) {
                locTypeTv.setError("Please enter name of location type")
                return
            } else {
                dismiss()
            }
        }
    }

    fun setLocation(loc: Place) {
        searchLocTv.text = loc.address
        this.place = loc;
    }

    interface addLocAsFavCallback {
        fun addlocCallback(place: Place?, locType: String?)
    }


}