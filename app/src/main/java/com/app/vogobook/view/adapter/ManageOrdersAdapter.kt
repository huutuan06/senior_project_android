package com.app.vogobook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Order
import kotlinx.android.synthetic.main.item_manage_order.view.*

class ManageOrdersAdapter(private var context: Context, private var orderList: ArrayList<Order>) :
    RecyclerView.Adapter<ManageOrdersAdapter.ViewHolder>() {

    lateinit var mListener: ManageOrderListener

    interface ManageOrderListener {
        fun NavigateToOrderDetail(position: Int)
    }

    fun setInterface(listener: ManageOrderListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtOrderName.text = orderList[position].name.toString()
        holder.txtOrderCode.text = orderList[position].code.toString()
        holder.txtOrderDate.text = orderList[position].updated_at.toString()
        when {
            orderList[position].confirm_ordering == 1 -> {
                holder.txtOrderStatus.text = "Confirmed"
            }
            orderList[position].unsuccessfull_payment == 1 -> {
                holder.txtOrderStatus.text = "Payment failed"
            }
            orderList[position].delivery == 1 -> {
                holder.txtOrderStatus.text = "Being transported"
            }
            orderList[position].success == 1 -> {
                holder.txtOrderStatus.text = "Successfull"
            }
            orderList[position].cancel == 1 -> {
                holder.txtOrderStatus.text = "Canceled"
            }
        }

        holder.itemOrder.setOnClickListener {
            mListener.NavigateToOrderDetail(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_manage_order,
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
        var itemOrder: ConstraintLayout = itemView.item_order
    }


    fun setList(arr: ArrayList<Order>) {
        orderList = arr
        notifyDataSetChanged()
    }
}