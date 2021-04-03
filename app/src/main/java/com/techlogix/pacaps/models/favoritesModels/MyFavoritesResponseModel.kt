package com.techlogix.pacaps.models.favoritesModels

import com.google.gson.annotations.SerializedName
import com.techlogix.pacaps.R
import com.techlogix.pacaps.utility.Utility

data class MyFavoritesResponseModel(@SerializedName("id") val id: Int,
                                    @SerializedName("customerid") val customerid: Int,
                                    @SerializedName("name") val name: String,
                                    @SerializedName("location") val location: String,
                                    @SerializedName("latitude") val latitude: Double,
                                    @SerializedName("longitude") val longitude: Double,
                                    @SerializedName("createdAt") val createdAt: String,
                                    @SerializedName("updatedAt") val updatedAt: String) {

    fun getLoctionImg(): Int {
        if (name.contains("Work", true) || name.contains("Office", true) || name.contains("Shop",
                true) || name.contains("Business", true)) {
            return R.drawable.office_bg;
        } else if (name.contains("Home", true) || name.contains("Ghar",
                true) || name.contains("House", true)) {
            return R.drawable.home_bg
        } else {
            return R.drawable.ic_favorite_loc
        }
    }
}