package com.app.bookselling.di.component

import com.app.bookselling.di.module.PersonalModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.profile.PersonalFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PersonalModule::class])
interface PersonalComponent {
    fun inject(personalFragment: PersonalFragment): PersonalFragment
}