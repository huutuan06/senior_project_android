package com.android.project.viewmodel;

import android.content.Context
import com.android.project.R
import com.android.project.presenter.MainPresenter
import com.android.project.service.connect.rx.DisposableManager
import com.android.project.service.connect.rx.IDisposableListener
import com.android.project.service.model.Config
import com.android.project.service.repository.BookService
import com.android.project.service.response.ConfigResponse
import com.android.project.utils.SessionManager
import com.android.project.utils.Utils

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
        if (Utils.isInternetOn(mContext)) {
            mPresenter!!.setDisposable(
                mDisposableManager!!.configure(
                    mService!!.configuration,
                    object : IDisposableListener<ConfigResponse> {
                        override fun onComplete() {

                        }

                        override fun onHandleData(response: ConfigResponse) {
                            when (response.error!!.code) {
                                0 -> {
                                    val configuration = response.data
                                    if (configuration != null) {
                                        mPresenter!!.loadAppConfigurationSuccess(configuration)
                                    }
                                    if (configuration != null) {
                                        saveInfoConfigureApp(configuration)
                                    }
                                }
                                else -> mPresenter!!.loadAppConfigurationFailure(
                                    mContext!!.getString(
                                        R.string.no_internet_connection
                                    )
                                )
                            }
                        }

                        override fun onRequestWrongData(code: Int) {

                        }

                        override fun onApiFailure(error: Throwable) {

                        }
                    })
            )
        } else {
            mPresenter!!.loadAppConfigurationFailure(mContext!!.getString(R.string.no_internet_connection))
        }
    }

    private fun saveInfoConfigureApp(configuration: Config) {
        SessionManager.getInstance(mContext).appVersion = configuration.appVersion!!
    }
}

