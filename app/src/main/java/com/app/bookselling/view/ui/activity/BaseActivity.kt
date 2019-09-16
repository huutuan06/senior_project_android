package com.app.bookselling.view.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.bookselling.view.ui.callback.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected abstract val layoutRes: Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        distributedDaggerComponents()
        setContentView(layoutRes)
        initViews()
        initAttributes()
    }

    abstract fun distributedDaggerComponents()

    protected abstract fun initAttributes()

    protected abstract fun initViews()
}
