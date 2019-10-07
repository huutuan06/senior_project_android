package com.app.bookselling.view.ui.fragment.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import com.app.bookselling.view.adapter.CategoryAdapter
import com.app.bookselling.view.adapter.CommonAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import javax.inject.Inject

class HomeCommonFragment : BaseFragment() {

//    @Inject
//    lateinit var mFragment: HomeFragment

//    @BindView(R.id.tvTest)
//    @JvmField var mEventTest: TextView? = null

    private var mCommonArrayList= ArrayList<ItemCommon>()

    @Inject lateinit var mCommonAdapter : CommonAdapter

    @Inject lateinit var homeFragment: HomeFragment

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
            HomeModule(Application.instance.getCurrentFragment() as HomeFragment)
        ).plus(HomeCommonModule(this)).inject(this)
    }

    override fun initAttributes() {
//        if (arguments != null)
//            Log.i("TAG", arguments!!.getString("name"))
        mCommonArrayList.add(ItemCommon("Comic Collection"))
        mCommonArrayList.add(ItemCommon("On Vietnam"))
        mCommonArrayList.add(ItemCommon("Comics"))
        mCommonArrayList.add(ItemCommon("Business"))
        mCommonArrayList.add(ItemCommon("Book for you"))
        showListOfCommon(mCommonArrayList)

        rcvCommon?.layoutManager = LinearLayoutManager(context)
        rcvCommon?.hasFixedSize()
        rcvCommon?.adapter = mCommonAdapter
    }

    private fun showListOfCommon(arrayListOfCommon: ArrayList<ItemCommon>) {
        mCommonAdapter.setList(arrayListOfCommon)
    }


//    @OnClick(R.id.tvTest)
//    fun processOnClick(view : View) {
//        when(view.id) {
//            R.id.tvTest -> {
//                mFragment.getNavController().navigate(R.id.action_homeCommonFragment_to_homeCommonDetailFragment);
//            }
//        }
//    }
}