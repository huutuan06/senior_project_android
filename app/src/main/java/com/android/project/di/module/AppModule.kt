package com.android.project.di.module

import android.content.Context
import com.android.project.app.Application
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
}
