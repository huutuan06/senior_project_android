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
import com.app.vogobook.service.model.ItemPersonal
import kotlinx.android.synthetic.main.item_personal_manage_order.view.*

class PersonalAdapter (private var context: Context, private var itemManageList: ArrayList<ItemPersonal>) : RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {


    private lateinit var mPersonalEventListener : PersonalEventListener

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtManageOrder.text = itemManageList[position].txtManage
        holder.relativeLayout.setOnClickListener {
            mPersonalEventListener.navigateToManageOrders(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_personal_manage_order,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemManageList.size
    }

    open class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgManageOrder: ImageView = itemView.image_manage_order
        var txtManageOrder: TextView = itemView.text_view_manage_order
        var relativeLayout: RelativeLayout = itemView.relative_layout_manage_orders
    }


    fun setList(arr: ArrayList<ItemPersonal>) {
        itemManageList = arr
        notifyDataSetChanged()
    }

    interface PersonalEventListener {
        fun navigateToManageOrders(position: Int)
    }
    fun setInterface(listener: PersonalEventListener) {
        mPersonalEventListener = listener
    }
}