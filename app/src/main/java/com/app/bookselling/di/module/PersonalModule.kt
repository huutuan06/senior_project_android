package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.utils.ItemPersonal
import com.app.bookselling.view.adapter.PersonalAdapter
import com.app.bookselling.view.ui.fragment.profile.PersonalFragment
import dagger.Module
import dagger.Provides

@Module
class PersonalModule(private val fragment: PersonalFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): PersonalFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun providePersonalAdapter(context : Context) = PersonalAdapter(context, ArrayList<ItemPersonal>())
}