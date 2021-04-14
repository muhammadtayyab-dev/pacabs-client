package com.techlogix.pacaps.models.NearestVehiclesModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VahiclesModel(@SerializedName("id") val id: Int,
                         @SerializedName("vehicleno") val vehicleno: String,
                         @SerializedName("latitude") val latitude: Double,
                         @SerializedName("longitude") val longitude: Double,
                         @SerializedName("speed") val speed: Float,
                         @SerializedName("direction") val direction: Int,
                         @SerializedName("accuracy") val accuracy: Int,
                         @SerializedName("gpsdatetime") val gpsdatetime: String,
                         @SerializedName("loggedin") val loggedin: Int,
                         @SerializedName("onjob") val onjob: Int,
                         @SerializedName("cityid") val cityid: Int,
                         @SerializedName("triptype") val triptype: Int,
                         @SerializedName("vehiclecategoryid") val vehiclecategoryid: Int,
                         @SerializedName("drivername") val drivername: String,
                         @SerializedName("drivermobile") val drivermobile: String,
                         @SerializedName("availability") val availability: Int,
                         @SerializedName("token") val token: String,
                         @SerializedName("bookingid") val bookingid: Int,
                         @SerializedName("usertype") val usertype: Int,
                         @SerializedName("createdAt") val createdAt: String,
                         @SerializedName("updatedAt") val updatedAt: String):Serializable