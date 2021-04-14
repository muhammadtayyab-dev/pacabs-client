package com.techlogix.pacaps.models.NearestVehiclesModels

import com.google.gson.annotations.SerializedName

data class GetNearAvailableVehiclesRequestModel(@SerializedName("cityid") val cityid: Int,
                                                @SerializedName("sourceLatitude") val sourceLatitude: Double,
                                                @SerializedName("sourceLongitude") val sourceLongitude: Double,
                                                @SerializedName("triptype") val triptype: Int,
                                                @SerializedName("userId") val userId: Int)