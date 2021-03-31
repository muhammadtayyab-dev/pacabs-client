package com.techlogix.pacaps.models.orderApiModels

import com.google.gson.annotations.SerializedName

data class GetOrderIdResponseModel(@SerializedName("id") val id: String,
                                   @SerializedName("entity") val entity: String,
                                   @SerializedName("amount") val amount: Int,
                                   @SerializedName("amount_paid") val amount_paid: Int,
                                   @SerializedName("amount_due") val amount_due: Int,
                                   @SerializedName("currency") val currency: String,
                                   @SerializedName("receipt") val receipt: String,
                                   @SerializedName("offer_id") val offer_id: String,
                                   @SerializedName("status") val status: String,
                                   @SerializedName("attempts") val attempts: Int,
                                   @SerializedName("notes") val notes: NotesForOrderID,
                                   @SerializedName("created_at") val created_at: String) {
}