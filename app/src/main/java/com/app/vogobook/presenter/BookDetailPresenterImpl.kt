package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.BookDetailView
import com.app.vogobook.view.ui.callback.PersonalView
import com.app.vogobook.view.ui.callback.WriteReviewView
import com.app.vogobook.viewmodel.BookDetailModel
import com.app.vogobook.viewmodel.PersonalModel
import com.app.vogobook.viewmodel.WriteReviewModel
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable


class BookDetailPresenterImpl(
    private val view: BookDetailView,
    private val model: BookDetailModel,
    private val context: Context
) : BookDetailPresenter {

    init {
        model.attachPresenter(this)
    }



    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
