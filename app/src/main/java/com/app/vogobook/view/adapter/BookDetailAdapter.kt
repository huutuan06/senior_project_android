package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.custom.CircleTransform
import com.squareup.picasso.Picasso

class BookDetailAdapter(private var context: Context, private var listReviews: ArrayList<Review>) :
    RecyclerView.Adapter<BookDetailAdapter.ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(listReviews[position].user_avatar).transform(CircleTransform()).into(holder.imgAvatar)
        holder.txtName.text = listReviews[position].user_name
        holder.rattingBar.rating = listReviews[position].rate!!
        holder.date.text = Utils.getDate(context, listReviews[position].date!!.toLong())
        holder.txtContent.text = listReviews[position].content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_book_detail,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listReviews.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgAvatar: ImageView = itemView.findViewById(R.id.image_view_avatar)

        var txtName: TextView = itemView.findViewById(R.id.text_view_user_name)

        var rattingBar: RatingBar = itemView.findViewById(R.id.rating_bar)

        var date: TextView = itemView.findViewById(R.id.text_view_date)

        var txtContent: TextView = itemView.findViewById(R.id.text_view_content)
    }


    fun setList(arr: ArrayList<Review>) {
        listReviews = arr
        notifyDataSetChanged()
    }
}