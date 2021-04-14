package com.techlogix.pacaps.models.tripsModels

import com.google.gson.annotations.SerializedName

data class TripEstimationsResponseModel(@SerializedName("min") val min: String,
                                        @SerializedName("max") val max: String,
                                        @SerializedName("dist") val dist: Double) {}