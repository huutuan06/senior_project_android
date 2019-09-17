package com.app.bookselling.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.LoginModule
import com.app.bookselling.view.ui.callback.LoginView
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener, LoginView {

    private var btnSignInByFacebook: ImageButton? = null
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
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf(EMAIL, PUBLIC_PROFILE)
        )
        LoginManager.getInstance().registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult?) {
                Log.d("LoginActivity", "onSuccess: "+loginResult?.accessToken)
                Log.d("LoginActivity", "Token: "+loginResult?.accessToken?.token)
//                val profile: Profile = Profile.getCurrentProfile()
//                Log.d("LoginActivity", "First name: "+ profile.firstName)
//                Log.d("LoginActivity", "Last name: "+ profile.lastName)
                val mGraphRequest: GraphRequest = GraphRequest.newMeRequest(loginResult?.accessToken
                ) { `object`, response -> }
                val parameters = Bundle()
                parameters.putString("fields","id,name,link")
                mGraphRequest.parameters
                mGraphRequest.executeAsync()
                startActivity(Intent(applicationContext, LogoutActivity::class.java))
            }

            override fun onCancel() {
                Log.d("LoginActivity", "Login was canceled!")
            }

            override fun onError(error: FacebookException?) {
                Log.d("LoginActivity", "Login had error!")
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
}
