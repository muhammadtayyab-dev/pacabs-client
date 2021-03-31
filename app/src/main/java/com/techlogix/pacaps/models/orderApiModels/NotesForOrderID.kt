package com.techlogix.pacaps.models.orderApiModels

import com.google.gson.annotations.SerializedName

data class NotesForOrderID(@SerializedName("notes_key_1") val notes_key_1: String,
                           @SerializedName("notes_key_2") val notes_key_2: String)