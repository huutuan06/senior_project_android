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
import com.app.vogobook.di.module.HomeNewReleaseModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.presenter.HomeNewReleasePresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.adapter.NewReleaseAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeNewReleaseView
import com.app.vogobook.view.ui.fragment.BaseFragment
import com.app.vogobook.view.ui.fragment.home.HomeFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeNewReleaseFragment : BaseFragment(), HomeNewReleaseView, NewReleaseAdapter.HomeNewReleaseEventListener {

    @Inject
    lateinit var mNewReleaseAdapter: NewReleaseAdapter

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mPresenter: HomeNewReleasePresenter

    @Inject
    lateinit var mActivity: MainActivity

    @BindView(R.id.recycler_view_new_release)
    @JvmField
    var rcvNewRelease: RecyclerView? = null


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_new_release, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(
                HomeModule(
                    Application.instance.getCurrentFragment() as HomeFragment,
                    Application.instance.getView()
                )
            ).plus(HomeNewReleaseModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.title = context!!.getString(R.string.label_app_name)
        mPresenter.getNewReleaseBooks()
    }

    override fun loadNewReleaseBooksSuccess(books: List<Book>) {
        mNewReleaseAdapter.setList(ArrayList(books))
        mNewReleaseAdapter.setInterface(this)
        rcvNewRelease?.layoutManager = LinearLayoutManager(context)
        rcvNewRelease?.hasFixedSize()
        rcvNewRelease?.adapter = mNewReleaseAdapter
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