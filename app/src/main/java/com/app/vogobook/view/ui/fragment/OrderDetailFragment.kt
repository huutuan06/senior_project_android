package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.OrderDetailModule
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.presenter.OrderDetailPresenter
import com.app.vogobook.view.adapter.OrderDetailAdapter
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.OrderDetailView
import com.app.vogobook.view.ui.dialog.VogoDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class OrderDetailFragment : BaseFragment(), OrderDetailAdapter.OrderDetailEventListener , OrderDetailView,  VogoDialog.IListener{

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

    @Inject
    lateinit var mPresenter: OrderDetailPresenter

    @Inject
    lateinit var mPgDialog: VogoLoadingDialog

    @Inject
    lateinit var mVogoDialog: VogoDialog

    @BindView(R.id.recycler_view_books)
    lateinit var rcvBook: RecyclerView

    @BindView(R.id.text_view_order_code)
    lateinit var tvOrderCode: TextView

    @BindView(R.id.text_view_order_date)
    lateinit var tvOrderDate: TextView

    @BindView(R.id.text_view_order_status)
    lateinit var tvOrderStatus: TextView

    @BindView(R.id.text_view_total_price)
    lateinit var tvOrderPrice: TextView

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
            .plus(OrderDetailModule(this, this)).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = context!!.getString(R.string.label_order)
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE


        mOrder = arguments!!.getParcelable(mContext.getString(R.string.label_order))!!
        mAdapter.setList(ArrayList(mOrder.arrBooks!!))
        mAdapter.setInterface(this)
        mVogoDialog.setListener(this)

        tvOrderCode.text = mOrder.code.toString()
        tvOrderDate.text = mOrder.updated_at.toString()
        when (1) {
            mOrder.confirmed_ordering -> {
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
            else -> {
                tvOrderStatus.text = "Seen"
            }
        }

        rcvBook.layoutManager = LinearLayoutManager(context)
        rcvBook.hasFixedSize()
        rcvBook.adapter = mAdapter
    }

    @SuppressLint("SetTextI18n")
    override fun setOrderPrice(price: Float) {
        tvOrderPrice.text = "$" +String.format("%.2f",price)
    }

    @OnClick(R.id.button_cancel_order)
    fun processEventClick(view: View) {
        when(view.id) {
            R.id.button_cancel_order -> {
                mPresenter.cancelOrder(mOrder.id)
                           }
        }
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        if (isShowProgressDialog) {
            if (!mPgDialog.isShowing) {
                mPgDialog.show()
            }
        } else {
            if (!mActivity.isDestroyed && mPgDialog.isShowing)
                mPgDialog.dismiss()
        }
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        if (!mVogoDialog.isAdded && !mActivity.isDestroyed) {
            mVogoDialog.updateMessageDialog(mContext, errorTitle, errorMessage)
            mVogoDialog.show(mActivity.supportFragmentManager, "OrderDetailFragment")
        }
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }

    override fun doYourAction() {
        mActivity.mNavController.navigate(R.id.personalFragment)
    }

    override fun dimiss() {
        //TODO
    }

}