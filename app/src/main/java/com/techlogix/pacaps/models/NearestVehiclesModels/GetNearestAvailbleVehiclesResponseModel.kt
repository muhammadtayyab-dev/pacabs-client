package com.techlogix.pacaps.models.NearestVehiclesModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetNearestAvailbleVehiclesResponseModel(@SerializedName("vehiclecategoryid") val vehiclecategoryid: Int,
                                                   @SerializedName("duration") val duration: String,
                                                   @SerializedName("dist") val dist: String,
                                                   @SerializedName("vehicle")val vehicle:ArrayList<VahiclesModel>
                                                   ):Serializable