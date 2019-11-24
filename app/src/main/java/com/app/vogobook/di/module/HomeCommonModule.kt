package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.utils.ItemCommon
import com.app.vogobook.view.adapter.CommonAdapter
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Module
import dagger.Provides

@Module
class HomeCommonModule (private val fragment : HomeCommonFragment) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeCommonFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideCommonAdapter(context: Context) =  CommonAdapter(context, ArrayList<ItemCommon>())
}