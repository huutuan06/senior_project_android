package com.app.bookselling.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(private var cartList: ArrayList<Book>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.height = Resources.getSystem().displayMetrics.heightPixels *2/9
        holder.txtTitle.layoutParams.width =  Resources.getSystem().displayMetrics.widthPixels *3/5


        holder.txtTitle.text = cartList[position].title
        Picasso.get().load(cartList[position].image).resize(holder.itemView.layoutParams.height*9/15,  holder.itemView.layoutParams.height*9/10)
            .centerCrop().into(holder.imgBook)
        holder.txtPrice.text = cartList[position].price

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
    }

    fun setList(arr: ArrayList<Book>) {
        cartList = arr
        notifyDataSetChanged()
    }

}