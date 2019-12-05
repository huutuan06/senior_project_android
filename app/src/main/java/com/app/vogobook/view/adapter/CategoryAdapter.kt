 package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common_category.view.*

 class CategoryAdapter(private var context: Context, private var categoriesList: ArrayList<Book>, private var mCategoryEventListener: CategoryEventListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.width = Resources.getSystem().displayMetrics.widthPixels *4/15
        holder.itemView.layoutParams.height = holder.itemView.layoutParams.width * 2

        holder.txtTitle.text = categoriesList[position].title
        holder.txtPrice.text = "$" + categoriesList[position].price.toString()
        Picasso.get().load(categoriesList[position].image).resize(holder.itemView.layoutParams.width,  holder.itemView.layoutParams.width*3/2)
            .centerCrop().into(holder.imgBook)

        holder.mItemBook.setOnClickListener {
            mCategoryEventListener.navigateToBookDetail(categoriesList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_common_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    interface CategoryEventListener {
        fun navigateToBookDetail(book: Book)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemBook :ConstraintLayout = itemView.fragment_item_category
        var txtTitle = itemView.text_view_book_title!!
        var imgBook = itemView.image_book!!
        var txtPrice = itemView.text_view_book_price!!

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}