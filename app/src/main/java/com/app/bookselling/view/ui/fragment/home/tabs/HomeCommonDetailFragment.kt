package com.app.bookselling.view.ui.fragment.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import javax.inject.Inject

class HomeCommonDetailFragment : BaseFragment() {

    @Inject
    lateinit var mActivity: MainActivity

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_common_detail, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)
        ).inject(this)
    }

    override fun initAttributes() {
        // TODO
    }

    @OnClick(R.id.backEvent)
    fun processClick(view : View) {
        when(view.id) {
            R.id.backEvent -> {
                mActivity.onBackPressed()
            }
        }
    }
}