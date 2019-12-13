package com.app.vogobook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.utils.ItemPersonal
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.item_personal_manage_order.view.*

class ManageOrdersAdapter(private var context: Context, private var orderList: ArrayList<Order>) :
    RecyclerView.Adapter<ManageOrdersAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtOrderName.text = orderList[position].name.toString()
        holder.txtOrderCode.text = orderList[position].code.toString()
        holder.txtOrderDate.text = orderList[position].updated_at.toString()
        if (orderList[position].confirm_ordering == 1) {
            holder.txtOrderStatus.text = "Confirmed"
        } else if (orderList[position].unsuccessfull_payment == 1) {
            holder.txtOrderStatus.text = "Payment failed"
        }else if (orderList[position].delivery == 1) {
            holder.txtOrderStatus.text = "Being transported"
        }else if (orderList[position].success == 1) {
            holder.txtOrderStatus.text = "Successfull"
        }else if (orderList[position].cancel == 1) {
            holder.txtOrderStatus.text = "Canceled"
        }
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
        return orderList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtOrderName: TextView = itemView.text_view_order_name
        var txtOrderCode: TextView = itemView.text_view_order_code
        var txtOrderDate: TextView = itemView.text_view_order_date
        var txtOrderStatus: TextView = itemView.text_view_order_status
    }


    fun setList(arr: ArrayList<Order>) {
        orderList = arr
        notifyDataSetChanged()
    }
}