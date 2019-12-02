package com.app.vogobook.view.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart.view.image_book
import kotlinx.android.synthetic.main.item_cart.view.text_view_book_price
import kotlinx.android.synthetic.main.item_cart.view.text_view_book_title
import kotlinx.android.synthetic.main.item_confirm_order.view.*


class ConfirmOrderAdapter(private var bookList: ArrayList<Book>) :
    RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.layoutParams.height =
            Resources.getSystem().displayMetrics.heightPixels * 8 / 45
        holder.txtTitle.layoutParams.width =
            Resources.getSystem().displayMetrics.widthPixels * 3 / 5


        holder.txtTitle.text = bookList[position].title
        Picasso.get().load(bookList[position].image).resize(
            holder.itemView.layoutParams.height * 9 / 15,
            holder.itemView.layoutParams.height * 9 / 10
        )
            .centerCrop().into(holder.imgBook)
//        holder.txtPrice.text = bookList[position].price


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
        return bookList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.text_view_book_title
        var imgBook: ImageView = itemView.image_book
        var txtPrice: TextView = itemView.text_view_book_price

        var txtAmountBook: TextView? = itemView.text_view_amount_book
    }

    fun setList(arr: ArrayList<Book>) {
        bookList = arr
        notifyDataSetChanged()
    }

}