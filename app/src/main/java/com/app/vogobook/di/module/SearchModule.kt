package com.app.vogobook.di.module

import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.adapter.SearchAdapter
import com.app.vogobook.view.ui.activity.SearchActivity
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val activity: SearchActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): SearchActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideSearchAdapter() = SearchAdapter(ArrayList())

}