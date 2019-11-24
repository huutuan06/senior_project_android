package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.utils.ItemPersonal
import com.app.vogobook.view.adapter.PersonalAdapter
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
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