package com.techlogix.pacaps.models.cabAndDriverInformationModels

import com.google.gson.annotations.SerializedName
import com.techlogix.pacaps.R
import java.io.Serializable

data class CabsAndTheirFareResponseModel(@SerializedName("id") val id: Int,
                                         @SerializedName("vehiclecategoryid") val vehiclecategoryid: Int,
                                         @SerializedName("vehiclecategory") val vehiclecategory: VehicleCategorayModel,
                                         @SerializedName("cityid") val cityid: Int,
                                         @SerializedName("city") val city: String,
                                         @SerializedName("triptypeid") val triptypeid: Int,
                                         @SerializedName("triptype") val triptype: String,
                                         @SerializedName("basekm") val basekm: Int,
                                         @SerializedName("basefare") val basefare: Int,
                                         @SerializedName("perkm") val perkm: Int,
                                         @SerializedName("permin") val permin: Int,
                                         @SerializedName("exkmrate") val exkmrate: Int,
                                         @SerializedName("waitingcharges") val waitingcharges: Int,
                                         @SerializedName("maxcostpercentage") val maxcostpercentage: Int,
                                         @SerializedName("createdAt") val createdAt: String,
                                         @SerializedName("updatedAt") val updatedAt: String) :
    Serializable {

    var isSelected: Boolean = false;
    var carType: Int = R.drawable.car_icon_top


}
