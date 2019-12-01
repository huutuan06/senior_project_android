package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.view.adapter.NewReleaseAdapter
import com.app.vogobook.view.ui.fragment.home.tabs.HomeNewReleaseFragment
import dagger.Module
import dagger.Provides

@Module
class HomeNewReleaseModule (private val fragment : HomeNewReleaseFragment) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeNewReleaseFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideNewReleaseAdapter(context: Context) =  NewReleaseAdapter(context, ArrayList<Book>())
}