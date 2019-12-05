package com.app.vogobook.view.ui.fragment.home.tabs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.module.HomeModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.adapter.CommonAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.fragment.BaseFragment
import com.app.vogobook.view.ui.fragment.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeCommonFragment : BaseFragment(), CommonAdapter.CommonEventListener, HomeCommonView {

    @Inject lateinit var mFragment: HomeFragment

    @Inject lateinit var mContext : Context

    @Inject lateinit var mCommonAdapter : CommonAdapter

    @Inject lateinit var homeFragment: HomeFragment

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mTabLayout: LinearLayout

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mPresenter: HomeCommonPresenter

    @BindView(R.id.recycler_view_common)
    @JvmField var rcvCommon : RecyclerView? = null

    @BindView(R.id.lnError)
    @JvmField var lnError : LinearLayout? = null

    @BindView(R.id.nestedRcvCategories)
    @JvmField var lnEnestedRcvCategoriesrror : NestedScrollView? = null

    private var mDisposable: Disposable? = null

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_common, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            HomeModule(Application.instance.getCurrentFragment() as HomeFragment, Application.instance.getView())
        ).plus(HomeCommonModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)

        mToolbar.title = context!!.getString(R.string.label_app_name)
        mBottomNavigation.visibility = View.VISIBLE
        mTabLayout.visibility = View.VISIBLE

        rcvCommon?.layoutManager = LinearLayoutManager(context)
        rcvCommon?.hasFixedSize()
        rcvCommon?.adapter = mCommonAdapter
        mCommonAdapter.setInterface(this)
        mPresenter.getCommonBooks()
    }

    override fun navigateToBookDetail(book: Book) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.BOOK,book)
        mActivity.mNavController.navigate(R.id.bookDetailFragment, bundle)
    }

    override fun navigateToBookCollection(category: Category) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.CATEGORY, category)
        mActivity.mNavController.navigate(R.id.bookCollectionFragment, bundle)
    }

    override fun loadCommonBooksSuccess(categories: List<Category>) {
        if  (categories.isEmpty()) {
            lnError!!.visibility = View.VISIBLE
            lnEnestedRcvCategoriesrror!!.visibility = View.GONE
        } else {
            lnError!!.visibility = View.GONE
            lnEnestedRcvCategoriesrror!!.visibility = View.VISIBLE
            mCommonAdapter.setList(categories as ArrayList<Category>)
        }
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        // TODO
    }

    override fun showErrorMessageDialog(errorTitle: String?, errorMessage: String?) {
        // TODO
    }

    override fun setDisposable(disposable: Disposable) {
        mDisposable = disposable
    }

    override fun onDestroyView() {
        if (mDisposable != null && mDisposable!!.isDisposed) mDisposable!!.dispose()
        super.onDestroyView()
    }
}