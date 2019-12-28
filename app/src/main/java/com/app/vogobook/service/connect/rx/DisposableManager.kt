package com.app.vogobook.service.connect.rx

import com.app.vogobook.service.response.*

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DisposableManager @Inject
constructor() {

    fun login(observable: Observable<Response<UserResponse>>, _interface: IDisposableListener<UserResponse>) : Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<UserResponse>>() {
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<UserResponse>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }

                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }
            })

    }

    fun getCommonBooks(observable: Observable<Response<HomeCommonResponse>>, _interface: IDisposableListener<HomeCommonResponse>) : Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<HomeCommonResponse>>() {
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<HomeCommonResponse>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }

                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }
            })

    }

    fun logOut(observable: Observable<Response<PersonalResponse>>, _interface: IDisposableListener<PersonalResponse>) : Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<PersonalResponse>>() {
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<PersonalResponse>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }

                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }
            })
    }

    fun getOrders(observable: Observable<Response<OrdersResponse>>, _interface: IDisposableListener<OrdersResponse>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<OrdersResponse>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<OrdersResponse>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }

                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }

            })
    }

    fun postReview(observable: Observable<Response<Error>>, _interface: IDisposableListener<Error>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<Error>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<Error>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }
                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }

            })
    }

    fun getReviews(observable: Observable<Response<ReviewsResponse>>, _interface: IDisposableListener<ReviewsResponse>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<ReviewsResponse>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<ReviewsResponse>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }
                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }

            })
    }

    fun submitOrder(observable: Observable<Response<Error>>, _interface: IDisposableListener<Error>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<Error>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<Error>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }
                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }

            })
    }

    fun cancelOrder(observable: Observable<Response<Error>>, _interface: IDisposableListener<Error>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<Error>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<Error>) {
                    if (value.isSuccessful) {
                        _interface.onHandleData(value.body())
                    } else {
                        _interface.onRequestWrongData(value.code())
                    }
                }
                override fun onError(e: Throwable) {
                    _interface.onApiFailure(e)
                }

            })
    }
}