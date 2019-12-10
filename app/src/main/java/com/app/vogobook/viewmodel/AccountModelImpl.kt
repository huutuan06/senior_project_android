package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.AccountPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.UserResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class AccountModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) : AccountModel {

    private var mPresenter: AccountPresenter? = null

    override fun attachPresenter(presenter: AccountPresenter) {
        mPresenter = presenter
    }

    override fun editAccount(jsonObject: JsonObject) {
        val file = File(jsonObject.get("image").toString())
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("name", jsonObject.get("name").toString())
        builder.addFormDataPart("date_of_birth",  jsonObject.get("date_of_birth").toString())
        builder.addFormDataPart("address",  jsonObject.get("address").toString())
        builder.addFormDataPart("gender",  jsonObject.get("gender").toString())
        builder.addFormDataPart(
            "file",
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )
        val requestBody = builder.build()
        val call = service.profile(mSessionManager.token, requestBody) as Call<UserResponse>
        call.enqueue(object  : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
               if (response.isSuccessful) {

               } else {
                   // TODO
               }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
}