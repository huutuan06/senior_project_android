package com.app.vogobook.service.repository;

import com.app.vogobook.service.model.OrdersData
import com.app.vogobook.service.response.*
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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

    @GET("api/v1/mobile/user/reviews/{id}")
    fun getReviews(@Path("id") bookId: Int?): Observable<Response<ReviewsResponse>>

    @POST("api/v1/mobile/order/submit")
    fun submitOrder(@Header("Authorization") accessToken: String, @Body ordersData: OrdersData) : Observable<Response<Error>>

    @POST("api/v1/mobile/cancelorder")
    fun cancelOrder(@Header("Authorization") accessToken: String, @Body orderId: Int?): Observable<Response<Error>>
}
