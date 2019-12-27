package com.app.vogobook.di.component

import com.app.vogobook.di.module.BookDetailModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.book.BookDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [BookDetailModule::class])
interface BookDetailComponent {
    fun inject(bookDetailFragment: BookDetailFragment) : BookDetailFragment
}