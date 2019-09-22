package com.app.bookselling.service.repository;

import com.app.bookselling.service.response.ConfigResponse
import com.app.bookselling.service.response.UserResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface BookService {

    @get:GET("/configure")
    val configuration: Observable<Response<ConfigResponse>>

    @POST("/v1/entertainment/game/facebook")
    fun user(@Body jsonObject: JsonObject) : Observable<Response<UserResponse>>

}
