package com.app.vogobook.view.custom

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import com.app.vogobook.R
import com.app.vogobook.utils.objects.Utils
import javax.inject.Inject

class TickAnimation @Inject constructor(private var context: Context, private var actvity: Activity) {

    fun loadTick() {
        val inflater = actvity.getLayoutInflater()
        val toastLayout =
            inflater.inflate(R.layout.layout_tick, actvity.findViewById(R.id.toast_layout))
        val imvTick = toastLayout.findViewById(R.id.imvTick) as ImageView
        imvTick.getLayoutParams().width =
            (Utils.getWidth(actvity, Utils.Metrics.WIDTH) * 0.3).toInt()
        imvTick.getLayoutParams().height =
            (Utils.getWidth(actvity, Utils.Metrics.WIDTH) * 0.3).toInt()
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastLayout
        toast.setGravity(Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }
}