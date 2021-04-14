package com.techlogix.pacaps.models.cityModel

import com.google.gson.annotations.SerializedName

data class GetCityFromLatLongResponseModel(@SerializedName("cityid") val cityid: Int,
                                           @SerializedName("cityname") val cityname: String) {

}