package com.app.vogobook.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.app.vogobook.R


/**
 * Dialog loading
 */
class VogoLoadingDialog(private val activity: Activity?) : Dialog(activity) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.vogo_progress_dialog)
        setCancelable(false)
    }

    override fun show() {
        if (activity != null && !activity.isFinishing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (activity != null && !activity.isFinishing) {
            super.dismiss()
        }
    }
}