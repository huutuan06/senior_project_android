package com.app.vogobook.view.ui.activity

import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.SplashModule
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.SessionManager
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var mSessionManager: SessionManager

    private var mDelayHandler: Handler? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            if (TextUtils.equals(Constants.EMPTY_STRING, mSessionManager.token)) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(SplashModule(this)).inject(this)
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