package com.app.vogobook.di.module

import android.accounts.Account
import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.AccountPresenter
import com.app.vogobook.presenter.AccountPresenterImpl
import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.presenter.HomeCommonPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.view.adapter.CommonAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.AccountView
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.fragment.AccountFragment
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import com.app.vogobook.viewmodel.AccountModel
import com.app.vogobook.viewmodel.AccountModelImpl
import com.app.vogobook.viewmodel.HomeCommonModel
import com.app.vogobook.viewmodel.HomeCommonModelImpl
import dagger.Module
import dagger.Provides

@Module
class AccountModule(private val fragment: AccountFragment, private val view: AccountView) {

    @Provides
    @FragmentScope
    fun provideFragment(): AccountFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideAccountViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager
    ): AccountModel =
        AccountModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager)

    @Provides
    @FragmentScope
    fun provideAccountPresenter(
        accountModel: AccountModel,
        context: Context
    ): AccountPresenter = AccountPresenterImpl(view, accountModel, context)
}