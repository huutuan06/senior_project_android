package com.app.vogobook.view.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.ConfirmOrderAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ConfirmOrderFragment : BaseFragment(), ConfirmOrderView {

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

    @BindView(R.id.text_view_total_price)
    lateinit var mTotalPrice: TextView

    private var mConFirmOrderArrayList = ArrayList<Cart>()

    private var mConFirmOrderAdapter = ConfirmOrderAdapter(mConFirmOrderArrayList)

    private var mCartPrice: String = ""


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

        setList(mConFirmOrderArrayList)
        rcvConfirmOrder.layoutParams.height =
            Resources.getSystem().displayMetrics.heightPixels * 23 / 32
        rcvConfirmOrder.layoutManager = LinearLayoutManager(context)
        rcvConfirmOrder.hasFixedSize()
        rcvConfirmOrder.adapter = mConFirmOrderAdapter

        mRoomUIManager.getAllCarts(mSessionManager.user_id, object : IRoomListener<Cart> {
            override fun showListData(carts: List<Cart>) {
                mConFirmOrderAdapter.setList(carts as ArrayList<Cart>)
            }
        })

        mBottomNavigation.visibility = View.GONE

        if (mActivity.user.name != null)
            edtName.setText(mActivity.user.name.toString())
        if (mActivity.user.phone_number != null)
            edtPhone.setText(mActivity.user.phone_number.toString())
        if (mActivity.user.address != null)
            edtAddress.setText(mActivity.user.address.toString())
        mCartPrice = arguments!!.getString(context!!.getString(R.string.label_cart_price))!!
        mTotalPrice.text = mCartPrice
    }

    @OnClick(R.id.button_order)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_order -> {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("Thank for your order!\n Let's move to main screen.")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Order is successful!")
                alert.show()
            }
        }
    }

    private fun setList(arr: ArrayList<Cart>) {
        mConFirmOrderAdapter.setList(arr)
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
}