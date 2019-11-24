package com.app.vogobook.di.component

import com.app.vogobook.di.module.AppModule
import com.app.vogobook.di.module.LoginModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface AppComponent {
    fun plus(module: MainModule): MainComponent
    fun plus(module: LoginModule): LoginComponent
}
