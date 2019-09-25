package com.app.bookselling.viewmodel;

import android.content.Context
import com.app.bookselling.R
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.service.connect.rx.DisposableManager
import com.app.bookselling.service.connect.rx.IDisposableListener
import com.app.bookselling.service.model.Config
import com.app.bookselling.service.repository.BookService
import com.app.bookselling.service.response.ConfigResponse
import com.app.bookselling.utils.SessionManager
import com.app.bookselling.utils.Utils

class ConfigVMImpl(context: Context, service: BookService, disposableManager: DisposableManager) :
    ConfigureVM {

    private var mPresenter: MainPresenter? = null
    private var mService: BookService? = null
    private var mContext: Context? = null
    private var mDisposableManager: DisposableManager? = null

    init {
        mService = service
        mContext = context
        mDisposableManager = disposableManager
    }

    override fun attachPresenter(presenter: MainPresenter) {
        mPresenter = presenter
    }

    override fun loadApplicationSettings() {
//        if (Utils.isInternetOn(mContext)) {
//            mPresenter!!.setDisposable(
//                mDisposableManager!!.configure(
//                    mService!!.configuration,
//                    object :
//                        IDisposableListener<ConfigResponse> {
//                        override fun onComplete() {
//
//                        }
//
//                        override fun onHandleData(response: ConfigResponse) {
//                            when (response.error!!.code) {
//                                0 -> {
//                                    val configuration = response.data
//                                    if (configuration != null) {
//                                        mPresenter!!.loadAppConfigurationSuccess(configuration)
//                                    }
//                                    if (configuration != null) {
//                                        saveInfoConfigureApp(configuration)
//                                    }
//                                }
//                                else -> mPresenter!!.loadAppConfigurationFailure(
//                                    mContext!!.getString(
//                                        R.string.no_internet_connection
//                                    )
//                                )
//                            }
//                        }
//
//                        override fun onRequestWrongData(code: Int) {
//
//                        }
//
//                        override fun onApiFailure(error: Throwable) {
//
//                        }
//                    })
//            )
//        } else {
//            mPresenter!!.loadAppConfigurationFailure(mContext!!.getString(R.string.no_internet_connection))
//        }
    }

    private fun saveInfoConfigureApp(configuration: Config) {
        SessionManager.getInstance(mContext).appVersion = configuration.appVersion!!
    }
}

