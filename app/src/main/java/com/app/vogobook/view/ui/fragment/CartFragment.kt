package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.CartModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.CartPresenter
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.adapter.CartAdapter
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.CartView
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CartFragment : BaseFragment(), CartAdapter.CartEventListener, CartView {

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mSessionManager: SessionManager

    @Inject
    lateinit var mRoomUIManager : RoomUIManager

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mPresenter: CartPresenter

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mCartAdapter: CartAdapter

    @BindView(R.id.layout_empty_cart)
    lateinit var emptyCartScreen: ConstraintLayout

    @BindView(R.id.layout_value_cart)
    lateinit var valueCartScreen: ConstraintLayout

    @BindView(R.id.recycler_view_cart)
    lateinit var rcvCart: RecyclerView

    @BindView(R.id.text_view_total_price)
    lateinit var txtTotalPrice: TextView

    private var mTotalPrice: Float = 0F


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(CartModule(this, this))
            .inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = context!!.getString(R.string.label_screen_cart)
        rcvCart.layoutParams.height = Resources.getSystem().displayMetrics.heightPixels*23/32
        rcvCart.layoutManager = LinearLayoutManager(context)
        rcvCart.hasFixedSize()
        rcvCart.adapter = mCartAdapter
        mCartAdapter!!.setInterface(this)

        mTotalPrice = 0F
        mRoomUIManager.getAllCarts(mSessionManager.user_id, object : IRoomListener<Cart> {
            @SuppressLint("SetTextI18n")
            override fun showListData(carts: List<Cart>) {
                mCartAdapter!!.setList(carts as ArrayList<Cart>)
                if (carts.isNotEmpty()) {
                    emptyCartScreen.visibility = View.GONE
                    valueCartScreen.visibility = View.VISIBLE
                    carts.forEach {
                        mTotalPrice += it.total_book!!*it.price!!
                    }
                } else {
                    emptyCartScreen.visibility = View.VISIBLE
                    valueCartScreen.visibility = View.GONE
                }
                txtTotalPrice.text = "$" +String.format("%.2f",mTotalPrice)
            }
        })
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
                val bundle = Bundle()
                bundle.putString(context!!.getString(R.string.label_cart_price), txtTotalPrice.text.toString())
                mNavController.navigate(R.id.confirmOrderFragment, bundle)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun deleteCart(cart: Cart, totalBooks: Int) {
        mPresenter.deleteCart(cart)
        mTotalPrice -= cart.price!!*totalBooks
        txtTotalPrice.text = "$" +String.format("%.2f",mTotalPrice)
        if (mCartAdapter!!.itemCount == 1) {
            emptyCartScreen.visibility = View.VISIBLE
            valueCartScreen.visibility = View.GONE
        } else {
            emptyCartScreen.visibility = View.GONE
            valueCartScreen.visibility = View.VISIBLE
        }

    }

    @SuppressLint("SetTextI18n")
    override fun updateCart(cartId: Int,totalBooks: Int, price: Float, type: String) {
        if (type == "Increase") {
            mTotalPrice += price
        } else {
            mTotalPrice -= price
        }
        mPresenter.updateCart(cartId, totalBooks)
        txtTotalPrice.text = "$" +String.format("%.2f",mTotalPrice)
    }

    override fun notifyMaximumBookAllow() {
        Toast.makeText(context,context!!.getString(R.string.label_maximum_book), Toast.LENGTH_SHORT).show()
    }

    override fun notifyNotEnoughBook() {
        Toast.makeText(mContext,mContext.getString(R.string.label_not_enough_book), Toast.LENGTH_SHORT).show()
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