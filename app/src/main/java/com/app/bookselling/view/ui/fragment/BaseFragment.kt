package com.app.bookselling.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
        distributedDaggerComponents()
        initViews()
        initAttributes()
    }

    abstract fun distributedDaggerComponents()

    protected abstract fun initAttributes()

    protected abstract fun initViews()
}