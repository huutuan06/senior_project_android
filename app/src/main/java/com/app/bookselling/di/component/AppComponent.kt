package com.app.bookselling.di.component

import com.app.bookselling.di.module.AppModule
import com.app.bookselling.di.module.LoginModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ServiceModule::class])
interface AppComponent {
    fun plus(module: MainModule): MainComponent
    fun plus(module: LoginModule): LoginComponent
}
