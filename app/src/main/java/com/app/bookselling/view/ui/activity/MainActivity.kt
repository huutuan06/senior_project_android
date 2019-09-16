package com.app.bookselling.view.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.service.model.Config
import com.app.bookselling.view.ui.callback.MainView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    var mContext: Context? = null
        @Inject set

    var mMainPresenter: MainPresenter? = null
        @Inject set

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        txtDemo!!.text = "Hello Ben!. Please tak a look and I will explain you about the structure!!"
    }

    override fun initViews() {

    }

    public override val layoutRes: Int
        get() = R.layout.activity_main

    override fun setDisposable(disposable: Disposable) {
        disposable.isDisposed
    }

    override fun loadAppConfigurationSuccess(config: Config) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAppConfigurationFailure(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
