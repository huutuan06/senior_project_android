package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.viewmodel.HomeCommonModel
import com.app.vogobook.viewmodel.HomeTopSellingModel
import io.reactivex.disposables.Disposable


class HomeTopSellingPresenterImpl(
    private val view: HomeTopSellingView,
    private val model: HomeTopSellingModel,
    private val context: Context
) : HomeTopSellingPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun getTopSellingBooks() {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.loadTopSellingBooksFromLocal()
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }

    override fun loadToppSellingBooksSuccess(
        books: List<Book>
    ) {
        view.updateProgressDialog(false)
        view.loadTopSellingBooksSuccess(books)
    }
}
