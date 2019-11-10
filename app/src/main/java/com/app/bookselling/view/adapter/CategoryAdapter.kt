 package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_common_category.view.*

class CategoryAdapter(private var context: Context, private var categoriesList: ArrayList<Book>, private var mCategoryEventListener: CategoryEventListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = categoriesList[position].title
        holder.txtPrice.text = categoriesList[position].price

        Picasso.get().load(categoriesList[position].image).resize(180,  270)
            .centerCrop().into(holder.imgBook)

        holder.mItemBook.setOnClickListener({
             mCategoryEventListener.navigateToBookDetail(categoriesList.get(position))
        })
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
        // You can pass object Book to method
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

    fun setList(arr: ArrayList<Book>) {
        categoriesList = arr
        notifyDataSetChanged()
    }
}