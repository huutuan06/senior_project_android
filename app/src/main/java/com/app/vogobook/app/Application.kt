package com.app.vogobook.app

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.app.vogobook.di.component.AppComponent
import com.app.vogobook.di.component.DaggerAppComponent
import com.app.vogobook.di.module.AppModule
import com.app.vogobook.di.module.RoomModule
import com.app.vogobook.di.module.ServiceModule
import com.facebook.CallbackManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class Application : android.app.Application() {

    companion object {
        lateinit var instance: Application
        lateinit var mCallbackManager: CallbackManager
    }

    private lateinit var mRootFragment : Fragment
    private lateinit var mView : View

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getAppComponent(): AppComponent? {
        return DaggerAppComponent.builder() 
            .appModule(AppModule(this, this.applicationContext))
            .serviceModule(ServiceModule())
            .roomModule(RoomModule(this))
            .build()
    }

    fun setCurrentFragment(fragment: Fragment) {
        mRootFragment = fragment
    }

    fun getCurrentFragment() = mRootFragment

    fun getView() = mView

    fun setView(view: View) {
        mView = view
    }

    private fun generateHashKey() {
        try {
            @SuppressLint("PackageManagerGetSignatures")
            val info = packageManager.getPackageInfo(
                "com.app.vogobook",
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
}
