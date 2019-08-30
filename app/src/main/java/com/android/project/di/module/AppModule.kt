package com.android.project.di.module

import android.content.Context
import com.android.project.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

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
