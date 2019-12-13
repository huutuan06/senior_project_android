package com.app.vogobook.di.component

import com.app.vogobook.di.module.ManageOrdersModule
import com.app.vogobook.di.module.PersonalModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.ManageOrdersFragment
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ManageOrdersModule::class])
interface ManageOrdersComponent {
    fun inject(manageOrdersFragment: ManageOrdersFragment): ManageOrdersFragment
}