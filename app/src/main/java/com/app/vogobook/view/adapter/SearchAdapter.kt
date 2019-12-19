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
import kotlinx.android.synthetic.main.item_cart.view.*


class SearchAdapter(private var booktList: ArrayList<Book>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var count: Int = 1
        holder.txtTitle.text = booktList[position].title.toString()
        holder.txtPrice.text = booktList[position].price.toString()
        holder.txtAuthor.text = booktList[position].author.toString()
        Picasso.get().load(booktList[position].image).resize(
            Resources.getSystem().displayMetrics.heightPixels * 2 / 9 * 9 / 15,
            Resources.getSystem().displayMetrics.widthPixels * 4 / 10
        ).centerCrop().into(holder.imgBook)

        holder.btnDelete!!.setOnClickListener {
            deleteItem(position)
        }
        holder.btnPlus?.setOnClickListener {
            count++
        }
        holder.btnMinus?.setOnClickListener {
            if (count > 1)
                count--
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
        return booktList.size
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

        init {
            txtCount!!.visibility = View.GONE
            btnDelete!!.visibility = View.GONE
            btnMinus!!.visibility = View.GONE
            btnPlus!!.visibility = View.GONE
        }
    }

    fun setList(arr: ArrayList<Book>) {
        arr.reverse()
        booktList = arr
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        booktList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, booktList.size)
    }

}