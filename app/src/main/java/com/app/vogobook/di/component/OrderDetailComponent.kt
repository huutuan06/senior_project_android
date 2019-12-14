package com.app.vogobook.di.component

import com.app.vogobook.di.module.OrderDetailModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.OrderDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [OrderDetailModule::class])
interface OrderDetailComponent {
    fun inject(orderDetailFragment: OrderDetailFragment): OrderDetailFragment
}