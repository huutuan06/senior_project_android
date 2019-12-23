package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.presenter.HomeNewReleasePresenter
import com.app.vogobook.presenter.HomeNewReleasePresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.view.adapter.NewReleaseAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeNewReleaseView
import com.app.vogobook.view.ui.fragment.home.tabs.HomeNewReleaseFragment
import com.app.vogobook.viewmodel.HomeNewReleaseModel
import com.app.vogobook.viewmodel.HomeNewReleaseModelImpl
import dagger.Module
import dagger.Provides

@Module
class HomeNewReleaseModule (private val fragment : HomeNewReleaseFragment, private val view: HomeNewReleaseView) {

    @Provides
    @SubFragmentScope
    fun provideFragment() : HomeNewReleaseFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideNewReleaseAdapter(context: Context) =  NewReleaseAdapter(context, ArrayList<Book>())

    @Provides
    @SubFragmentScope
    fun provideHomeNewReleaseViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager
    ): HomeNewReleaseModel =
        HomeNewReleaseModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager)

    @Provides
    @SubFragmentScope
    fun provideHomeTopSellingPresenter(
        homeNewReleaseModule: HomeNewReleaseModel,
        context: Context
    ): HomeNewReleasePresenter = HomeNewReleasePresenterImpl(view, homeNewReleaseModule, context)
}