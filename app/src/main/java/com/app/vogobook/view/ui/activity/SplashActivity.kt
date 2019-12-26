package com.app.vogobook.view.ui.activity

import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import com.app.vogobook.R
import com.app.vogobook.analytics.VogoAnalytics
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.SplashModule
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

/**
 * Created by ben on 7/October/2019.
 */
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var mSessionManager: SessionManager

    @Inject
    lateinit var mFirebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var mVogoAnalytics: VogoAnalytics

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
        mVogoAnalytics.reportScreen(mFirebaseAnalytics, this, Utils.replaceAvitityByScreen(SplashActivity::class.java.simpleName))
        mSessionManager.time_using = System.currentTimeMillis()
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