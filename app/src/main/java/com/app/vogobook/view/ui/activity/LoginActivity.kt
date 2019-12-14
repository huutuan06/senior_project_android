package com.app.vogobook.view.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.analytics.VogoAnalytics
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.LoginModule
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.callback.LoginView
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by ben on xx/xxx/2019.
 */
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
    @Inject lateinit var mSessionManager: SessionManager
    @Inject lateinit var mPgDialog: VogoLoadingDialog
    @Inject lateinit var mFirebaseAnalytics: FirebaseAnalytics
    @Inject lateinit var mVogoAnalytics: VogoAnalytics

    private var googleApiClient: GoogleApiClient? = null
    private var mDisposable: Disposable? = null
    private val  TAG = LoginActivity::class.qualifiedName

    public override val layoutRes: Int
        get() = R.layout.activity_login

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(LoginModule(this, this)).inject(this)
        mVogoAnalytics.reportScreen(mFirebaseAnalytics, this, Utils.replaceAvitityByScreen(SplashActivity::class.java.simpleName))
    }

    override fun initAttributes() {
        Application.mCallbackManager = mCallbackManager
        configureGoogleSignIn()
    }

    override fun onResume() {
        updateProgressDialog(false);
        super.onResume()
    }

    @OnClick(R.id.imvSignInByGoogle, R.id.imvSignInByFacebook)
    fun processEventClick(v : View) {
        when(v.id) {
            R.id.imvSignInByFacebook -> {
                mVogoAnalytics.reportLoginSocialNetwork(mFirebaseAnalytics, Constants.FACEBOOK)
                loginFacebookApp()
            }
            R.id.imvSignInByGoogle -> {
                mVogoAnalytics.reportLoginSocialNetwork(mFirebaseAnalytics, Constants.GOOGLE)
                loginGoogleApp()
            }
        }
    }

    private fun loginFacebookApp() {
        updateProgressDialog(true);
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf(Constants.EMAIL, Constants.PUBLIC_PROFILE)
        )
        LoginManager.getInstance()
            .registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val mGraphRequest: GraphRequest = GraphRequest.newMeRequest(
                        loginResult?.accessToken
                    ) { `object`, _ -> generateJsonFacebook(`object`)}

                    val parameters = Bundle()
                    parameters.putString(Constants.FIELDS, Constants.FACEBOOK_PARAMS)
                    mGraphRequest.parameters = parameters
                    mGraphRequest.executeAsync()
                }

                override fun onCancel() {
                    Utils.showLog(Utils.LogType.DEBUG, TAG,"Login was canceled!" )
                }

                override fun onError(error: FacebookException?) {
                    Utils.showLog(Utils.LogType.DEBUG, TAG,"Login had error!" )
                }
            })
    }

    private fun generateJsonFacebook(jsonObject: JSONObject?) {
        val jsonFacebookServer = JSONObject()
        jsonFacebookServer.put("name", jsonObject?.getString("name"))
        jsonFacebookServer.put("email", jsonObject?.getString("email"))
        jsonFacebookServer.put("image", "https://graph.facebook.com/" + jsonObject?.getString("id") + "/picture?type=normal")
        jsonFacebookServer.put("platform", Constants.FACEBOOK)

        val jsonParser  = JsonParser()
        mPresenter.loginSocial(jsonParser.parse(jsonFacebookServer.toString()) as JsonObject)
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
        updateProgressDialog(true);
        val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN)
    }

    private fun handleResult(googleSignInResult: GoogleSignInResult) {
        if (googleSignInResult.isSuccess) {
            val account: GoogleSignInAccount? = googleSignInResult.signInAccount
            val imageUrl: Uri? = account?.photoUrl
            val name: String? = account?.displayName
            val email: String? = account?.email

            Utils.showLog(Utils.LogType.INFO, TAG, "User_name: $name")
            Utils.showLog(Utils.LogType.INFO, TAG, "User_email: $email")
            Utils.showLog(Utils.LogType.INFO, TAG, "User_image_url: $imageUrl")

            val obj = JsonObject()
            obj.addProperty("name" , name)
            obj.addProperty("email", email)
            obj.addProperty("image", imageUrl.toString())
            obj.addProperty("platform", Constants.GOOGLE)
            mPresenter.loginSocial(obj)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RC_SIGN_IN) {
            val googleSignInResult: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleResult(googleSignInResult)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun loadUserSuccess(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        if (isShowProgressDialog) {
            if (!mPgDialog.isShowing) {
                mPgDialog.show()
            }
        } else {
            if (!mActivity.isDestroyed && mPgDialog.isShowing)
                mPgDialog.dismiss()
        }
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        // TODO
    }

    override fun setDisposable(disposable: Disposable) {
        mDisposable = disposable
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Utils.showLog(Utils.LogType.DEBUG, TAG,p0.errorMessage.toString())
    }

    override fun onDestroy() {
        if (mDisposable!!.isDisposed) mDisposable!!.dispose()
        super.onDestroy()
    }
}
