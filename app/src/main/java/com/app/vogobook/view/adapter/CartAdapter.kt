package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.livedata.VogoBookLive
import com.app.vogobook.livedata.`object`.LiveDataBook
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.activity.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlin.collections.ArrayList


class CartAdapter(private var cartList: ArrayList<Cart>, private var vogoBookLive: VogoBookLive, private var activity: MainActivity) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private lateinit var mCartEventListener: CartEventListener

    interface CartEventListener{
        fun deleteCart(cart: Cart, totalBooks: Int)
        fun updateCart(cartId: Int,totalBooks: Int, price: Float, type: String)
        fun notifyMaximumBookAllow()
//        fun notifyNotEnoughBook()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var count: Int? = cartList[position].total_book

        holder.txtTitle.text = cartList[position].book_title.toString()
        holder.txtPrice.text = "$" + cartList[position].price.toString()
        holder.txtAuthor.text = cartList[position].book_author.toString()
        holder.txtCount!!.text = cartList[position].total_book.toString()
        Picasso.get().load(cartList[position].image).resize(
            Resources.getSystem().displayMetrics.heightPixels * 2 / 9 * 9 / 15,
            Resources.getSystem().displayMetrics.widthPixels * 4 / 10
        ).centerCrop().into(holder.imgBook)
//        holder.txtPrice.text = cartList[position].price

        holder.btnDelete!!.setOnClickListener {
            mCartEventListener.deleteCart(cartList[position], count!!)
            deleteItem(position)
        }
        holder.btnPlus?.setOnClickListener {
            vogoBookLive.implLiveDataBook()!!.observe(activity, Observer { book: LiveDataBook? ->
                if (TextUtils.equals(book!!.key, Utils.generateKeyFromText(cartList[position].book_title))) {
                    if (count!= null && count < book.book!!.amount!!) {
                        count++
                        holder.txtCount!!.text = count.toString()
                        mCartEventListener.updateCart(cartList[position].id!!, count, cartList[position].price!!, "Increase")
                    } else if (count == book.book!!.amount!!) {
                        mCartEventListener.notifyMaximumBookAllow()
                    }
                }
            })
        }
        holder.btnMinus?.setOnClickListener {
            if ( count != null && count > 1)
            {
                count--
                holder.txtCount!!.text = count.toString()
                mCartEventListener.updateCart(cartList[position].id!!,count, cartList[position].price!!, "Descrease")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.text_view_book_title
        var imgBook: ImageView = itemView.image_book
        var txtPrice: TextView = itemView.text_view_book_price
        var txtAuthor: TextView = itemView.text_view_book_author

        var btnDelete: ImageView? = itemView.button_delete
        var btnPlus: ImageView? = itemView.button_plus
        var btnMinus: ImageView? = itemView.button_minus
        var txtCount: TextView? = itemView.text_view_count_book
    }

    fun setList(arr: ArrayList<Cart>) {
        arr.reverse()
        cartList = arr
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, cartList.size)
    }

    fun setInterface(listener: CartEventListener) {
        mCartEventListener = listener
    }

}