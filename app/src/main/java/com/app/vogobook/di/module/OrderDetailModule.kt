package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.adapter.OrderDetailAdapter
import com.app.vogobook.view.ui.fragment.OrderDetailFragment
import dagger.Module
import dagger.Provides

@Module
class OrderDetailModule(private val fragment: OrderDetailFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): OrderDetailFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideOrderDetailAdapter(context : Context) = OrderDetailAdapter(context, ArrayList())

}