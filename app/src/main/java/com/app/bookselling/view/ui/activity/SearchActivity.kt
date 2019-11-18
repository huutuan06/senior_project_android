package com.app.bookselling.view.ui.activity

import android.widget.EditText
import butterknife.BindView
import com.app.bookselling.R

class SearchActivity : BaseActivity() {

    @BindView(R.id.toolbar_search)
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @BindView(R.id.edit_text_search)
    lateinit var edtSearch: EditText

    override val layoutRes: Int
        get() = R.layout.activity_search

    override fun distributedDaggerComponents() {
        //TODO
    }

    override fun initAttributes() {
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        edtSearch.requestFocus()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}