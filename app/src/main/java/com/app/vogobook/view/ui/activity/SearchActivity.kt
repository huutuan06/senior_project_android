package com.app.vogobook.view.ui.activity

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.SearchModule
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.adapter.SearchAdapter
import javax.inject.Inject

class SearchActivity : BaseActivity() , TextView.OnEditorActionListener {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mSearchActivity: SearchActivity

    @Inject
    lateinit var mRoomUIManager: RoomUIManager

    @Inject
    lateinit var mSearchAdapter: SearchAdapter

    @BindView(R.id.edtSearchBox)
    @JvmField var edtSearchBox : EditText? = null

    @BindView(R.id.recycler_search)
    @JvmField var rcvSearch : RecyclerView? = null

    @BindView(R.id.layout_books)
    @JvmField var layout_books : ConstraintLayout? = null

    @BindView(R.id.layout_no_found)
    @JvmField var layout_no_found : ConstraintLayout? = null

    override val layoutRes: Int
        get() = R.layout.activity_search

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(SearchModule(this)).inject(this)
    }

    override fun initAttributes() {
        edtSearchBox!!.setOnEditorActionListener(this)
        rcvSearch!!.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        rcvSearch!!.run {
            isNestedScrollingEnabled = false
            this.setHasFixedSize(true)
            adapter = mSearchAdapter
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (edtSearchBox!!.text.isNotEmpty()) {
                mRoomUIManager.getAllBooksBySearch(Utils.enclosePercentage(edtSearchBox!!.text.toString()), object : IRoomListener<Book> {
                    override fun showListData(books: List<Book>) {
                        if (books.isNotEmpty()) {
                            layout_no_found!!.visibility = View.GONE
                            layout_books!!.visibility = View.VISIBLE
                        } else {
                            layout_no_found!!.visibility = View.VISIBLE
                            layout_books!!.visibility = View.GONE
                        }
                        mSearchAdapter.setList(books as ArrayList<Book>)
                        Utils.hiddenKeyBoard(mSearchActivity)
                    }
                })
            }
        }
        return true
    }

}