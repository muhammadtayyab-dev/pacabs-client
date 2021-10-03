package com.techlogix.pacaps.models.cabAndDriverInformationModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VehicleCategorayModel(@SerializedName("id") val id: Int,
                                 @SerializedName("category") val category: String,
                                 @SerializedName("seatingcapacity") val seatingcapacity: Int,
                                 @SerializedName("status") val status: Int):Serializable {}