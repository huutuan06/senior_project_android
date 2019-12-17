package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.OrderDetailModule
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.view.adapter.OrderDetailAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class OrderDetailFragment : BaseFragment() {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mAdapter: OrderDetailAdapter

    @BindView(R.id.recycler_view_books)
    lateinit var rcvBook: RecyclerView

    @BindView(R.id.text_view_order_code)
    lateinit var tvOrderCode: TextView

    @BindView(R.id.text_view_order_date)
    lateinit var tvOrderDate: TextView

    @BindView(R.id.text_view_order_status)
    lateinit var tvOrderStatus: TextView

    var mOrder = Order()


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(OrderDetailModule(this)).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = context!!.getString(R.string.label_order)
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE


        mOrder = arguments!!.getParcelable(mContext.getString(R.string.label_order))
        mAdapter.setList(ArrayList(mOrder.arrBooks))

        tvOrderCode.text = mOrder.code.toString()
        tvOrderDate.text = mOrder.updated_at.toString()
        when (1) {
            mOrder.confirm_ordering -> {
                tvOrderStatus.text = "Confirmed"
            }
            mOrder.delivery -> {
                tvOrderStatus.text = "Being Transsported"
            }
            mOrder.success -> {
                tvOrderStatus.text = "Successful"
            }
            mOrder.cancel -> {
                tvOrderStatus.text = "Canceled"
            }
            mOrder.payment -> {
                tvOrderStatus.text = "Paid"
            }
        }
        if (mOrder.confirm_ordering == mOrder.delivery && mOrder.delivery == mOrder.success && mOrder.success == mOrder.cancel
            && mOrder.cancel == mOrder.payment && mOrder.payment == 0)
            tvOrderStatus.text = "Seen"

        rcvBook.layoutManager = LinearLayoutManager(context)
        rcvBook.hasFixedSize()
        rcvBook.adapter = mAdapter
    }


}