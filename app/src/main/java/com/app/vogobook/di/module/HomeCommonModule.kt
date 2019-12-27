package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.livedata.VogoBookLive
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.presenter.HomeCommonPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.view.adapter.CommonAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import com.app.vogobook.viewmodel.HomeCommonModel
import com.app.vogobook.viewmodel.HomeCommonModelImpl
import dagger.Module
import dagger.Provides

@Module
class HomeCommonModule(private val fragment: HomeCommonFragment, private val view: HomeCommonView) {

    @Provides
    @SubFragmentScope
    fun provideFragment(): HomeCommonFragment {
        return fragment
    }

    @Provides
    @SubFragmentScope
    fun provideCommonAdapter(context: Context) = CommonAdapter(context, ArrayList())

    @Provides
    @SubFragmentScope
    fun provideHomeCommonViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        vogoBookLive: VogoBookLive
    ): HomeCommonModel =
        HomeCommonModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager, vogoBookLive)

    @Provides
    @SubFragmentScope
    fun provideHomeCommonPresenter(
        homeCommonModel: HomeCommonModel,
        context: Context
    ): HomeCommonPresenter = HomeCommonPresenterImpl(view, homeCommonModel, context)
}