package com.app.bookselling.viewmodel

import android.content.Context
import com.app.bookselling.presenter.LoginPresenter
import com.app.bookselling.service.connect.rx.DisposableManager
import com.app.bookselling.service.connect.rx.IDisposableListener
import com.app.bookselling.service.model.User
import com.app.bookselling.service.repository.BookService
import com.app.bookselling.service.response.Error
import com.app.bookselling.service.response.UserData
import com.app.bookselling.service.response.UserResponse
import com.app.bookselling.utils.Utils
import com.google.gson.JsonObject

class UserViewModelImpl(private var context: Context, private var service: BookService, private var disposableManager: DisposableManager) :
    UserViewModel {

    private var mloginPresenter: LoginPresenter? = null

    override fun attachPresenter(loginPresenter: LoginPresenter) {
        mloginPresenter = loginPresenter
    }

    override fun loginSocial(jsonObject: JsonObject) {
        if (Utils.isInternetOn(context)) {

            var dummyJson = JsonObject()
            dummyJson.addProperty("email", "abc11@gmail.com")
            dummyJson.addProperty("name", "abc11")
            dummyJson.addProperty("password", "abfdjhsd21231HN@@")
            dummyJson.addProperty("picture", "abc11")
            dummyJson.addProperty("deviceID", "abc11")

            disposableManager.user((service.user(dummyJson)), object : IDisposableListener<UserResponse> {
                override fun onComplete() {
                }

                override fun onHandleData(response: UserResponse?) {
//                    val configuration = response!!.data
                    var error: Error? = response!!.error
                    var data: UserData? = response.data
                    var user: User? = response.data!!.user
                }

                override fun onRequestWrongData(code: Int) {
                    mloginPresenter!!.loginFailure()
                }

                override fun onApiFailure(error: Throwable?) {
                    mloginPresenter!!.loginFailure()
                }
            })
        } else {
            mloginPresenter!!.errorConnection()
        }
    }
}

