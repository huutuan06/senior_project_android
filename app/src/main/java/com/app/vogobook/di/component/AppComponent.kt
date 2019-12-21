package com.app.vogobook.di.component

import com.app.vogobook.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class, RoomModule::class])
interface AppComponent {
    fun plus(module: MainModule): MainComponent
    fun plus(module: LoginModule): LoginComponent
    fun plus(module: SplashModule): SplashComponent
    fun plus(module: SearchModule): SearchComponent
}
