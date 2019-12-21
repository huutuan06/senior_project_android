package com.app.vogobook.di.component

import com.app.vogobook.di.module.SearchModule
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.SearchActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {
    fun inject(activity: SearchActivity): SearchActivity
}