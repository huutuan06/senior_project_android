package com.app.vogobook.view.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.ConfirmOrderModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.service.response.Address
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.ConfirmOrderAdapter
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.view.ui.dialog.VogoDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ConfirmOrderFragment : BaseFragment(), ConfirmOrderView , VogoDialog.IListener {

    override fun doYourAction() {
        // TODO
    }

    override fun dimiss() {
        // TODO
    }

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mRoomUIManager: RoomUIManager

    @Inject
    lateinit var mSessionManager: SessionManager

    @Inject
    lateinit var mPresenter: ConfirmOrderPresenter

    @Inject
    lateinit var mAdapter: ConfirmOrderAdapter

    @Inject
    lateinit var mPgDialog: VogoLoadingDialog

    @Inject
    lateinit var mVogoDialog: VogoDialog

    @BindView(R.id.recycler_view_confirm_order)
    lateinit var rcvConfirmOrder: RecyclerView

    @BindView(R.id.button_order)
    lateinit var btnOrder: Button

    @BindView(R.id.edit_text_name)
    lateinit var edtName: EditText

    @BindView(R.id.edit_text_phone)
    lateinit var edtPhone: EditText

    @BindView(R.id.edit_text_address)
    lateinit var edtAddress: EditText

    @BindView(R.id.rdCash)
    lateinit var rdCash: RadioButton

    @BindView(R.id.rdMomo)
    lateinit var rdMomo: RadioButton

    @BindView(R.id.rdCreditCard)
    lateinit var rdCreditCard: RadioButton

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(ConfirmOrderModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = "Confirm Order"
        rcvConfirmOrder.layoutParams.height =
            Resources.getSystem().displayMetrics.heightPixels * 23 / 32
        rcvConfirmOrder.layoutManager = LinearLayoutManager(context)
        rcvConfirmOrder.hasFixedSize()
        rcvConfirmOrder.adapter = mAdapter
        mVogoDialog.setListener(this)
        mRoomUIManager.getAllCarts(mSessionManager.user_id, object : IRoomListener<Cart> {
            override fun showListData(carts: List<Cart>) {
                mAdapter.setList(carts as ArrayList<Cart>)
            }
        })

        mBottomNavigation.visibility = View.GONE

        if (mActivity.user.name != null)
            edtName.setText(mActivity.user.name.toString())
        if (mActivity.user.phone_number != null)
            edtPhone.setText(mActivity.user.phone_number.toString())
        if (mActivity.user.address != null)
            edtAddress.setText(mActivity.user.address.toString())

    }

    @OnClick(R.id.button_order)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_order -> {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("Thank for your order!\n Let's move to main screen.")
                    .setPositiveButton("OK") { dialog, id ->
                        var address = Address()
                        address.name = edtName.text.toString()
                        address.phone_number = edtPhone.text.toString()
                        address.address = edtAddress.text.toString()
                        if (rdCash.isChecked) {
                            address.payment_method = rdCash.text.toString()
                        }
                        if (rdMomo.isChecked) {
                            address.payment_method = rdMomo.text.toString()
                        }
                        if (rdCreditCard.isChecked) {
                            address.payment_method = rdMomo.text.toString()
                        }
                        mPresenter.submitOrder(address, mAdapter.getList())
                    }
                val alert = dialogBuilder.create()
                alert.setTitle("Order is successful!")
                alert.show()
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
            mVogoDialog.show(mActivity.supportFragmentManager, "WriteReviewFragment")
        }
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }
}