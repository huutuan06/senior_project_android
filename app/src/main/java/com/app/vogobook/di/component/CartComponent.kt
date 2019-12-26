package com.app.vogobook.di.component

import com.app.vogobook.di.module.CartModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.CartFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [CartModule::class])
interface CartComponent {
    fun inject(cartFragment: CartFragment) : CartFragment
}