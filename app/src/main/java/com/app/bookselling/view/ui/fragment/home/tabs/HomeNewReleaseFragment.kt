package com.app.bookselling.view.ui.fragment.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.bookselling.R
import com.app.bookselling.view.ui.fragment.BaseFragment

class HomeNewReleaseFragment : BaseFragment() {

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_new_release, container, false)
    }

    override fun distributedDaggerComponents() {
        // TODO
    }

    override fun initAttributes() {
        // TODO
    }
}