package com.app.vogobook.di.component

import com.app.vogobook.di.module.*
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.fragment.*

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun plus(homeModule: HomeModule) : HomeComponent
    fun plus(personalModule: PersonalModule) : PersonalComponent
    fun plus(bookCollectionModule: BookCollectionModule) : BookCollectionComponent
    fun plus(bookDetailModule: BookDetailModule) : BookDetailComponent
    fun plus(accountModule: AccountModule) : AccountComponent
    fun plus(manageOrdersModule: ManageOrdersModule) : ManageOrdersComponent
    fun plus(orderDetailModule: OrderDetailModule) : OrderDetailComponent
    fun plus(writeReviewModule: WriteReviewModule) : WriteReviewComponent
    fun plus(confirmOrderModule: ConfirmOrderModule) : ConfirmOrderComponent
    fun plus(cartModule: CartModule) : CartComponent
    fun inject(activity: MainActivity): MainActivity
}