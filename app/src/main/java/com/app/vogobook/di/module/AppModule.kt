package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.analytics.VogoAnalytics
import com.app.vogobook.app.Application
import com.app.vogobook.utils.SessionManager
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: Application, private val mContext: Context) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    internal fun provideSessionManager() : SessionManager {
        return SessionManager(mContext)
    }

    @Provides
    @Singleton
    internal fun provideFirebaseAnalytics(context: Context) : FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideVogoAnalytics() : VogoAnalytics = VogoAnalytics()
}
