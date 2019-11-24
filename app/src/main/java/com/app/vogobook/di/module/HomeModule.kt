package com.app.vogobook.di.module

import android.view.View
import android.widget.LinearLayout
import com.app.vogobook.R
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.home.HomeFragment
import dagger.Module
import dagger.Provides

@Module
class HomeModule(
    private val fragment: HomeFragment,
    private val viewFrag: View
) {

    @Provides
    @FragmentScope
    fun provideFragment(): HomeFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideViewFragment(): View {
        return viewFrag
    }

    @Provides
    @FragmentScope
    fun provideTabLayout(): LinearLayout = viewFrag.findViewById(R.id.tablayout_book_list) as LinearLayout
}