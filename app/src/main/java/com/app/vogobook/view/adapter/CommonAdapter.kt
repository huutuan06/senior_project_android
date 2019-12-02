package com.app.vogobook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import kotlinx.android.synthetic.main.item_home_common.view.*

class CommonAdapter(private var context: Context, private var commonList: ArrayList<Category>) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>(), CategoryAdapter.CategoryEventListener {

    private lateinit var mCommonEventListener : CommonEventListener

    override fun navigateToBookDetail(book: Book) {
          mCommonEventListener.navigateToBookDetail(book)
    }

    interface CommonEventListener {
        fun navigateToBookDetail(book: Book)
        fun navigateToBookCollection(title: String)
    }


    fun setInterface(listener: CommonEventListener) {
        mCommonEventListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = commonList[position].name
        holder.rcvBooks.adapter = CategoryAdapter(context,
            commonList[position].arrBooks as ArrayList<Book>, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_home_common,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return commonList.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.tv_category_name
        var rcvBooks: RecyclerView = itemView.rcv_books
        init {
            rcvBooks.layoutManager =  LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun setList(arr: ArrayList<Category>) {
        commonList = arr
        notifyDataSetChanged()
    }
}