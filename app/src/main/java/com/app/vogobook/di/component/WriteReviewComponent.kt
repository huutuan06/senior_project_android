package com.app.vogobook.di.component

import com.app.vogobook.di.module.PersonalModule
import com.app.vogobook.di.module.WriteReviewModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.WriteReviewFragment
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [WriteReviewModule::class])
interface WriteReviewComponent {
    fun inject(writeReviewFragment: WriteReviewFragment): WriteReviewFragment
}