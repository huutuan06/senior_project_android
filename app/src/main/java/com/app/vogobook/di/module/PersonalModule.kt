package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.presenter.PersonalPresenter
import com.app.vogobook.presenter.PersonalPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.ItemPersonal
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.PersonalAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.PersonalView
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
import com.app.vogobook.viewmodel.PersonalModel
import com.app.vogobook.viewmodel.PersonalModelImpl
import dagger.Module
import dagger.Provides

@Module
class PersonalModule(private val fragment: PersonalFragment, private val view: PersonalView) {

    @Provides
    @FragmentScope
    fun provideFragment(): PersonalFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun providePersonalAdapter(context : Context) = PersonalAdapter(context, ArrayList())

    @Provides
    @FragmentScope
    fun provideOrdersList() = ArrayList<Order>()

    @Provides
    @FragmentScope
    fun providePersonalViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): PersonalModel =
        PersonalModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideAccountPresenter(
        accountModel: PersonalModel,
        context: Context
    ): PersonalPresenter = PersonalPresenterImpl(view, accountModel, context)
}