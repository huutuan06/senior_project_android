package com.app.bookselling.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder


abstract class BaseFragment : Fragment() {

    private lateinit var mUnbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = provideYourFragmentView(inflater, container, savedInstanceState)
        mUnbinder = ButterKnife.bind(this, view)
        distributedDaggerComponents()
        initAttributes()
        return view
    }

    abstract fun provideYourFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View

    abstract fun distributedDaggerComponents()

    protected abstract fun initAttributes()

}