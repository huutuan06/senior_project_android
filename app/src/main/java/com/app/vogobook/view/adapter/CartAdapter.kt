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
import com.app.vogobook.localstorage.entities.Cart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cart.view.*


class CartAdapter(private var cartList: ArrayList<Cart>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var count: Int = 1

        holder.itemView.layoutParams.height =
            Resources.getSystem().displayMetrics.heightPixels * 2 / 9
        holder.txtTitle.layoutParams.width =
            Resources.getSystem().displayMetrics.widthPixels * 3 / 5


        holder.txtTitle.text = cartList[position].book_title
        Picasso.get().load(cartList[position].image).resize(
            holder.itemView.layoutParams.height * 9 / 15,
            holder.itemView.layoutParams.height * 9 / 10
        )
            .centerCrop().into(holder.imgBook)
//        holder.txtPrice.text = cartList[position].price


        holder.btnDelete!!.setOnClickListener {
            deleteItem(position)
        }
        holder.btnPlus?.setOnClickListener {
            count++
            holder.txtCount!!.text = count.toString()
        }
        holder.btnMinus?.setOnClickListener {
            if (count > 1)
                count--
            holder.txtCount!!.text = count.toString()
        }
        holder.txtCount!!.text = count.toString()


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

        var btnDelete: ImageView? = itemView.button_delete
        var btnPlus: ImageView? = itemView.button_plus
        var btnMinus: ImageView? = itemView.button_minus
        var txtCount: TextView? = itemView.text_view_count_book
    }

    fun setList(arr: ArrayList<Cart>) {
        cartList = arr
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartList.size)
    }

}