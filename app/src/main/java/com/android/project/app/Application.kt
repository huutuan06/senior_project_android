package com.android.project.app

import com.android.project.di.component.AppComponent
import com.android.project.di.component.DaggerAppComponent
import com.android.project.di.module.AppModule
import com.android.project.di.module.ServiceModule

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

class Application : android.app.Application() {

    private var appComponent: AppComponent? = null

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        instance = this
        appComponent!!.plus(this)
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this, this.applicationContext))
            .serviceModule(ServiceModule(this, this.applicationContext))
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

}
