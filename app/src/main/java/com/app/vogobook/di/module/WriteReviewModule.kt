package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.WriteReviewPresenter
import com.app.vogobook.presenter.WriteReviewPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.WriteReviewView
import com.app.vogobook.view.ui.fragment.WriteReviewFragment
import com.app.vogobook.viewmodel.WriteReviewModel
import com.app.vogobook.viewmodel.WriteReviewModelImpl
import dagger.Module
import dagger.Provides

@Module
class WriteReviewModule(private val fragment: WriteReviewFragment, private val view: WriteReviewView) {

    @Provides
    @FragmentScope
    fun provideFragment(): WriteReviewFragment {
        return fragment
    }


    @Provides
    @FragmentScope
    fun provideWriteReviewViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): WriteReviewModel =
        WriteReviewModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideWriteReviewPresenter(
        writeReviewModel: WriteReviewModel,
        context: Context
    ): WriteReviewPresenter = WriteReviewPresenterImpl(view, writeReviewModel, context)
}