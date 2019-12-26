package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.presenter.HomeTopSellingPresenter
import com.app.vogobook.presenter.HomeTopSellingPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.view.adapter.TopSellingAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.view.ui.fragment.home.tabs.HomeTopSellingFragment
import com.app.vogobook.viewmodel.HomeTopSellingModel
import com.app.vogobook.viewmodel.HomeTopSellingModelImpl
import dagger.Module
import dagger.Provides

@Module
class HomeTopSellingModule (private val fragment : HomeTopSellingFragment, private var view: HomeTopSellingView) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeTopSellingFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideTopSellingAdapter(context: Context) =  TopSellingAdapter(context, ArrayList<Book>())


    @Provides
    @SubFragmentScope
    fun provideHomeTopSellingViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager
    ): HomeTopSellingModel =
        HomeTopSellingModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager)

    @Provides
    @SubFragmentScope
    fun provideHomeTopSellingPresenter(
        homeTopSellingModel: HomeTopSellingModel,
        context: Context
    ): HomeTopSellingPresenter = HomeTopSellingPresenterImpl(view, homeTopSellingModel, context)
}