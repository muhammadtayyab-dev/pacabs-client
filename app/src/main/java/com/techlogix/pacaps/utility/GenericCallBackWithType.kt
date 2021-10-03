package com.techlogix.pacaps.utility

interface GenericCallBackWithType<T> {
    fun returnCallback(t: T, level: Int)
}