package com.techlogix.pacaps.models.settingsModels

import com.google.gson.annotations.SerializedName

data class GetSettingsResponseModels(@SerializedName("id") val id:Int,
                                     @SerializedName("customercarenumber") val customercarenumber:String,
                                     @SerializedName("advancebookingtime") val advancebookingtime:String,
                                     @SerializedName("nooffavsearchable") val nooffavsearchable:Int,
                                     @SerializedName("noofshareallowedtofamily") val noofshareallowedtofamily:Int,
                                     @SerializedName("cancellationtime") val cancellationtime:String,
                                     @SerializedName("razorpayKey") val razorpayKey:String,
                                     @SerializedName("razorpaypwd") val razorpaypwd:String,
                                     @SerializedName("createdAt") val createdAt:String,
                                     @SerializedName("updatedAt") val updatedAt:String
)