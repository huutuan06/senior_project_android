package com.app.vogobook.service.repository;

import com.app.vogobook.service.response.HomeCommonResponse
import com.app.vogobook.service.response.UserResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface BookService {

    @POST("api/v1/mobile/user/loginSocialNetwork")
    fun login(@Body jsonObject: JsonObject) : Observable<Response<UserResponse>>


    @GET("api/v1/mobile/get/books")
    fun getCommonBooks() : Observable<Response<HomeCommonResponse>>

}
