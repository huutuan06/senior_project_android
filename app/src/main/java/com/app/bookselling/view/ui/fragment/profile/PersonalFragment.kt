package com.app.bookselling.view.ui.fragment.profile

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.PersonalModule
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemPersonal
import com.app.bookselling.view.adapter.PersonalAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class PersonalFragment : BaseFragment(), PersonalAdapter.PersonalEventListener {


    private var mItemPersonalArrayList = ArrayList<ItemPersonal>()

    @Inject lateinit var mAdapter: PersonalAdapter

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @BindView(R.id.recycler_view_personal)
    @JvmField var rcvPersonal : RecyclerView? = null

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            PersonalModule(this)
        ).inject(this)
    }

    override fun initAttributes() {
        mItemPersonalArrayList.clear()
        mItemPersonalArrayList.add(ItemPersonal("Manage orders"))
        mItemPersonalArrayList.add(ItemPersonal("The orders have been seen"))
        mItemPersonalArrayList.add(ItemPersonal("The orders have been confirmed"))
        mItemPersonalArrayList.add(ItemPersonal("The orders are being shipped"))
        mItemPersonalArrayList.add(ItemPersonal("The orders successful"))
        mItemPersonalArrayList.add(ItemPersonal("The orders canceled"))
        mItemPersonalArrayList.add(ItemPersonal("The books have been purchased"))
        mItemPersonalArrayList.add(ItemPersonal("My comments"))
        showList(mItemPersonalArrayList)

        rcvPersonal?.layoutManager = LinearLayoutManager(context)
        rcvPersonal?.hasFixedSize()
        rcvPersonal?.adapter = mAdapter
        mAdapter.setInterface(this)

        mActivity.setSupportActionBar(mToolbar)
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)
        mToolbar.title = "Personal"
        setHasOptionsMenu(true)
        mBottomNavigation.visibility = View.VISIBLE

    }

    override fun navigateToManageOrders() {
        mActivity.mNavController.navigate(R.id.manageOrdersFragment)
    }

    @OnClick(R.id.card_view_personal)
    fun processEventClick(view: View){
        when (view.id) {
            R.id.card_view_personal -> {
                mActivity.mNavController.navigate(R.id.accountFragment)
            }
        }
    }

    private fun showList(arr : ArrayList<ItemPersonal>) {
        mAdapter.setList(arr)
    }
}