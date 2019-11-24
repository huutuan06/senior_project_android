package com.app.vogobook.view.ui.fragment.home.tabs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.module.HomeModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.service.model.Book
import com.app.vogobook.utils.ItemCommon
import com.app.vogobook.view.adapter.CommonAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.fragment.BaseFragment
import com.app.vogobook.view.ui.fragment.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class HomeCommonFragment : BaseFragment(), CommonAdapter.CommonEventListener {

    @Inject lateinit var mFragment: HomeFragment

    private var mCommonArrayList = ArrayList<ItemCommon>()

    @Inject lateinit var mContext : Context

    @Inject lateinit var mCommonAdapter : CommonAdapter

    @Inject lateinit var homeFragment: HomeFragment

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mTabLayout: LinearLayout

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar


    @BindView(R.id.recycler_view_category)
    @JvmField var rcvCommonCategory : RecyclerView? = null

    @BindView(R.id.recycler_view_common)
    @JvmField var rcvCommon : RecyclerView? = null

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
        ).plus(HomeCommonModule(this)).inject(this)
    }

    override fun initAttributes() {
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)

        mToolbar.title = "Book Selling Online"
        mBottomNavigation.visibility = View.VISIBLE
        mTabLayout.visibility = View.VISIBLE

        mCommonArrayList.clear()
        mCommonArrayList.add(ItemCommon("Comic Collection"))
        mCommonArrayList.add(ItemCommon("On Vietnam"))
        mCommonArrayList.add(ItemCommon("Comics"))
        mCommonArrayList.add(ItemCommon("Business"))
        mCommonArrayList.add(ItemCommon("Book for you"))
        showListOfCommon(mCommonArrayList)

        rcvCommon?.layoutManager = LinearLayoutManager(context)
        rcvCommon?.hasFixedSize()
        rcvCommon?.adapter = mCommonAdapter
        mCommonAdapter.setInterface(this)
    }

    private fun showListOfCommon(arrayListOfCommon: ArrayList<ItemCommon>) {
        mCommonAdapter.setList(arrayListOfCommon)
    }

    override fun navigateToBookDetail(book: Book) {
        mActivity.mNavController.navigate(R.id.bookDetailFragment)
    }

    override fun navigateToBookCollection(title: String) {
        val bundle = Bundle()
        bundle.putString("title", title)
        mActivity.mNavController.navigate(R.id.bookCollectionFragment, bundle)
    }
}