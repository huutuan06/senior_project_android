 package com.app.vogobook.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.app.vogobook.R
import com.app.vogobook.service.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common_category.view.*

class CollectionAdapter(private var bookList: ArrayList<Book>) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = bookList[position].title
        holder.txtPrice.text = bookList[position].price

        Picasso.get().load(bookList[position].image).resize(180,  270)
            .centerCrop().into(holder.imgBook)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book_collection,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle = itemView.text_view_book_title!!
        var imgBook = itemView.image_book!!
        var txtPrice = itemView.text_view_book_price!!

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    fun setList(arr: ArrayList<Book>) {
        bookList = arr
        notifyDataSetChanged()
    }
}