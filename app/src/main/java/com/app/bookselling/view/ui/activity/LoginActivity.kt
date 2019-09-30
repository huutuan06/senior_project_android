package com.app.bookselling.view.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
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

class LoginActivity : BaseActivity(), LoginView,
    GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.imvSignInByFacebook)
    @JvmField var imvSignInByFacebook: ImageButton? = null

    @BindView(R.id.imvSignInByGoogle)
    @JvmField var imvSignInByGoogle: ImageButton? = null

    @Inject lateinit var mContext: Context
    @Inject lateinit var mActivity: LoginActivity
    @Inject lateinit var mPresenter: LoginPresenter
    @Inject lateinit var mCallbackManager: CallbackManager

    private var googleApiClient: GoogleApiClient? = null

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

    override fun initAttributes() {
        Application.mCallbackManager = mCallbackManager
        configureGoogleSignIn()
    }

    @OnClick(R.id.imvSignInByGoogle, R.id.imvSignInByFacebook)
    fun processEventClick(v : View) {
        when(v.id) {
            R.id.imvSignInByFacebook -> {
                loginFacebookApp()
            }
            R.id.imvSignInByGoogle -> {
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
        val jsonParser : JsonParser = JsonParser()
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
            val obj = JsonObject()
            obj.addProperty("id" , "")
            obj.addProperty("email", email)
            mPresenter.loginSocial(obj)
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
        Toast.makeText(mContext, "No connection", Toast.LENGTH_SHORT).show()
    }

    override fun showDialogProgress() {
        // TODO
    }

    override fun closeDialogProgress() {
        // TODO
    }

    override fun loadUser(fullName: String?, email: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Name", fullName)
        intent.putExtra("Email", email)
        startActivity(intent)
        finish()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        // TODO
    }
}
