package com.android.project.di.component

import com.android.project.di.module.AppModule
import com.android.project.di.module.LoginModule
import com.android.project.di.module.MainModule
import com.android.project.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface AppComponent {
    fun plus(module: MainModule): MainComponent
    fun plus(module: LoginModule): LoginComponent
}
