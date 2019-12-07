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
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.LoginModule
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.utils.VogoLoadingDialog
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
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.disposables.Disposable
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
    @Inject lateinit var mDialog: VogoLoadingDialog

    private var googleApiClient: GoogleApiClient? = null
    private val  TAG = LoginActivity::class.qualifiedName

    public override val layoutRes: Int
        get() = R.layout.activity_login

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
//        Utils.showLog(Utils.LogType.INFO, TAG, "" + jsonObject?.getString("id"))
//        Utils.showLog(Utils.LogType.INFO, TAG, "" + jsonObject?.getString("name"))
//        Utils.showLog(Utils.LogType.INFO, TAG, "" + jsonObject?.getString("first_name"))
//        Utils.showLog(Utils.LogType.INFO, TAG, "" + jsonObject?.getString("last_name"))
//        Utils.showLog(Utils.LogType.INFO, TAG, "https://graph.facebook.com/" + jsonObject?.getString("id") + "/picture?type=normal")

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
        intent.putExtra(Constants.USER, user)
        startActivity(intent)
        finish()
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessageDialog(errorTitle: String?, errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDisposable(disposable: Disposable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
