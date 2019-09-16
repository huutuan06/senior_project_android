package com.app.bookselling.view.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.LoginModule
import com.app.bookselling.view.ui.callback.LoginView
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener, LoginView {

    private var btnSignInByFacebook: LoginButton? = null
    private var imgBtnSignInByGoogle: ImageButton? = null

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
        btnSignInByFacebook = findViewById(R.id.btnSignInByFacebook)
        imgBtnSignInByGoogle = findViewById(R.id.btnSignInByGoogle)
    }

    override fun initAttributes() {
        btnSignInByFacebook!!.setOnClickListener(this)
        imgBtnSignInByGoogle!!.setOnClickListener(this)
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
        mCallbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf(EMAIL, PUBLIC_PROFILE)
        )
        LoginManager.getInstance().registerCallback(Application.mCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("LoginActivity", "Facebook Token: " + loginResult.accessToken.token)

            }

            override fun onCancel() {
                if (AccessToken.getCurrentAccessToken() != null) {
                    GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permission/", null, HttpMethod.DELETE, GraphRequest.Callback {
                        AccessToken.setCurrentAccessToken(null)
                        LoginManager.getInstance().logOut()
                    }).executeAsync()
                }
            }

            override fun onError(exception: FacebookException) {
                Log.d("LoginActivity", "Facebook onError!")
            }
        })
    }

    companion object {
        const val EMAIL = "email"
        const val PUBLIC_PROFILE = "public_profile"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun loadUserProfile( newAccessToken: AccessToken) {
//        var request: GraphRequest = GraphRequest.newMeRequest(newAccessToken,  )
    }

}
