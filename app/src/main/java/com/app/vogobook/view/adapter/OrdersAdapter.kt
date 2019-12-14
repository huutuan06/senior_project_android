package com.app.vogobook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Order
import kotlinx.android.synthetic.main.item_manage_order.view.*

class OrdersAdapter(private var context: Context, private var listBooks: ArrayList<Book>) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.txtOrderName.text = listBooks[position].name.toString()
//        holder.txtOrderCode.text = listBooks[position].code.toString()
//        holder.txtOrderDate.text = listBooks[position].updated_at.toString()

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

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtOrderName: TextView = itemView.text_view_order_name
        var txtOrderCode: TextView = itemView.text_view_order_code
        var txtOrderDate: TextView = itemView.text_view_order_date
        var txtOrderStatus: TextView = itemView.text_view_order_status
        var itemOrder: ConstraintLayout = itemView.item_order
    }


    fun setList(arr: ArrayList<Book>) {
        listBooks = arr
        notifyDataSetChanged()
    }
}