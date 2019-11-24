package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.UserResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.google.gson.JsonObject

class UserViewModelImpl(
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private val sessionManager: SessionManager
) :
    UserViewModel {

    private var mloginPresenter: LoginPresenter? = null

    override fun attachPresenter(loginPresenter: LoginPresenter) {
        mloginPresenter = loginPresenter
    }

    /**
     * Login with social
     */
    override fun loginSocial(jsonObject: JsonObject) {
        if (Utils.isInternetOn(context)) {
            disposableManager.login((service.login(jsonObject)), object : IDisposableListener<UserResponse> {
                override fun onComplete() {
                }
                override fun onHandleData(response: UserResponse?) {
                    /*val error: Error? = response!!.error
                    val data: UserData? = response.data
                    val user: User? = response.data!!.user

                    val pref: SharedPreferences = context.getSharedPreferences("LoginPref", 0)
                    val editor: SharedPreferences.Editor = pref.edit()
                    editor.putString("Token",data?.token).apply()

                    mloginPresenter!!.loadUser(user?.fullName, user?.email)*/
                }

                override fun onRequestWrongData(code: Int) {
//                    mloginPresenter!!.loginFailure()
                }

                override fun onApiFailure(error: Throwable?) {
//                    mloginPresenter!!.loginFailure()
                }
            })
        } else {
            mloginPresenter!!.loginFailure()
        }
    }
}

