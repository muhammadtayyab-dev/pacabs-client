package com.techlogix.pacaps.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.SphericalUtil
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class Utility {

    companion object {
        val PLACES_CODE: Int=1214
        val HEADER_TYPE = 0
        val SERVICE_PROVIDER_TYPE: Int = 11
        val DRIVER_TYPE: Int = 12
        val PROVIDER_CAB_TIME_IN_KM_RECYCLER_TYPE: Int = 1
        val PROVIDER_CAB_CAR_TYPE_RECYCLER_TYPE: Int = 2
        val NAV_ITEMS: Int = 3
        val TEMPBOOKING = 82
        val START_PLACES_CODE: Int = 1213
        val DESTINATION_PLACES_CODE: Int = 1214
        val SHOW_AVAILABLE_CAB = 53
        val MY_RIDES: Int = 4
        var currentUserLoc: LatLng? = null;
        val RAZORPAY: Int = 9977
        val OFFERS_DISCOUNT: Int = 5
        val timeDateFormat = "yyyy-MM-dd HH:mm:ss"

        val MY_FAVORITES: Int = 6
        val GET_CITIES: Int = 612
        val GET_VEHICLES: Int = 121
        val SETTINGS: Int = 12112
        val EST_PRICE_FOR_VEHICLE: Int = 122781
        fun bitmapDescriptorFromVector(context: Context,
                                       @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
            val background = ContextCompat.getDrawable(context, vectorDrawableResourceId)
            background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
            val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
            vectorDrawable!!.setBounds(40,
                20,
                vectorDrawable.intrinsicWidth + 40,
                vectorDrawable.intrinsicHeight + 20)
            val bitmap = Bitmap.createBitmap(background.intrinsicWidth,
                background.intrinsicHeight,
                Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            background.draw(canvas)
//            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }

        fun emailValidator(email: String?): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun getBounds(): LatLngBounds {
            val distanceFromCenterToCorner = 5 * Math.sqrt(2.0)
            val southwestCorner =
                SphericalUtil.computeOffset(currentUserLoc, distanceFromCenterToCorner, 225.0)
            val northeastCorner =
                SphericalUtil.computeOffset(currentUserLoc, distanceFromCenterToCorner, 45.0)

            return LatLngBounds(southwestCorner,northeastCorner)
        }

        fun getAddress(context: Context,lat:Double,long:Double):List<Address>{
            val geocoder=Geocoder(context, Locale.getDefault())
            return geocoder.getFromLocation(lat,long,1)

        }
        fun getCurrentDateTime(): String {
            val date = Calendar.getInstance().time;
            val simpleDateFormate = SimpleDateFormat(timeDateFormat, Locale.getDefault())
            return simpleDateFormate.format(date)
        }
    }
}