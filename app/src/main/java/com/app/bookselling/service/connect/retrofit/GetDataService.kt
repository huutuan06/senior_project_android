package com.app.bookselling.service.connect.retrofit


import com.app.bookselling.model.TokenResponse
import com.app.bookselling.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface GetDataService {
    @POST("/v1/entertainment/game/facebook")
    fun loginAuth(@Body user: User): Observable<TokenResponse>
}