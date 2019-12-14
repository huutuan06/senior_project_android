package com.app.vogobook.service.repository;

import com.app.vogobook.service.response.Error
import com.app.vogobook.service.response.HomeCommonResponse
import com.app.vogobook.service.response.OrdersResponse
import com.app.vogobook.service.response.PersonalResponse
import com.app.vogobook.service.response.UserResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * If you want to pass param @Part as GET
 * Example: url/1 => @Part
 * If you want to pass param as POST
 * Example url => @Query
 * If you want o pass @Header.
 *
 */
interface BookService {

    @POST("api/v1/mobile/user/login")
    fun login(@Body jsonObject: JsonObject) : Observable<Response<UserResponse>>

    @GET("api/v1/mobile/get/books")
    fun getCommonBooks() : Observable<Response<HomeCommonResponse>>

    @POST("api/v1/mobile/user/logout")
    fun logOut(@Header("Authorization") accessToken: String) : Observable<Response<PersonalResponse>>

    @POST("api/v1/mobile/user/profile")
    fun profile(@Header("Authorization") accessToken: String, @Body requestBody: RequestBody) : Call<UserResponse>

    @GET("api/v1/mobile/user/manageorders")
    fun getOrder(@Header("Authorization") accessToken: String) : Observable<Response<OrdersResponse>>

    @POST("api/v1/mobile/user/reviews")
    fun postReview(@Header("Authorization") accessToken: String, @Body jsonObject: JsonObject): Observable<Response<Error>>
}
