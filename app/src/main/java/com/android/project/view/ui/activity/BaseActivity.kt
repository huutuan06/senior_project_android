package com.android.project.view.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.android.project.view.ui.callback.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var mUnbinder: Unbinder? = null

    protected abstract val layoutRes: Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        distributedDaggerComponents()
        setContentView(layoutRes)
        mUnbinder = ButterKnife.bind(this)
        ButterKnife.bind(this)
        initAttributes()
        initViews()
    }

    abstract fun distributedDaggerComponents()

    protected abstract fun initAttributes()

    protected abstract fun initViews()

    override fun onDestroy() {
        if (mUnbinder != null) {
            mUnbinder!!.unbind()
        }
        super.onDestroy()
    }
}
