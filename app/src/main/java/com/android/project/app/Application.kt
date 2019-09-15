package com.android.project.app

import com.android.project.di.component.AppComponent
import com.android.project.di.component.DaggerAppComponent
import com.android.project.di.module.AppModule
import com.android.project.di.module.ServiceModule

class Application : android.app.Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getAppComponent(): AppComponent? {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this, this.applicationContext))
            .serviceModule(ServiceModule(this, this.applicationContext))
            .build()
    }

}
