package com.app.vogobook.view.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.ManageOrdersModule
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.adapter.ManageOrdersAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList


class ManageOrdersFragment : BaseFragment(), ManageOrdersAdapter.ManageOrderListener {

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mAdapter: ManageOrdersAdapter

    @BindView(R.id.recycler_view_manage_orders)
    @JvmField
    var mRecyclerView: RecyclerView? = null

    @BindView(R.id.layout_empty_order)
    lateinit var mLayoutEmptyOrders: ConstraintLayout

    @BindView(R.id.layout_value_orders)
    lateinit var mLayoutValueOrders: ConstraintLayout

    private var mTitle: String? = null

    private var mOrderArraylist = ArrayList<Order>()

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_manage_orders, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(ManageOrdersModule(this)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mTitle = arguments!!.getString(mContext.getString(R.string.label_manage_orders))
        mToolbar.title = mTitle

        mBottomNavigation.visibility = View.GONE
        mOrderArraylist = arguments!!.getParcelableArrayList(Constants.LIST_ORDERS)
        if (mOrderArraylist.isEmpty()) {
            mLayoutValueOrders.visibility = View.GONE
            mLayoutEmptyOrders.visibility = View.VISIBLE
        } else {
            mLayoutValueOrders.visibility = View.VISIBLE
            mLayoutEmptyOrders.visibility = View.GONE
            mAdapter.setList(mOrderArraylist)
        }


        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.hasFixedSize()
        mRecyclerView?.adapter = mAdapter

        mAdapter.setInterface(this)
    }

    @OnClick(R.id.button_go_shopping)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_go_shopping -> {
                mNavController.popBackStack()
                mNavController.popBackStack()
                mBottomNavigation.selectedItemId = R.id.menu_item_home
                mNavController.navigate(R.id.homeFragment)
            }
        }
    }

    override fun NavigateToOrderDetail(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable(context!!.getString(R.string.label_order),mOrderArraylist[position])
        mActivity.mNavController.navigate(R.id.orderDetailFragment, bundle)
    }

}