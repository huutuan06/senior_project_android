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
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.BookDetailAdapter
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookDetailView
import com.app.vogobook.view.ui.fragment.BookDetailFragment
import com.app.vogobook.viewmodel.BookDetailModel
import com.app.vogobook.viewmodel.BookDetailModelImpl
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.Module
import dagger.Provides

@Module
class BookDetailModule(
    private val bookDetailFragment: BookDetailFragment,
    private val view: BookDetailView
) {

    @Provides
    @FragmentScope
    fun provideFragment(): BookDetailFragment {
        return bookDetailFragment
    }

    @SuppressLint("RestrictedApi")
    @Provides
    @FragmentScope
    fun provideSnackBarLayout(context: Context, fragment: BookDetailFragment): CartSnackBarLayout {
        return CartSnackBarLayout(context, fragment)
    }

    @Provides
    @FragmentScope
    fun provideBookDetailAdapter(context: Context) = BookDetailAdapter(context, ArrayList())

    @Provides
    @FragmentScope
    fun provideBookDetailViewModel(
        context: Context,
        disposableManager: DisposableManager,
        bookService: BookService,
        mainActivity: MainActivity,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): BookDetailModel =
        BookDetailModelImpl(
            context,
            bookService,
            disposableManager,
            mainActivity,
            roomUIManager,
            sessionManager
        )

    @Provides
    @FragmentScope
    fun provideWriteReviewPresenter(
        module: BookDetailModel,
        context: Context,
        roomUIManager: RoomUIManager,
        sessionManager: SessionManager
    ): BookDetailPresenter = BookDetailPresenterImpl(view, module, context, roomUIManager, sessionManager)

    @Provides
    @FragmentScope
    fun provideSnackBar(
        snackbarLayout: CartSnackBarLayout,
        activity: MainActivity,
        context: Context
    ): Snackbar {
        val parentLayout: View = activity.window.decorView.findViewById(R.id.content)
        val snackbar = Snackbar.make(parentLayout, "File Choosers", Snackbar.LENGTH_LONG)
        val layout = snackbar.view as Snackbar.SnackbarLayout
        layout.background =
            ContextCompat.getDrawable(context, com.app.vogobook.R.color.windowBackground)
        val params = layout.layoutParams as FrameLayout.LayoutParams
        params.width = FrameLayout.LayoutParams.MATCH_PARENT
        params.gravity = Gravity.CENTER or Gravity.BOTTOM
        layout.layoutParams = params
        val textView =
            layout.findViewById<TextView>(com.app.vogobook.R.id.snackbar_text)
        textView.visibility = View.INVISIBLE
        if (snackbarLayout.parent != null) (snackbarLayout.parent as ViewGroup).removeView(
            snackbarLayout
        )
        layout.addView(snackbarLayout)
        snackbar.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
        return snackbar
    }
}