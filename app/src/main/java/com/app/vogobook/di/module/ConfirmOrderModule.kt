package com.app.vogobook.di.module

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.presenter.BookDetailPresenterImpl
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.presenter.ConfirmOrderPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.BookDetailAdapter
import com.app.vogobook.view.adapter.ConfirmOrderAdapter
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookDetailView
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.view.ui.fragment.BookDetailFragment
import com.app.vogobook.view.ui.fragment.ConfirmOrderFragment
import com.app.vogobook.viewmodel.BookDetailModel
import com.app.vogobook.viewmodel.BookDetailModelImpl
import com.app.vogobook.viewmodel.ConfirmOrderModel
import com.app.vogobook.viewmodel.ConfirmOrderModelImpl
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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