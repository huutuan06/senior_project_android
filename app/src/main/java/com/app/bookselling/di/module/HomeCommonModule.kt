package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.utils.ItemCommon
import com.app.bookselling.view.adapter.CommonAdapter
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Module
import dagger.Provides

@Module
class HomeCommonModule (private val fragment : HomeCommonFragment) {

    @Provides
    @FragmentScope
    fun provideFragment() : HomeCommonFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideCommonAdapter(context: Context) =  CommonAdapter(context, ArrayList<ItemCommon>())
}