package com.app.vogobook.di.component

import com.app.vogobook.di.module.PersonalModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PersonalModule::class])
interface PersonalComponent {
    fun inject(personalFragment: PersonalFragment): PersonalFragment
}