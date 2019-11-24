package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.app.Application
import com.app.vogobook.utils.SessionManager
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
}
