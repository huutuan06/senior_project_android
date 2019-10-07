package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common_category.view.*

class CategoryAdapter(private var context: Context, private var categoriesList: ArrayList<Book>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = categoriesList[position].title
        holder.price.text = categoriesList[position].price

        Picasso.get().load(categoriesList[position].image).resize(180,  280)
            .centerCrop().into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_common_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.text_view_book_title!!
        var image = itemView.image_book!!
        var price = itemView.text_view_book_price!!
    }


    fun setList(arr: ArrayList<Book>) {
        categoriesList = arr
        notifyDataSetChanged()
    }
}