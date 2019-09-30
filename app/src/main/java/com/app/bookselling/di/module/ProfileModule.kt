package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.utils.ItemManageOrders
import com.app.bookselling.view.adapter.ManageOrderAdapter
import com.app.bookselling.view.ui.fragment.profile.ProfileFragment
import dagger.Module
import dagger.Provides

@Module
class ProfileModule(private val fragment: ProfileFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): ProfileFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideManageOrderAdapter(context : Context) = ManageOrderAdapter(context, ArrayList<ItemManageOrders>())
}