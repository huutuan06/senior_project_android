package com.app.vogobook.analytics

import android.os.Bundle
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.ui.activity.BaseActivity
import com.google.firebase.analytics.FirebaseAnalytics

class VogoAnalytics {

    fun reportScreen(analytics: FirebaseAnalytics, activity: BaseActivity, screenName: String) {
        analytics.setCurrentScreen(activity, screenName, screenName)
    }

    fun reportLoginSocialNetwork(analytics: FirebaseAnalytics, platform: String) {
        val bundle = Bundle()
        bundle.putString(AnalyticsConstant.PLATFORM, platform)
        analytics.logEvent(AnalyticsConstant.EVENT_CONFIG, bundle)
    }

    fun reportDevideInfor(analytics: FirebaseAnalytics, localLanguage: String, time: Long) {
        val bundle = Bundle()
        bundle.putString(AnalyticsConstant.LOCAL_LANGUAGE, localLanguage)
        bundle.putLong(AnalyticsConstant.TIME_USING, time)
        analytics.logEvent(AnalyticsConstant.EVENT_CONFIG, bundle)
    }
}