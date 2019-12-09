package com.app.vogobook.di.component

import com.app.vogobook.di.module.AccountModule
import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.view.ui.fragment.AccountFragment
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [AccountModule::class])
interface AccountComponent {
    fun inject(accountFragment: AccountFragment) : AccountFragment
}