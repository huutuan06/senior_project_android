package com.app.vogobook.view.ui.activity

import android.content.Intent
import android.os.Handler
import com.app.vogobook.R
import com.app.vogobook.utils.Constants

class SplashActivity : BaseActivity() {
    private var mDelayHandler: Handler? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun distributedDaggerComponents() {
        //TODO
    }

    override fun initAttributes() {
        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, Constants.TIME_DELAY)
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}