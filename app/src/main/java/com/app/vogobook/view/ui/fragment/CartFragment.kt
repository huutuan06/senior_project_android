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

    @BindView(R.id.layout_empty_cart)
    lateinit var emptyCartScreen: ConstraintLayout

    @BindView(R.id.layout_value_cart)
    lateinit var valueCartScreen: ConstraintLayout

    @BindView(R.id.recycler_view_cart)
    lateinit var rcvCart: RecyclerView

    @BindView(R.id.text_view_total_price)
    lateinit var txtTotalPrice: TextView

    private var mCartAdapter : CartAdapter? = null


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
        mCartAdapter = CartAdapter(ArrayList())
        rcvCart.adapter = mCartAdapter
        mCartAdapter!!.setInterface(this)
        mRoomUIManager.getAllCarts(mSessionManager.user_id, object : IRoomListener<Cart> {
            override fun showListData(carts: List<Cart>) {
                mCartAdapter!!.setList(carts as ArrayList<Cart>)
                if (carts.isNotEmpty()) {
                    emptyCartScreen.visibility = View.GONE
                    valueCartScreen.visibility = View.VISIBLE
                } else {
                    emptyCartScreen.visibility = View.VISIBLE
                    valueCartScreen.visibility = View.GONE
                }
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
                mNavController.navigate(R.id.confirmOrderFragment)
            }
        }
    }

    override fun deleteCart(cart: Cart) {
        mPresenter.deleteCart(cart)
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