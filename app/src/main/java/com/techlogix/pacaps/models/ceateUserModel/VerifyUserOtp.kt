package com.techlogix.pacaps.models.ceateUserModel

import com.google.gson.annotations.SerializedName

data class VerifyUserOtp(@SerializedName("mobile") val mobile : String,
                         @SerializedName("otp") val name : String) {}