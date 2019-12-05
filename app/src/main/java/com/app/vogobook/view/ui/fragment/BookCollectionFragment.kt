package com.app.vogobook.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.BookCollectionModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.presenter.BookCollectionPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.adapter.CollectionAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookCollectionView
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class BookCollectionFragment : BaseFragment(), BookCollectionView {

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mCollectionAdapter: CollectionAdapter

    @Inject lateinit var mPresenter: BookCollectionPresenter

    @Inject lateinit var mBookCollectionAdapter: CollectionAdapter

    @BindView(R.id.recycler_view_collection)
    @JvmField var rcvCollection : RecyclerView? = null

    @BindView(R.id.lnError)
    @JvmField var lnError : LinearLayout? = null

    @BindView(R.id.nestedRcvCategories)
    @JvmField var lnEnestedRcvCategoriesrror : NestedScrollView? = null

    private lateinit var mViewFrag : View


    var category: Category? = null


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewFrag = inflater.inflate(R.layout.fragment_book_collection, container, false)
        return mViewFrag
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            BookCollectionModule(this, this)
        ).inject(this)
    }

    override fun initAttributes() {
        category = arguments?.getParcelable(Constants.CATEGORY)
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = category!!.name
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE


        rcvCollection!!.layoutManager = GridLayoutManager(context, 3)
        rcvCollection?.hasFixedSize()
        rcvCollection!!.adapter = mCollectionAdapter

        mPresenter.getBookCollection(category!!.id)
//        mCollectionAdapter.setInterface(this)
    }

    override fun loadBookCollectionSuccess(books: List<Book>) {
        if  (books.isEmpty()) {
            lnError!!.visibility = View.VISIBLE
            lnEnestedRcvCategoriesrror!!.visibility = View.GONE
        } else {
            lnError!!.visibility = View.GONE
            lnEnestedRcvCategoriesrror!!.visibility = View.VISIBLE
            mBookCollectionAdapter.setList(books as ArrayList<Book>)
        }
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        //TODO
    }

    override fun showErrorMessageDialog(errorTitle: String?, errorMessage: String?) {
        //TODO
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }
}