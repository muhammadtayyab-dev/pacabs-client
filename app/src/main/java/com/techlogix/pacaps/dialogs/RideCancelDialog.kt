package com.techlogix.pacaps.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.techlogix.pacaps.R
import kotlinx.android.synthetic.main.ride_cancellation_dialog_layout.*

class RideCancelDialog(context: Context, alertDialogCallback: AlertDialogCallback) :
    Dialog(context) {
    var altertCallback: AlertDialogCallback? = null
    var yesNo: Boolean = false

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        setContentView(R.layout.ride_cancellation_dialog_layout)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = lp
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        this.altertCallback = alertDialogCallback
        yesBtn.setOnClickListener {
            yesNo = true
            dismiss()
        }

        noBtn.setOnClickListener {
            yesNo = false
            dismiss()
        }
    }


    override fun dismiss() {
        super.dismiss()
        if (yesNo) altertCallback?.onDissmiss()
    }
}