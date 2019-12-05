package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.BookCollectionPresenter
import com.app.vogobook.presenter.BookCollectionPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.view.adapter.CollectionAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookCollectionView
import com.app.vogobook.view.ui.fragment.BookCollectionFragment
import com.app.vogobook.viewmodel.BookCollectionModel
import com.app.vogobook.viewmodel.BookCollectionModelImpl
import dagger.Module
import dagger.Provides

@Module
class BookCollectionModule(
    private val fragment: BookCollectionFragment,
    private val view: BookCollectionView
) {

    @Provides
    @FragmentScope
    fun provideFragment(): BookCollectionFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideCollectionAdapter() = CollectionAdapter(ArrayList())

    @Provides
    @FragmentScope
    fun provideBookCollectionViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager
    ): BookCollectionModel = BookCollectionModelImpl(
        context,
        bookService,
        disposableManager,
        mainActivity,
        roomUIManager
    )

    @Provides
    @FragmentScope
    fun provideBookCollectionPresenter(
        bookCollectionModel: BookCollectionModel,
        context: Context
    ): BookCollectionPresenter = BookCollectionPresenterImpl(view, bookCollectionModel, context)
}