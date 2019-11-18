package com.app.bookselling.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common.view.*
import kotlinx.android.synthetic.main.item_home_new_release.view.*
import kotlinx.android.synthetic.main.item_home_top_selling.view.*
import kotlinx.android.synthetic.main.item_home_top_selling.view.image_book
import kotlinx.android.synthetic.main.item_home_top_selling.view.text_view_book_author
import kotlinx.android.synthetic.main.item_home_top_selling.view.text_view_book_price
import kotlinx.android.synthetic.main.item_home_top_selling.view.text_view_book_title
import kotlinx.android.synthetic.main.item_home_top_selling.view.text_view_rate

class NewReleaseAdapter(private var context: Context, private var newReleaseList: ArrayList<Book>) :
    RecyclerView.Adapter<NewReleaseAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.width = Resources.getSystem().displayMetrics.widthPixels
        holder.itemView.layoutParams.height =  Resources.getSystem().displayMetrics.heightPixels /7

        holder.txtTitle.text = newReleaseList[position].title
        Picasso.get().load(newReleaseList[position].image).resize(holder.itemView.layoutParams.height*2/3,  holder.itemView.layoutParams.height)
            .centerCrop().into(holder.imgBook)
        holder.txtAuthor.text = newReleaseList[position].author
        holder.txtRate.text = newReleaseList[position].rate
        holder.txtPrice.text = newReleaseList[position].price

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_new_release,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return newReleaseList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtRank: TextView = itemView.text_view_new_release
        var txtTitle: TextView = itemView.text_view_book_title
        var imgBook: ImageView = itemView.image_book
        var txtAuthor: TextView = itemView.text_view_book_author
        var txtRate: TextView = itemView.text_view_rate
        var txtPrice: TextView = itemView.text_view_book_price
    }

    fun setList(arr: ArrayList<Book>) {
        newReleaseList = arr
        notifyDataSetChanged()
    }

}