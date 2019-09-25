package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.HomeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment): HomeFragment
}