package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.app.vogobook.R
import com.app.vogobook.service.model.BookOrder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order.view.*

class OrderDetailAdapter(private var context: Context, private var listBooks: ArrayList<BookOrder>) :
    RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {

    private lateinit var mOrderEventListener: OrderDetailEventListener
    var mOrderPrice: Float = 0F

    interface OrderDetailEventListener {
        fun setOrderPrice(price: Float)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.height =
            Resources.getSystem().displayMetrics.heightPixels * 2 / 15
        holder.txtTitle.layoutParams.width =
            Resources.getSystem().displayMetrics.widthPixels * 2 / 5
        Picasso.get().load(listBooks[position].image).resize(
            holder.itemView.layoutParams.height * 9 / 15,
            holder.itemView.layoutParams.height * 9 / 10
        )
            .centerCrop().into(holder.imgBook)
        holder.txtTitle.text = listBooks[position].book_title.toString()
        holder.txtPrice.text = "$" + listBooks[position].price.toString()
        holder.txtTotalBooks.text = listBooks[position].total_book.toString()

        listBooks.forEach {
            mOrderPrice+= listBooks[position].total_book!! * listBooks[position].price!!
        }
        mOrderEventListener.setOrderPrice(mOrderPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_order,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgBook = itemView.image_book!!
        var txtTitle = itemView.text_view_book_title!!
        var txtPrice = itemView.text_view_book_price!!
        var txtTotalBooks = itemView.text_view_total_book!!
    }


    fun setList(arr: ArrayList<BookOrder>) {
        listBooks = arr
        notifyDataSetChanged()
    }

    fun setInterface(listener: OrderDetailEventListener) {
        mOrderEventListener = listener
    }
}