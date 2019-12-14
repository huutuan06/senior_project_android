package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.WriteReviewView
import com.app.vogobook.viewmodel.WriteReviewModel
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable


class WriteReviewPresenterImpl(
    private val view: WriteReviewView,
    private val model: WriteReviewModel,
    private val context: Context
) : WriteReviewPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun postReview(jsonObject: JsonObject) {
        if (Utils.isInternetOn(context)) {
            model.postReview(jsonObject)
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun submitReviewSuccess() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_success), context.getString(R.string.submit_review_successfully))
    }

    override fun submitReviewFailed() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_failure), context.getString(R.string.cannot_process_request))
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
