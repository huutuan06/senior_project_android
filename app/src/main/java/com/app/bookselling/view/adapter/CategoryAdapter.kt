package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.ItemCommon
import kotlinx.android.synthetic.main.item_home_common.view.*

class CategoryAdapter (private var context: Context, private var commonList: ArrayList<ItemCommon>) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO
        holder.txtCategory.text = commonList[position].category
//        holder.rcvCommonItem.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_common, parent, false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    open class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var txtCategory = itemView.text_view_category
        var rcvCommonItem = itemView.recycler_view_common_category
    }

    fun setList(arr: ArrayList<ItemCommon>) {
        commonList = arr
        notifyDataSetChanged()
    }