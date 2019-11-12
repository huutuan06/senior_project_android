package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.PersonalModule
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.*
import com.app.bookselling.view.ui.fragment.profile.PersonalFragment

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun plus(homeModule: HomeModule) : HomeComponent
    fun plus(personalModule: PersonalModule) : PersonalComponent

    fun inject(activity: MainActivity): MainActivity
    fun inject(bookDetailFragment: BookDetailFragment) : BookDetailFragment
    fun inject(cartFragment: CartFragment) : CartFragment
    fun inject(bookCollectionFragment: BookCollectionFragment) : BookCollectionFragment
    fun inject(manageOrdersFragment: ManageOrdersFragment) : ManageOrdersFragment
    fun inject(writeReviewFragment: WriteReviewFragment) : WriteReviewFragment
}