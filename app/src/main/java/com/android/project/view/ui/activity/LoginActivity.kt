package com.android.project.view.ui.activity

import android.content.Context
import android.view.View
import android.widget.Button
import com.android.project.R
import com.android.project.app.Application
import com.android.project.di.module.LoginModule
import com.android.project.view.ui.callback.LoginView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener, LoginView {

    private var btnSignInByFacebook: Button? = null
    private var btnSignInByGoogle: Button? = null

    @Inject
    lateinit var mActivity: LoginActivity

    @Inject
    lateinit var mCallbackManager: CallbackManager

    public override val layoutRes: Int
        get() = R.layout.activity_login

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(LoginModule(this, this)).inject(this)
    }

    override fun initViews() {
        Application.mCallbackManager = mCallbackManager
        btnSignInByFacebook = findViewById(R.id.btnSignInByGoogle)
        btnSignInByGoogle = findViewById(R.id.btnSignInByGoogle)
    }

    override fun initAttributes() {
        btnSignInByFacebook!!.setOnClickListener(this)
        btnSignInByGoogle!!.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnSignInByFacebook -> {
                loginFacebookApp()
            }
            R.id.btnSignInByGoogle -> {

            }
        }
    }

    private fun loginFacebookApp() {
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf(EMAIL, PUBLIC_PROFILE)
        )
        LoginManager.getInstance().registerCallback(Application.mCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

            }

            override fun onCancel() {}

            override fun onError(exception: FacebookException) {}
        })
    }

    companion object {
        const val EMAIL = "email"
        const val PUBLIC_PROFILE = "public_profile"
    }

}
