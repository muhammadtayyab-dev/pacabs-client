package com.techlogix.pacaps.utility

import androidx.annotation.Nullable
import java.util.*

interface GenericCallback<T> {
    fun GenericCallType(T: Any)
}