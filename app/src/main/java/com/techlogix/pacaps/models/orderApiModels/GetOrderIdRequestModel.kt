package com.techlogix.pacaps.models.orderApiModels

import com.google.gson.annotations.SerializedName

data class GetOrderIdRequestModel(@SerializedName("amount") val amount: Int,
                                  @SerializedName("currency") val currency: String,
                                  @SerializedName("receipt") val receipt: String,
                                  @SerializedName("payment_capture") val payment_capture: Int,
                                  @SerializedName("notes") val notes: NotesForOrderID)