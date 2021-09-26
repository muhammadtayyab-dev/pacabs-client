package com.techlogix.pacaps.models.ceateUserModel

import com.google.gson.annotations.SerializedName

data class CreateUserResponseModel(@SerializedName("id") val id : Int,
                                   @SerializedName("name") val name : String,
                                   @SerializedName("mobile") val mobile : String,
                                   @SerializedName("email") val email : String,
                                   @SerializedName("password") val password : String,
                                   @SerializedName("verified") val verified : Int,
                                   @SerializedName("typeid") val typeid : Int,
                                   @SerializedName("OTP") val oTP : String,
                                   @SerializedName("tokenId") val tokenId : String,
                                   @SerializedName("referralcode") val referralcode : String,
                                   @SerializedName("referredbycode") val referredbycode : String,
                                   @SerializedName("profileurl") val profileurl : String,
                                   @SerializedName("createdAt") val createdAt : String,
                                   @SerializedName("updatedAt") val updatedAt : String) {}