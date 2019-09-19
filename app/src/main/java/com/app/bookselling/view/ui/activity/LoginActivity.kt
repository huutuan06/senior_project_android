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
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener, LoginView,
    GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var btnSignInByFacebook: ImageButton? = null
    private var imgBtnSignInByGoogle: ImageButton? = null

    //private var signInOptions: GoogleSignInOptions? = null
    private var googleApiClient: GoogleApiClient? = null
    val RC_SIGN_IN = 404

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
        //Google
        imgBtnSignInByGoogle!!.setOnClickListener(this)

        configureGoogleSignIn()
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnSignInByFacebook -> {
                loginFacebookApp()
            }
            R.id.btnSignInByGoogle -> {
                loginGoogleApp()
            }
        }
    }

    private fun loginFacebookApp() {
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf(EMAIL, PUBLIC_PROFILE)
        )
        LoginManager.getInstance()
            .registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
//                    Log.d("LoginActivity", "onSuccess: " + loginResult?.accessToken)
//                    Log.d("LoginActivity", "Token: " + loginResult?.accessToken?.token?.length)
//                    val profile: Profile = Profile.getCurrentProfile()
//                    Log.d("LoginActivity", "First name: " + profile.firstName)
//                    Log.d("LoginActivity", "Last name: " + profile.lastName)
                    val mGraphRequest: GraphRequest = GraphRequest.newMeRequest(
                        loginResult?.accessToken
                    ) { `object`, response -> }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,link")
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

        //Facebook
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        //Google
        if (requestCode == RC_SIGN_IN) {
            val googleSignInResult: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleResult(googleSignInResult)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun configureGoogleSignIn() {
        val signInOptions: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build()
        googleApiClient =
            GoogleApiClient.Builder(applicationContext).enableAutoManage(mActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build()
    }

    private fun loginGoogleApp() {
        val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleResult(googleSignInResult: GoogleSignInResult) {
        if (googleSignInResult.isSuccess) {
            val account: GoogleSignInAccount? = googleSignInResult.signInAccount
            val name: String? = account?.displayName
            var email: String? = account?.email

            Log.d("LoginActivity", "Token Google: $name")
        }
    }

}
