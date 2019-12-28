package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.OrderDetailPresenter
import com.app.vogobook.presenter.OrderDetailPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.OrderDetailAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.OrderDetailView
import com.app.vogobook.view.ui.fragment.OrderDetailFragment
import com.app.vogobook.viewmodel.OrderDetailModel
import com.app.vogobook.viewmodel.OrderDetailModelImpl
import dagger.Module
import dagger.Provides

@Module
class OrderDetailModule(private val fragment: OrderDetailFragment, private val view: OrderDetailView) {

    @Provides
    @FragmentScope
    fun provideFragment(): OrderDetailFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideOrderDetailAdapter(context : Context) = OrderDetailAdapter(context, ArrayList())

    @Provides
    @FragmentScope
    fun provideOrderDetailViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): OrderDetailModel =
        OrderDetailModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideAccountPresenter(
        accountModel: OrderDetailModel,
        context: Context,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): OrderDetailPresenter = OrderDetailPresenterImpl(view, accountModel, context, roomUIManager, sessionManager)
}