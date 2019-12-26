package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.CartPresenter
import com.app.vogobook.presenter.CartPresenterImpl
import com.app.vogobook.presenter.HomeTopSellingPresenter
import com.app.vogobook.presenter.HomeTopSellingPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.CartAdapter
import com.app.vogobook.view.adapter.TopSellingAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.CartView
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.view.ui.fragment.CartFragment
import com.app.vogobook.view.ui.fragment.home.tabs.HomeTopSellingFragment
import com.app.vogobook.viewmodel.CartModel
import com.app.vogobook.viewmodel.CartModelImpl
import com.app.vogobook.viewmodel.HomeTopSellingModel
import com.app.vogobook.viewmodel.HomeTopSellingModelImpl
import dagger.Module
import dagger.Provides

@Module
class CartModule (private val fragment : CartFragment, private var view: CartView) {

    @Provides
    @FragmentScope
    fun provideFragment() : CartFragment {
        return fragment
    }

    @Provides
    @FragmentScope
    fun provideCartAdapter(context: Context) =  CartAdapter(ArrayList<Cart>())


    @Provides
    @FragmentScope
    fun provideCartViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): CartModel =
        CartModelImpl(context, bookService, disposableManager, mainActivity, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideCartPresenter(
        cartModel: CartModel,
        context: Context
    ): CartPresenter = CartPresenterImpl(view, cartModel, context)
}