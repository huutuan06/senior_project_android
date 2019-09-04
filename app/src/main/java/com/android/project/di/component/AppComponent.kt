package com.android.project.di.component

import com.android.project.app.Application
import com.android.project.di.module.AppModule
import com.android.project.di.module.MainModule
import com.android.project.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface AppComponent {
    operator fun plus(application: Application)
    operator fun plus(module: MainModule): MainComponent
}
