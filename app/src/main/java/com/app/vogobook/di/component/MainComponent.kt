package com.app.vogobook.di.component

import com.app.vogobook.di.module.*
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.activity.SearchActivity
import com.app.vogobook.view.ui.fragment.*

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun plus(homeModule: HomeModule) : HomeComponent
    fun plus(personalModule: PersonalModule) : PersonalComponent
    fun plus(bookCollectionModule: BookCollectionModule) : BookCollectionComponent
    fun plus(bookDetailModule: BookDetailModule) : BookDetailComponent

    fun inject(activity: MainActivity): MainActivity
//    fun inject(bookDetailFragment: BookDetailFragment) : BookDetailFragment
    fun inject(cartFragment: CartFragment) : CartFragment
//    fun inject(bookCollectionFragment: BookCollectionFragment) : BookCollectionFragment
    fun inject(manageOrdersFragment: ManageOrdersFragment) : ManageOrdersFragment
    fun inject(writeReviewFragment: WriteReviewFragment) : WriteReviewFragment
    fun inject(accountFragment: AccountFragment) : AccountFragment
    fun inject(confirmOrderFragment: ConfirmOrderFragment) : ConfirmOrderFragment

    fun inject(searchActivity: SearchActivity) : SearchActivity
}