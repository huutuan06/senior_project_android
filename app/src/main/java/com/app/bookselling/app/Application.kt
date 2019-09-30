package com.app.bookselling.app

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import com.app.bookselling.di.component.DaggerAppComponent
import com.app.bookselling.di.component.AppComponent
import com.app.bookselling.di.module.AppModule
import com.app.bookselling.di.module.ServiceModule
import com.facebook.CallbackManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class Application : android.app.Application() {

    companion object {
        lateinit var instance: Application
        lateinit var mCallbackManager: CallbackManager
    }

    private lateinit var mRootFragment : Fragment

    override fun onCreate() {
        super.onCreate()
        instance = this
        generateHashKey()
    }

    private fun generateHashKey() {
        try {
            @SuppressLint("PackageManagerGetSignatures")
            val info = packageManager.getPackageInfo(
                "com.app.bookselling",
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

    fun setCurrentFragment(fragment: Fragment) {
        mRootFragment = fragment
    }

    fun getCurrentFragment() = mRootFragment
}
