package com.app.bookselling.view.ui.fragment

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
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.utils.Book
import com.app.bookselling.view.adapter.CartAdapter
import com.app.bookselling.view.ui.activity.MainActivity
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

        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/81WWiiLgEyL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/81h2gWPTYJL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/81XR45UdqkL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/91ibhD5nhUL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/71LVKXutJmL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/91rhzcPiXAL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/41ILRrgp5-L._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/91HHxxtA1wL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/81HCcHPXZnL._AC_UL200_SR200,200_.jpg","$100"))
        mCartArrayList.add(Book("Alibaba: The House That Jack Ma Built","https://images-na.ssl-images-amazon.com/images/I/81dKveFv2%2BL._AC_UL200_SR200,200_.jpg","$100"))

        setList(mCartArrayList)
        rcvCart.layoutParams.height = Resources.getSystem().displayMetrics.heightPixels*23/32
        rcvCart.layoutManager = LinearLayoutManager(context)
        rcvCart.hasFixedSize()
        rcvCart.adapter = mCartAdapter

        if (mCartArrayList.size == 0) {
            valueCartScreen.visibility = View.GONE
        } else {
            emptyCartScreen.visibility = View.GONE

        }
        mBottomNavigation.visibility = View.GONE
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

    private fun setList(arr: ArrayList<Book>) {
        mCartAdapter.setList(arr)

    }
}