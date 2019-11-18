package com.app.bookselling.view.custom


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.app.bookselling.R

class CartSnackBarLayout(context: Context) : ConstraintLayout(context) {

    interface CartSnackbarInterface{
        fun showSnackBar()
    }

    companion object {
        private lateinit var listener: CartSnackbarInterface
    }
    fun AddToCartLayout(context: Context) {
        super.getContext()
        val mInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            ?: return
        val view: View = mInflater.inflate(R.layout.dialog_add_to_cart, this, true)
        ButterKnife.bind(this,view)
    }

    fun setInterface(_interface: CartSnackbarInterface){
        listener = _interface
    }
}