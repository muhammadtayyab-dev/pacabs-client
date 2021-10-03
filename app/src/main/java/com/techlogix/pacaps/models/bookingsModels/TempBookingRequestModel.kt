package com.techlogix.hirecabs.models.bookingsModels

import com.google.gson.annotations.SerializedName

data class TempBookingRequestModel(@SerializedName("allocationsentstatus") var allocationsentstatus: Int,
                                   @SerializedName("bookingdate") var bookingdate: String,
                                   @SerializedName("cityid") var cityid: Int,
                                   @SerializedName("destinationLocation") var destinationLocation: String,
                                   @SerializedName("destinationlatitude") var destinationlatitude: Double,
                                   @SerializedName("destinationlongitude") var destinationlongitude: Double,
                                   @SerializedName("paidamount") var paidamount: Float,
                                   @SerializedName("paymentmode") var paymentmode: String,
                                   @SerializedName("returndate") var returndate: String,
                                   @SerializedName("sourceLocation") var sourceLocation: String,
                                   @SerializedName("sourcelatitude") var sourcelatitude: Double,
                                   @SerializedName("sourcelongitude") var sourcelongitude: Double,
                                   @SerializedName("status") var status: String,
                                   @SerializedName("statuscode") var statuscode: Int,
                                   @SerializedName("tempbookingid") var tempbookingid: Int,
                                   @SerializedName("tocityid") var tocityid: Int,
                                   @SerializedName("tripestimate") var tripestimate: String,
                                   @SerializedName("triptype") var triptype: Int,
                                   @SerializedName("txnid") var txnid: String,
                                   @SerializedName("userid") var userid: Int,
                                   @SerializedName("usermobile") var usermobile: String,
                                   @SerializedName("username") var username: String,
                                   @SerializedName("usertoken") var usertoken: String,
                                   @SerializedName("vehiclecategoryid") var vehiclecategoryid: Int) {


}

