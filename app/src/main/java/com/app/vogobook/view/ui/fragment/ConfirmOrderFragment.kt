package com.app.vogobook.view.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.ConfirmOrderAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class ConfirmOrderFragment : BaseFragment() {

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

    @BindView(R.id.recycler_view_confirm_order)
    lateinit var rcvConfirmOrder: RecyclerView

    @BindView(R.id.button_order)
    lateinit var btnOrder: Button

    private var mConFirmOrderArrayList = ArrayList<Cart>()

    private var mConFirmOrderAdapter = ConfirmOrderAdapter(mConFirmOrderArrayList)


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_confirm_order, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .inject(this)
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

        btnOrder.setOnClickListener {
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

    private fun setList(arr: ArrayList<Cart>) {
        mConFirmOrderAdapter.setList(arr)

    }
}