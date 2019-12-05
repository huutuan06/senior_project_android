package com.app.vogobook.di.component

import com.app.vogobook.di.module.BookCollectionModule
import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.view.ui.fragment.BookCollectionFragment
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [BookCollectionModule::class])
interface BookCollectionComponent {
    fun inject(bookCollectionFragment: BookCollectionFragment) : BookCollectionFragment
}