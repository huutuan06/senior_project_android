package com.android.project.app

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.android.project.di.component.AppComponent
import com.android.project.di.component.DaggerAppComponent
import com.android.project.di.module.AppModule
import com.android.project.di.module.ServiceModule
import com.facebook.CallbackManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Application : android.app.Application() {

    companion object {
        lateinit var instance: Application
        lateinit var mCallbackManager: CallbackManager
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        generateHashKey()
    }

    private fun generateHashKey() {
        try {
            @SuppressLint("PackageManagerGetSignatures")
            val info = packageManager.getPackageInfo(
                "com.game.millionaire",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

    fun getAppComponent(): AppComponent? {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this, this.applicationContext))
            .serviceModule(ServiceModule(this, this.applicationContext))
            .build()
    }

}
