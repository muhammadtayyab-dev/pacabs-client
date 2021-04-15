package com.techlogix.pacaps.models.NearestVehiclesModels

import com.google.gson.annotations.SerializedName
import com.techlogix.pacaps.utility.Utility
import java.io.Serializable

data class GetNearestAvailbleVehiclesResponseModel(@SerializedName("vehiclecategoryid") val vehiclecategoryid: Int,
                                                   @SerializedName("duration") val duration: String,
                                                   @SerializedName("dist") val dist: String,
                                                   @SerializedName("vehicle") val vehicle: ArrayList<VahiclesModel>) :
    Serializable {

    var type: Int = Utility.SERVICE_PROVIDER_TYPE;
    var rating:Float=3.0f
}