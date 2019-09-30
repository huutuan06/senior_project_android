package com.app.bookselling.view.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutRes: Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(layoutRes)
        distributedDaggerComponents()
        ButterKnife.bind(this)
        initAttributes()
    }

    abstract fun distributedDaggerComponents()

    protected abstract fun initAttributes()

    override fun onDestroy() {
        ButterKnife.bind(this).unbind()
        super.onDestroy()
    }
}
