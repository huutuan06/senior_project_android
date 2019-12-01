package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.view.adapter.TopSellingAdapter
import com.app.vogobook.view.ui.fragment.home.tabs.HomeTopSellingFragment
import dagger.Module
import dagger.Provides

@Module
class HomeTopSellingModule (private val fragment : HomeTopSellingFragment) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeTopSellingFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideTopSellingAdapter(context: Context) =  TopSellingAdapter(context, ArrayList<Book>())
}