package com.techlogix.pacaps.models.ceateUserModel

import com.google.gson.annotations.SerializedName

data class VerifyUserWithMobileAndPasswoadRequest(@SerializedName("mobile") val mobile : String,
                                                  @SerializedName("password") val password : String) {}