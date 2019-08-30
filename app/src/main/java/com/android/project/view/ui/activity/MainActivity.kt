package com.android.project.view.ui.activity

import com.android.project.R
import com.android.project.app.Application
import com.android.project.di.module.MainModule
import com.android.project.view.ui.callback.MainView

class MainActivity : BaseActivity(), MainView {

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {

    }

    override fun initViews() {

    }

    public override val layoutRes: Int
        get() = R.layout.activity_main

}
