package com.app.vogobook.view.ui.fragment.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.PersonalModule
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.presenter.PersonalPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.service.model.ItemPersonal
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.PersonalAdapter
import com.app.vogobook.view.custom.CircleTransform
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.LoginActivity
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.PersonalView
import com.app.vogobook.view.ui.fragment.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PersonalFragment : BaseFragment(), PersonalAdapter.PersonalEventListener, PersonalView {


    private var mItemPersonalArrayList = ArrayList<ItemPersonal>()

    @Inject
    lateinit var mAdapter: PersonalAdapter

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mToolbar: Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mPresenter: PersonalPresenter

    @Inject
    lateinit var mSessionManager: SessionManager

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mOrderList: ArrayList<Order>

    @Inject
    lateinit var mPgDialog: VogoLoadingDialog

    @BindView(R.id.recycler_view_personal)
    @JvmField
    var rcvPersonal: RecyclerView? = null

    @BindView(R.id.image_profile)
    lateinit var mImageProfile: ImageView

    @BindView(R.id.text_view_name)
    lateinit var mName: TextView

    @BindView(R.id.text_view_member_date)
    lateinit var mDate: TextView


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(
                PersonalModule(this, this)
            ).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {

        Picasso.get().load(mActivity.user.avatar).transform(CircleTransform()).into(mImageProfile)
        mName.text = mActivity.user.name.toString()
        mDate.text = "Member from " + mActivity.user.created_at.toString()

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
        mToolbar.title = context!!.getString(R.string.label_personal_string)
        setHasOptionsMenu(true)
        mBottomNavigation.visibility = View.VISIBLE

        mPresenter.getOrders()

    }

    override fun navigateToManageOrders(position: Int) {
        val bundle = Bundle()
        var listOrder = ArrayList<Order>()
        when (position) {
            0 -> {
                listOrder = mOrderList
            }
            1 -> {
                //TODO
            }
            2 -> {
                mOrderList.forEach {
                    if (it.confirm_ordering == 1)
                        listOrder.add(it)
                }
            }
            3 -> {
                mOrderList.forEach {
                    if (it.delivery == 1)
                        listOrder.add(it)
                }
            }
            4 -> {
                mOrderList.forEach {
                    if (it.success == 1)
                        listOrder.add(it)
                }
            }
            5 -> {
                mOrderList.forEach {
                    if (it.cancel == 1)
                        listOrder.add(it)
                }
            }
            6 -> {
                mOrderList.forEach {
                    if (it.payment == 1)
                        listOrder.add(it)
                }
            }
        }
        bundle.putString(mContext.getString(R.string.label_manage_orders), mItemPersonalArrayList[position].txtManage)
        bundle.putParcelableArrayList(Constants.LIST_ORDERS, listOrder)
        mActivity.mNavController.navigate(R.id.manageOrdersFragment, bundle)
    }

    @OnClick(R.id.card_view_personal, R.id.button_logout)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.card_view_personal -> {
                mActivity.mNavController.navigate(R.id.accountFragment)
            }
            R.id.button_logout -> {
                mPresenter.logOut()
            }
        }
    }

    private fun showList(arr: ArrayList<ItemPersonal>) {
        mAdapter.setList(arr)
    }

    override fun logoutSuccess() {
        mSessionManager.clear()
        val intent = Intent(mActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mActivity.startActivity(intent)
        mActivity.finish()
    }

    override fun getOrdersSuccess(orders: List<Order>) {
       mOrderList = ArrayList(orders)
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
        //TODO
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }
}