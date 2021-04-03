package com.techlogix.pacaps.utility

import androidx.annotation.Nullable
import java.util.*

interface GenericCallback<Any> {
    fun GenericCallType(T: Any)
}