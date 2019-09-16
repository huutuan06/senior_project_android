package com.app.bookselling.service.repository;

import com.app.bookselling.service.response.ConfigResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET


interface BookService {

    @get:GET("/configure")
    val configuration: Observable<Response<ConfigResponse>>

}
