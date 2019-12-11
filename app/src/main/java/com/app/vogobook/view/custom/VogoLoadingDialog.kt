package com.app.vogobook.view.custom

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.app.vogobook.R

class VogoLoadingDialog(private val activity: Activity?) : Dialog(activity) {

    init {
        init()
    }

    private fun init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        setContentView(R.layout.progress_dialog)
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