package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.view.ui.callback.BookCollectionView
import com.app.vogobook.viewmodel.BookCollectionModel
import io.reactivex.disposables.Disposable
import com.app.vogobook.utils.objects.Utils

class BookCollectionPresenterImpl(
    private var view: BookCollectionView,
    private var model: BookCollectionModel,
    private var context: Context
) : BookCollectionPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun getBookCollection(category_id: Int?) {
        if (Utils.isInternetOn(context)) {
            model.getBookCollection(category_id)
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.no_internet_connection)
            )
        }
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }

    override fun loadBookCollectionSuccess(books: List<Book>) {
        view.loadBookCollectionSuccess(books)
    }
}