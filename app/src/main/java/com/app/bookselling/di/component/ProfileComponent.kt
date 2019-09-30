package com.app.bookselling.di.component

import com.app.bookselling.di.module.ProfileModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.profile.ProfileFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(profileFragment: ProfileFragment): ProfileFragment
}