package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.adapter.ManageOrdersAdapter
import com.app.vogobook.view.ui.fragment.ManageOrdersFragment
import dagger.Module
import dagger.Provides

@Module
class ManageOrdersModule(private val fragment: ManageOrdersFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): ManageOrdersFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideManageOrdersAdapter(context : Context) = ManageOrdersAdapter(context, ArrayList())

}