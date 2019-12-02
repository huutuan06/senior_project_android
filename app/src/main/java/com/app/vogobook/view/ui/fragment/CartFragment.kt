package com.app.vogobook.view.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.view.adapter.CartAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class CartFragment : BaseFragment() {

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @BindView(R.id.layout_empty_cart)
    lateinit var emptyCartScreen: ConstraintLayout

    @BindView(R.id.layout_value_cart)
    lateinit var valueCartScreen: ConstraintLayout

    @BindView(R.id.recycler_view_cart)
    lateinit var rcvCart: RecyclerView

    @BindView(R.id.text_view_total_price)
    lateinit var txtTotalPrice: TextView

    private var mCartArrayList = ArrayList<Book>()

    private var mCartAdapter = CartAdapter(mCartArrayList)


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .inject(this)
    }

    override fun initAttributes() {
//        mBottomNavigation.setOnNavigationItemSelectedListener(mActivity)
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = "Cart"

//        mCartArrayList.clear()
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/81WWiiLgEyL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/81h2gWPTYJL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/81XR45UdqkL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/91ibhD5nhUL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/71LVKXutJmL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/91rhzcPiXAL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/41ILRrgp5-L._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/91HHxxtA1wL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/81HCcHPXZnL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )
//        mCartArrayList.add(
//            Book(
//                "Alibaba: The House That Jack Ma Built",
//                "https://images-na.ssl-images-amazon.com/images/I/81dKveFv2%2BL._AC_UL200_SR200,200_.jpg",
//                "$100"
//            )
//        )

        setList(mCartArrayList)
        rcvCart.layoutParams.height = Resources.getSystem().displayMetrics.heightPixels*23/32
        rcvCart.layoutManager = LinearLayoutManager(context)
        rcvCart.hasFixedSize()
        rcvCart.adapter = mCartAdapter

        if (mCartArrayList.size == 0) {
            valueCartScreen.visibility = View.GONE
        } else {
            emptyCartScreen.visibility = View.GONE
            txtTotalPrice.text = "$".plus(totalPrice().toString())
        }
        mBottomNavigation.visibility = View.GONE
    }

    @OnClick(R.id.button_go_shopping, R.id.button_order)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_go_shopping -> {
                mNavController.popBackStack()
                mNavController.popBackStack()
                mBottomNavigation.selectedItemId = R.id.menu_item_home
                mNavController.navigate(R.id.homeFragment)
            }

            R.id.button_order -> {
                mNavController.navigate(R.id.confirmOrderFragment)
            }
        }
    }

    private fun setList(arr: ArrayList<Book>) {
        mCartAdapter.setList(arr)

    }

    fun totalPrice(): Float {
        var totalPrice : Float = 0F
        for (item in mCartArrayList) {
//            totalPrice += item.price!!.substring(1).toFloat()
        }
        return totalPrice
    }
}