package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.presenter.ConfirmOrderPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.ConfirmOrderAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.view.ui.fragment.ConfirmOrderFragment
import com.app.vogobook.viewmodel.ConfirmOrderModel
import com.app.vogobook.viewmodel.ConfirmOrderModelImpl
import dagger.Module
import dagger.Provides

@Module
class ConfirmOrderModule(
    private val confirmOrderFragment: ConfirmOrderFragment,
    private val view: ConfirmOrderView
) {

    @Provides
    @FragmentScope
    fun provideFragment(): ConfirmOrderFragment {
        return confirmOrderFragment
    }

    @Provides
    @FragmentScope
    fun provideConfirmOrderViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): ConfirmOrderModel =
        ConfirmOrderModelImpl(
            context,
            bookService,
            disposableManager,
            mainActivity,
            roomUIManager,
            sessionManager
        )

    @Provides
    @FragmentScope
    fun provideConfirmOrderPresenter(
        model: ConfirmOrderModel,
        context: Context,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): ConfirmOrderPresenter = ConfirmOrderPresenterImpl(view, model, context, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideAdapter() : ConfirmOrderAdapter = ConfirmOrderAdapter(ArrayList())
}