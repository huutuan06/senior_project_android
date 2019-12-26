package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.callback.HomeNewReleaseView
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.viewmodel.HomeCommonModel
import com.app.vogobook.viewmodel.HomeNewReleaseModel
import com.app.vogobook.viewmodel.HomeTopSellingModel
import io.reactivex.disposables.Disposable


class HomeNewReleasePresenterImpl(
    private val view: HomeNewReleaseView,
    private val model: HomeNewReleaseModel,
    private val context: Context
) : HomeNewReleasePresenter {

    init {
        model.attachPresenter(this)
    }

    override fun getNewReleaseBooks() {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.loadNewReleaseBooksFromLocal()
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

    override fun loadNewReleaseBooksSuccess(
        books: List<Book>
    ) {
        view.updateProgressDialog(false)
        view.loadNewReleaseBooksSuccess(books)
    }
}
