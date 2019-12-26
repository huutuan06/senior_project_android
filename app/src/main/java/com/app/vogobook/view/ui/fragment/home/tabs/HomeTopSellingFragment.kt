package com.app.vogobook.view.ui.fragment.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.HomeModule
import com.app.vogobook.di.module.HomeTopSellingModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.presenter.HomeTopSellingPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.adapter.TopSellingAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.view.ui.fragment.BaseFragment
import com.app.vogobook.view.ui.fragment.home.HomeFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeTopSellingFragment : BaseFragment(), HomeTopSellingView, TopSellingAdapter.HomeTopSellingListener {

    @Inject
    lateinit var mTopSellingAdapter: TopSellingAdapter

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mPresenter: HomeTopSellingPresenter

    @Inject
    lateinit var mActivity: MainActivity

    @BindView(R.id.recycler_view_top_selling)
    @JvmField
    var rcvTopSelling: RecyclerView? = null


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_top_selling, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(
                HomeModule(
                    Application.instance.getCurrentFragment() as HomeFragment,
                    Application.instance.getView()
                )
            ).plus(HomeTopSellingModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mPresenter.getTopSellingBooks()
        mToolbar.title = context!!.getString(R.string.label_app_name)
    }

    override fun loadTopSellingBooksSuccess(books: List<Book>) {
        mTopSellingAdapter.setList(ArrayList(books))
        mTopSellingAdapter.setInterface(this)
        rcvTopSelling?.layoutManager = LinearLayoutManager(context)
        rcvTopSelling?.hasFixedSize()
        rcvTopSelling?.adapter = mTopSellingAdapter
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        //TODO
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        //TODO
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }

    override fun navigateToBookDetail(book: Book) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.BOOK, book)
        mActivity.mNavController.navigate(R.id.bookDetailFragment, bundle)
    }
}