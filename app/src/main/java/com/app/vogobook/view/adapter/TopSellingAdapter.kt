package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_top_selling.view.*

class TopSellingAdapter(private var context: Context, private var topSellingList: ArrayList<Book>) :
    RecyclerView.Adapter<TopSellingAdapter.ViewHolder>() {

    private lateinit var mHomeTopSellingListener: HomeTopSellingListener

    interface HomeTopSellingListener {
        fun navigateToBookDetail(book: Book)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.width = Resources.getSystem().displayMetrics.widthPixels
        holder.itemView.layoutParams.height =  Resources.getSystem().displayMetrics.heightPixels /7

        holder.txtTitle.text = topSellingList[position].title
        Picasso.get().load(topSellingList[position].image).resize(Resources.getSystem().displayMetrics.heightPixels /7*2/3,  Resources.getSystem().displayMetrics.heightPixels /7)
            .centerCrop().into(holder.imgBook)
        holder.txtAuthor.text = topSellingList[position].author
//        holder.txtRate.text = topSellingList[position].rate
        holder.txtPrice.text = "$" + topSellingList[position].price.toString()
        holder.txtRank.text = (position + 1).toString()
        holder.item.setOnClickListener {
            mHomeTopSellingListener.navigateToBookDetail(topSellingList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_top_selling,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return topSellingList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtRank: TextView = itemView.text_view_selling_rank
        var txtTitle: TextView = itemView.text_view_book_title
        var imgBook: ImageView = itemView.image_book
        var txtAuthor: TextView = itemView.text_view_book_author
        var txtRate: TextView = itemView.text_view_rate
        var txtPrice: TextView = itemView.text_view_book_price
        var item: ConstraintLayout = itemView.item_topselling
    }

    fun setList(arr: ArrayList<Book>) {
        topSellingList = arr
        notifyDataSetChanged()
    }

    fun setInterface(listener: HomeTopSellingListener){
        mHomeTopSellingListener = listener
    }
}