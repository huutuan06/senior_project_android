package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.viewmodel.HomeCommonModel
import io.reactivex.disposables.Disposable


class HomeCommonPresenterImpl(
    private val view: HomeCommonView,
    private val model: HomeCommonModel,
    private val context: Context
) : HomeCommonPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun getCommonBooks() {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.getCommonBooks()
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

    override fun loadCommonBooksSuccess(
        categories: List<Category>,
        books: List<Book>
    ) {
        view.updateProgressDialog(false)
        for  (category in categories) {
            val temps: ArrayList<Book> = ArrayList()
            for (book in books) {
                if (book.category_id == category.id) {
                    temps.add(book)
                }
            }
            category.arrBooks = temps
        }
        view.loadCommonBooksSuccess(categories)
    }
}
