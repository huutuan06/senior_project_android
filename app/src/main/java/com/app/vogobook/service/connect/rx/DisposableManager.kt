package com.app.vogobook.service.connect.rx

import com.app.vogobook.service.response.BookCollectionResponse
import com.app.vogobook.service.response.HomeCommonResponse
import com.app.vogobook.service.response.UserResponse

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

    fun getBookCollection(observable: Observable<Response<BookCollectionResponse>>, _interface: IDisposableListener<BookCollectionResponse>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<BookCollectionResponse>>(){
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<BookCollectionResponse>) {
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