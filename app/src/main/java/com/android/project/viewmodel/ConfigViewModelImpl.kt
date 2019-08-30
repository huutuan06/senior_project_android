package com.android.project.viewmodel;

class ConfigViewModelImpl(private val mContext: Context, private val mApiService: ApiService) : ConfigViewModel {

private var mSplashPresenter: SplashPresenter? = null
private val mDisposableManager: DisposableManager = DisposableManager()

        override fun attachPresenter(splashPresenter: SplashPresenterImpl) {
        mSplashPresenter = splashPresenter
        }

        override fun loadApplicationSettings() {
        if (Utils.isInternetOn(mContext)) {
        mSplashPresenter!!.setDisposable(mDisposableManager.configure(mApiService.configuration, object : IDisposableListener<ConfigurationResponse> {
        override fun onComplete() {

        }

        override fun onHandleData(response: ConfigurationResponse) {
        when (response.error.code) {
        0 -> {
        val configuration = response.data
        mSplashPresenter!!.loadAppConfigurationSuccess(configuration)
        saveInfoConfigureApp(configuration)
        }
        else -> mSplashPresenter!!.loadAppConfigurationFailure(mContext.getString(R.string.no_internet_connection))
        }
        }

        override fun onRequestWrongData(code: Int) {

        }

        override fun onApiFailure(error: Throwable) {

        }
        }))
        } else {
        mSplashPresenter!!.loadAppConfigurationFailure(mContext.getString(R.string.no_internet_connection))
        }
        }

private fun saveInfoConfigureApp(configuration: Configuration) {
        SessionManager.getInstance(mContext).minimumVersion = configuration.minimumVersion
        SessionManager.getInstance(mContext).latestVersion = configuration.newestVersion
        }
        }
