package com.android.project.di.module

import com.android.project.di.scope.ActivityScope
import com.android.project.view.ui.callback.MainView
import com.android.project.view.ui.activity.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
class MainModule(private val activity: MainActivity, private val view: MainView) {


    @Provides
    @ActivityScope
    internal fun provideActivity(): MainActivity {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideView(): MainView {
        return view
    }

}