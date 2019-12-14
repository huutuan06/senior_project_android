package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.AccountView
import com.app.vogobook.viewmodel.AccountModel
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable


class AccountPresenterImpl(
    private val view: AccountView,
    private val model: AccountModel,
    private val context: Context
) : AccountPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun editAccount(jsonObject: JsonObject) {
        if (Utils.isInternetOn(context)) {
            model.editAccount(jsonObject)
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
}
