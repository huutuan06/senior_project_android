package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.di.scope.SubFragmentScope
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import com.app.bookselling.view.adapter.CategoryAdapter
import com.app.bookselling.view.adapter.CommonAdapter
import com.app.bookselling.view.adapter.TopSellingAdapter
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeTopSellingFragment
import dagger.Module
import dagger.Provides

@Module
class HomeTopSellingModule (private val fragment : HomeTopSellingFragment) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeTopSellingFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideTopSellingAdapter(context: Context) =  TopSellingAdapter(context, ArrayList<Book>())
}