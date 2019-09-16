package com.app.bookselling.service.connect.rx

import com.app.bookselling.service.response.ConfigResponse

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DisposableManager @Inject
constructor() {

    fun configure(observable: Observable<Response<ConfigResponse>>, _interface: IDisposableListener<ConfigResponse>): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<ConfigResponse>>() {
                override fun onComplete() {
                    _interface.onComplete()
                }

                override fun onNext(value: Response<ConfigResponse>) {
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