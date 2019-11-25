package com.app.vogobook.view.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.app.vogobook.R


/**
 * Please git --config to configure email/username to know who commit and push code.
 * Who is author of the class.
 */
class CartSnackBarLayout(context: Context) : ConstraintLayout(context) {

    @BindView(R.id.text_view_book_title)
    @JvmField var tvNameOFBook: TextView? = null

    interface CartSnackBarLayoutInterface {
        // You can change name of function hello by your name
        // You can update param here and send it to BookDetailFragment.

        fun hello(text: String)
        fun dismissSnackbar()
    }

    private var listener: CartSnackBarLayoutInterface? = null

    /**
     * OK. I will explain here.
     * With Java. Attribute is declared in Constructor. You can see Java class.
     * If we switch to Kotlin. They optimize it with syntax here CartSnackBarLayout(context: Context) : ConstraintLayout(context)
     * Constructor is Kotlin is replace function init().
     * You can get params from Constructors in here
     * For example, context.
     */
    init {
        val mInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = mInflater.inflate(R.layout.snackbar_add_to_cart, this, true)
        ButterKnife.bind(this, view)
    }

    @OnClick(R.id.button_go_to_cart, R.id.button_close)
    fun onClick(view: View) {
        // This is where you get data
        // Here, I set string = "HEllo Ben"
        when (view.id) {
            R.id.button_go_to_cart -> {
                listener!!.hello(tvNameOFBook!!.text as String)
            }
            R.id.button_close -> {
                listener!!.dismissSnackbar()
            }
        }
    }

    fun attachDialogInterface(_interface: CartSnackBarLayoutInterface?) {
        listener = _interface
    }
}