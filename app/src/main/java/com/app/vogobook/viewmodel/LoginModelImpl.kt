package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.UserResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.activity.LoginActivity
import com.google.gson.JsonObject

class LoginModelImpl(
    private val context: Context,
    private val activity: LoginActivity,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private val sessionManager: SessionManager,
    private val roomUIManager: RoomUIManager
) :
    LoginModel {

    private var mPresenter: LoginPresenter? = null

    override fun attachPresenter(loginPresenter: LoginPresenter) {
        mPresenter = loginPresenter
    }

    /**
     * Login with social
     */
    override fun loginSocial(jsonObject: JsonObject) {
        if (Utils.isInternetOn(context)) {
            disposableManager.login(
                (service.login(jsonObject)),
                object : IDisposableListener<UserResponse> {
                    override fun onComplete() {
                    }

                    override fun onHandleData(response: UserResponse?) {
                        if (response!!.error!!.code == 0) {
                            activity.runOnUiThread {
                                ProcessDatabase().execute(response)
                            }
                        } else {
                            loadUserFromLocal()
                        }
                    }

                    override fun onRequestWrongData(code: Int) {
//                    mloginPresenter!!.loginFailure()
                    }

                    override fun onApiFailure(error: Throwable?) {
//                    mloginPresenter!!.loginFailure()
                    }
                })
        } else {
            mPresenter!!.loginFailure()
        }
    }

    override fun loadUserFromLocal() {
        roomUIManager.getUser(object : IRoomListener<User> {
            override fun showListData(users: List<User>) {
                mPresenter!!.loadUserSuccess(users[0])
            }

        })
    }


    @SuppressLint("StaticFieldLeak")
    inner class ProcessDatabase : AsyncTask<UserResponse, UserResponse, Boolean>() {

        override fun doInBackground(vararg response: UserResponse): Boolean {
            roomUIManager.saveUser(response[0].data!!.user)
            roomUIManager.getUser(object : IRoomListener<User> {
                override fun showListData(t: List<User>) {
                    sessionManager.token = response[0].data!!.token.toString()
                    onPostExecute(true)
                }
            })
            return false
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!) {
                activity.runOnUiThread { loadUserFromLocal() }
            }
        }

    }
}

