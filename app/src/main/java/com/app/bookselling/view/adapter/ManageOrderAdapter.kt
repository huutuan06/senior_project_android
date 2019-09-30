package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.ItemManageOrders

class ManageOrderAdapter (private var context: Context ,private var itemManageList: ArrayList<ItemManageOrders>) : RecyclerView.Adapter<ManageOrderAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_personal, parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    open class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // TODO
    }

    fun setList(arr: ArrayList<ItemManageOrders>) {
        itemManageList = arr
        notifyDataSetChanged()
    }


}