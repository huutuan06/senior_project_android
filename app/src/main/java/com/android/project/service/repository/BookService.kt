package com.android.project.service.repository;

import com.android.project.service.response.ConfigResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET


interface BookService {

    @get:GET("/configure")
    val configuration: Observable<Response<ConfigResponse>>

}
