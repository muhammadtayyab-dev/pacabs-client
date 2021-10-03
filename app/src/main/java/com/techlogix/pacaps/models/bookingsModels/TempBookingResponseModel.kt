package com.techlogix.hirecabs.models.bookingsModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TempBookingResponseModel(@SerializedName("finalamount") val finalamount: Int,
                                    @SerializedName("finaldistance") val finaldistance: Int,
                                    @SerializedName("tempbookingid") val tempbookingid: Int,
                                    @SerializedName("sourcelatitude") val sourcelatitude: Double,
                                    @SerializedName("sourcelongitude") val sourcelongitude: Double,
                                    @SerializedName("sourceLocation") val sourceLocation: String,
                                    @SerializedName("destinationlatitude") val destinationlatitude: Double,
                                    @SerializedName("destinationlongitude") val destinationlongitude: Double,
                                    @SerializedName("destinationLocation") val destinationLocation: String,
                                    @SerializedName("bookingdate") val bookingdate: String,
                                    @SerializedName("tripestimate") val tripestimate: String,
                                    @SerializedName("userid") val userid: Int,
                                    @SerializedName("username") val username: String,
                                    @SerializedName("usertoken") val usertoken: String,
                                    @SerializedName("usermobile") val usermobile: String,
                                    @SerializedName("triptype") val triptype: Int,
                                    @SerializedName("vehiclecategoryid") val vehiclecategoryid: Int,
                                    @SerializedName("cityid") val cityid: Int,
                                    @SerializedName("paymentmode") val paymentmode: String,
                                    @SerializedName("paidamount") val paidamount: Int,
                                    @SerializedName("txnid") val txnid: String,
                                    @SerializedName("allocationsentstatus") val allocationsentstatus: Int,
                                    @SerializedName("tocityid") val tocityid: Int,
                                    @SerializedName("returndate") val returndate: String,
                                    @SerializedName("status") val status: String,
                                    @SerializedName("statusid") val statusid: Int,
                                    @SerializedName("updatedAt") val updatedAt: String,
                                    @SerializedName("createdAt") val createdAt: String):Serializable