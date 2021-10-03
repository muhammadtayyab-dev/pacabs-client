package com.techlogix.hirecabs.models.bookingsModels

import com.google.gson.annotations.SerializedName

data class CancellBookingRequestModel(@SerializedName("cancelledBy") val cancelledBy: String,
                                      @SerializedName("cancelledReason") val cancelledReason: String,
                                      @SerializedName("bookingid") val bookingid: Int,
                                      @SerializedName("vehicleid") val vehicleid: Int,
                                      @SerializedName("latitude") val latitude: Double,
                                      @SerializedName("longitude") val longitude: Double
);