package com.techlogix.pacaps.models.tripsModels

import com.google.gson.annotations.SerializedName

data class TripEstimateRequestModel(@SerializedName("sourceLatitude") val sourceLatitude:Double,
                                    @SerializedName("sourceLongitude") val sourceLongitude:Double,
                                    @SerializedName("destinationLatitude") val destinationLatitude:Double,
                                    @SerializedName("destinationLongitude") val destinationLongitude:Double,
                                    @SerializedName("vehicleId") val vehicleId:Int,
                                    @SerializedName("userId") val userId:Int,
                                    @SerializedName("triptype") val triptype:Int,
                                    @SerializedName("cityid") val cityid:Int)