package com.app.vogobook.di.component

import com.app.vogobook.di.module.ConfirmOrderModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.ConfirmOrderFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ConfirmOrderModule::class])
interface ConfirmOrderComponent {
    fun inject(confirmOrderFragment: ConfirmOrderFragment) : ConfirmOrderFragment
}