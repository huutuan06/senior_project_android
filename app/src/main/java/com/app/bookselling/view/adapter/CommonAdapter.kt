package com.app.bookselling.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bookselling.R
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import kotlinx.android.synthetic.main.item_home_common.view.*

class CommonAdapter(private var context: Context, private var commonList: ArrayList<ItemCommon>) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    private var mCategoryArrayList= ArrayList<Book>()
    private var mCategoryAdapter = CategoryAdapter(context, mCategoryArrayList)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCategory.text = commonList[position].title

        mCategoryArrayList.add(Book("Harry Potter","https://blog-cdn.reedsy.com/directories/gallery/38/large_60b66e669d1d08645dcc69c28d68f027.jpeg","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://vignette.wikia.nocookie.net/wingsoffire/images/7/78/Dragonslayer_Placeholder.jpg/revision/latest?cb=20190507040739","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://about.canva.com/wp-content/uploads/sites/3/2015/01/art_bookcover.png","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://99designs-blog.imgix.net/blog/wp-content/uploads/2018/12/91lKQ1w00DL.jpg?auto=format&q=60&fit=max&w=930","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://www.creativeparamita.com/wp-content/uploads/2018/12/spy-in-the-house.jpg","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8a7nxItx2AB4lA--bEOjsMQLscGmxw3wmk28vu5jfysNzb0HEbw","100 USD"))
        mCategoryArrayList.add(Book("Harry Potter","https://www.bookbaby.com/plugins/coverscarousel/images/basic/EverlastingJoy.jpg","100 USD"))
        showListOfCategory(mCategoryArrayList)
        holder.rcvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rcvCategory.adapter = mCategoryAdapter
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
        var txtCategory: TextView = itemView.text_view_category
        var rcvCategory: RecyclerView = itemView.recycler_view_category
    }

    fun setList(arr: ArrayList<ItemCommon>) {
        commonList = arr
        notifyDataSetChanged()
    }

    private fun showListOfCategory(arrayListOfCategory: ArrayList<Book>) {
        mCategoryAdapter.setList(arrayListOfCategory)
    }
}