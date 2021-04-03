package com.techlogix.pacaps.models.favoritesModels

import com.google.gson.annotations.SerializedName

data class DeleteMyFavLocRequestModel(@SerializedName("customerid")val customerid:Int,
                                      @SerializedName("name")val name:String,
                                      @SerializedName("location")val location:String,
                                      @SerializedName("latitude")val latitude:Double,
                                      @SerializedName("longitude")val longitude:Double)