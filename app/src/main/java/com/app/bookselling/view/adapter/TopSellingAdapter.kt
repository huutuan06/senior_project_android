package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common.view.*
import kotlinx.android.synthetic.main.item_home_top_selling.view.*

class TopSellingAdapter(private var context: Context, private var topSellingList: ArrayList<Book>) :
    RecyclerView.Adapter<TopSellingAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.txtRank.text = topSellingList[position].title
        holder.txtTitle.text = topSellingList[position].title
        Picasso.get().load(topSellingList[position].image).resize(160,  240)
            .centerCrop().into(holder.imgBook)
        holder.txtAuthor.text = topSellingList[position].author
        holder.txtRate.text = topSellingList[position].rate
        holder.txtPrice.text = topSellingList[position].price

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_top_selling,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return topSellingList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtRank = itemView.text_view_selling_rank
        var txtTitle = itemView.text_view_book_title
        var imgBook = itemView.image_book
        var txtAuthor = itemView.text_view_book_author
        var txtRate = itemView.text_view_rate
        var txtPrice = itemView.text_view_book_price
    }

    fun setList(arr: ArrayList<Book>) {
        topSellingList = arr
        notifyDataSetChanged()
    }

}