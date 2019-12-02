package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Category

interface HomeCommonView :BaseView {
    fun loadCommonBooksSuccess(categories: List<Category>)
}