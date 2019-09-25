package com.app.bookselling.di.module

import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.HomeFragment
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val fragment: HomeFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): HomeFragment {
        return fragment
    }
}