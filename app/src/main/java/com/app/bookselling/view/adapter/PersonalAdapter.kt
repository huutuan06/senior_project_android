package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.ItemPersonal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_personal_manage_order.view.*
import kotlinx.android.synthetic.main.item_personal_profile.view.*

class PersonalAdapter (private var context: Context, private var itemManageList: ArrayList<ItemPersonal>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            0 -> {
                val profileViewHolder: ProfileViewHolder = holder as ProfileViewHolder
                Picasso.get().load(itemManageList[position].imgProfile).resize(60,  60)
                    .centerCrop().into(profileViewHolder.imgProfile)
                profileViewHolder.txtName.text = itemManageList[position].nameProfile
            }
            1-> {
                val manageOrdersViewHolder: ManageOrdersViewHolder = holder as ManageOrdersViewHolder
//                Picasso.get().load(itemManageList[position].imgManage).resize(60,  60)
//                    .centerCrop().into(manageOrdersViewHolder.imgManageOrder)
                manageOrdersViewHolder.txtManageOrder.text = itemManageList[position].txtManage
            }
            2 -> {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> return ProfileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_personal_profile, parent, false))
            1 -> return ManageOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_personal_manage_order, parent, false))
            2 -> return LogoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_personal_logout, parent, false))
        }
        return ProfileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_personal_profile, parent, false))
    }

    override fun getItemCount(): Int {
        return itemManageList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            itemManageList[position].nameProfile != "" -> 0
            itemManageList[position].txtManage != "" -> 1
            else -> 2
        }
    }

    open class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgProfile: ImageView = itemView.image_profile
        var txtName: TextView = itemView.text_view_name
    }

    open class ManageOrdersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgManageOrder: ImageView = itemView.image_manage_order
        var txtManageOrder: TextView = itemView.text_view_manage_order
    }

    open class LogoutViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun setList(arr: ArrayList<ItemPersonal>) {
        itemManageList = arr
        notifyDataSetChanged()
    }


}