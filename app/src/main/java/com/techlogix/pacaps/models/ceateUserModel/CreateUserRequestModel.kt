package com.techlogix.pacaps.models.ceateUserModel

import com.google.gson.annotations.SerializedName

data class CreateUserRequestModel(@SerializedName("name") val name: String,
                                  @SerializedName("mobile") val mobile: String,
                                  @SerializedName("email") val email: String,
                                  @SerializedName("password") val password: String,
                                  @SerializedName("tokenid") val tokenid: String,
                                  @SerializedName("referredbycode") val referredbycode: String)