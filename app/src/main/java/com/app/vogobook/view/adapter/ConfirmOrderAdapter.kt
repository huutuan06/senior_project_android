package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Cart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart.view.image_book
import kotlinx.android.synthetic.main.item_cart.view.text_view_book_price
import kotlinx.android.synthetic.main.item_cart.view.text_view_book_title
import kotlinx.android.synthetic.main.item_confirm_order.view.*


class ConfirmOrderAdapter(private var list: ArrayList<Cart>) :
    RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtTitle.text = list[position].book_title
        Picasso.get().load(list[position].image).resize(
            Resources.getSystem().displayMetrics.heightPixels * 8 / 45 * 9 / 15,
            Resources.getSystem().displayMetrics.widthPixels * 3 / 10
        )
            .centerCrop().into(holder.imgBook)
        holder.txtPrice.text = "$" + list[position].price.toString()
        holder.txtAmountBook!!.text = list[position].total_book.toString()
        holder.txtAuthor.text = list[position].book_author.toString()
    } 

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_confirm_order,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.text_view_book_title
        var imgBook: ImageView = itemView.image_book
        var txtPrice: TextView = itemView.text_view_book_price
        var txtAuthor: TextView = itemView.text_view_book_author

        var txtAmountBook: TextView? = itemView.text_view_amount_book
    }

    fun setList(arr: ArrayList<Cart>) {
        arr.reverse()
        list = arr
        notifyDataSetChanged()
    }

}