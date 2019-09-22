package com.app.bookselling.view.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.LoginModule
import com.app.bookselling.presenter.LoginPresenter
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
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import javax.inject.Inject



class LoginActivity : BaseActivity(), View.OnClickListener, LoginView,
    GoogleApiClient.OnConnectionFailedListener {

    override fun onConnectionFailed(p0: ConnectionResult) {
    }


    private var btnSignInByFacebook: ImageButton? = null
    private var imgBtnSignInByGoogle: ImageButton? = null

    private var googleApiClient: GoogleApiClient? = null

    @Inject lateinit var mContext: Context

    @Inject
    lateinit var mActivity: LoginActivity

    @Inject
    lateinit var mPresenter: LoginPresenter

    @Inject
    lateinit var mCallbackManager: CallbackManager

    public override val layoutRes: Int
        get() = R.layout.activity_login


    companion object {
        const val EMAIL = "email"
        const val PUBLIC_PROFILE = "public_profile"
        const val FACEBOOK = "Facebook"
        const val GOOGLE = "Google"
        const val RC_SIGN_IN = 1
    }

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
                    Log.i(FACEBOOK, "onSuccess: " + loginResult?.accessToken)
                    Log.i(FACEBOOK, "Token: " + loginResult?.accessToken?.token?.length)
                    val mGraphRequest: GraphRequest = GraphRequest.newMeRequest(
                        loginResult?.accessToken
                    ) { `object`, _ -> generateJsonFacebook(`object`)}


                    val parameters = Bundle()
                    parameters.putString("fields", "id, name, first_name, last_name, email")
                    mGraphRequest.parameters = parameters
                    mGraphRequest.executeAsync()
                }

                override fun onCancel() {
                    Log.d(FACEBOOK, "Login was canceled!")
                }

                override fun onError(error: FacebookException?) {
                    Log.d(FACEBOOK, "Login had error!")
                }
            })
    }

    private fun generateJsonFacebook(jsonObject: JSONObject?) {
        Log.i(FACEBOOK, jsonObject?.getString("id"))
        Log.i(FACEBOOK, jsonObject?.getString("name"))
        Log.i(FACEBOOK, jsonObject?.getString("first_name"))
        Log.i(FACEBOOK, jsonObject?.getString("last_name"))
        Log.i(FACEBOOK, jsonObject?.getString("email"))
        Log.i(FACEBOOK, "https://graph.facebook.com/" + jsonObject?.getString("id") + "/picture?type=normal")
        var jsonParser : JsonParser = JsonParser()
        mPresenter.loginSocial(jsonParser.parse(jsonObject.toString()) as JsonObject)
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
            val imageUrl: Uri? = account?.photoUrl
            val name: String? = account?.displayName
            val email: String? = account?.email

            Log.i(GOOGLE, "User_name: $name")
            Log.i(GOOGLE, "User_email: $email")
            Log.i(GOOGLE, "User_image_url: $imageUrl")
            var obj : JsonObject = JsonObject()
            obj.addProperty("id" , "")
            obj.addProperty("email", email)

            mPresenter.loginSocial(obj)
//            mPresenter.loginSocial(JSONObject("""{"id": "", "name": "$name", "first_name": "$name", "last_name": "$name", "email": "$email"}"""))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val googleSignInResult: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleResult(googleSignInResult)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun errorConnection() {
        Toast.makeText(mContext, "No connection", Toast.LENGTH_SHORT).show();
    }

    override fun showDialogProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeDialogProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
