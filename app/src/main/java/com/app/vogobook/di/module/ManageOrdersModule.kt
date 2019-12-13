package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.PersonalPresenter
import com.app.vogobook.presenter.PersonalPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.ItemPersonal
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.ManageOrdersAdapter
import com.app.vogobook.view.adapter.PersonalAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.PersonalView
import com.app.vogobook.view.ui.fragment.ManageOrdersFragment
import com.app.vogobook.view.ui.fragment.profile.PersonalFragment
import com.app.vogobook.viewmodel.PersonalModel
import com.app.vogobook.viewmodel.PersonalModelImpl
import dagger.Module
import dagger.Provides

@Module
class ManageOrdersModule(private val fragment: ManageOrdersFragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): ManageOrdersFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideManageOrdersAdapter(context : Context) = ManageOrdersAdapter(context, ArrayList())

}