package com.app.vogobook.view.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book_collection.view.*
import kotlinx.android.synthetic.main.item_home_common_category.view.*
import kotlinx.android.synthetic.main.item_home_common_category.view.fragment_item_category
import kotlinx.android.synthetic.main.item_home_common_category.view.image_book
import kotlinx.android.synthetic.main.item_home_common_category.view.text_view_book_price
import kotlinx.android.synthetic.main.item_home_common_category.view.text_view_book_title

class CollectionAdapter(private var bookList: ArrayList<Book>) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private lateinit var mCollectionEventListener: ColectionEventListener

    interface ColectionEventListener {
        fun navigateToBookDetail(book: Book)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = bookList[position].title
        holder.txtPrice.text = "$" + bookList[position].price.toString()

        Picasso.get().load(bookList[position].image).resize(Resources.getSystem().displayMetrics.widthPixels *4/15, Resources.getSystem().displayMetrics.widthPixels *4/15*3/2)
            .centerCrop().into(holder.imgBook)

        holder.item.setOnClickListener {
            mCollectionEventListener.navigateToBookDetail(bookList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book_collection,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle = itemView.text_view_book_title!!
        var imgBook = itemView.image_book!!
        var txtPrice = itemView.text_view_book_price!!
        var item = itemView.fragment_item_category!!

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    fun setList(arr: ArrayList<Book>) {
        bookList = arr
        notifyDataSetChanged()
    }

    fun setInterface(listener: ColectionEventListener) {
        mCollectionEventListener = listener
    }
}