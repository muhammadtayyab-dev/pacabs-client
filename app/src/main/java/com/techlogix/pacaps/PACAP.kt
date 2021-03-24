package com.techlogix.pacaps

import android.app.Activity
import android.app.Application
import android.content.Context
import com.techlogix.pacaps.activities.BaseActivity

class PACAP : Application() {

    private var baseActivity: Activity? = null

    companion object {
        var INSTANCE: PACAP? = null
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        context = this

    }

    fun setActivity(baseActivity: Activity) {
        this.baseActivity = baseActivity
    }

    fun getBaseActivity(): Activity? {
        return baseActivity
    }
}