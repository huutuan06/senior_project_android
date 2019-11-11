package com.app.bookselling.view.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.utils.Book
import com.app.bookselling.view.adapter.CollectionAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BookCollectionFragment : BaseFragment() {

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mTabLayout: LinearLayout

    private var mBookArrayList = ArrayList<Book>()

    private var mCollectionAdapter = CollectionAdapter(mBookArrayList)

    @BindView(R.id.recycler_view_collection)
    @JvmField var rcvCollection : RecyclerView? = null


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_book_collection, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = arguments?.getString("title")
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE
        mTabLayout.visibility = View.GONE

        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81WWiiLgEyL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81h2gWPTYJL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81XR45UdqkL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/91ibhD5nhUL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/71LVKXutJmL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/91rhzcPiXAL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/41ILRrgp5-L._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/91HHxxtA1wL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81HCcHPXZnL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81dKveFv2%2BL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/81ThXlXJqgL._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://images-na.ssl-images-amazon.com/images/I/71QKQ9mwV7L._AC_UL200_SR200,200_.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8a7nxItx2AB4lA--bEOjsMQLscGmxw3wmk28vu5jfysNzb0HEbw","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://www.bookbaby.com/plugins/coverscarousel/images/basic/EverlastingJoy.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://blog-cdn.reedsy.com/directories/gallery/38/large_60b66e669d1d08645dcc69c28d68f027.jpeg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://vignette.wikia.nocookie.net/wingsoffire/images/7/78/Dragonslayer_Placeholder.jpg/revision/latest?cb=20190507040739","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://about.canva.com/wp-content/uploads/sites/3/2015/01/art_bookcover.png","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://99designs-blog.imgix.net/blog/wp-content/uploads/2018/12/91lKQ1w00DL.jpg?auto=format&q=60&fit=max&w=930","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://www.creativeparamita.com/wp-content/uploads/2018/12/spy-in-the-house.jpg","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8a7nxItx2AB4lA--bEOjsMQLscGmxw3wmk28vu5jfysNzb0HEbw","100 USD"))
        mBookArrayList.add(Book("Harry Potter","https://www.bookbaby.com/plugins/coverscarousel/images/basic/EverlastingJoy.jpg","100 USD"))
        setList(mBookArrayList)

        rcvCollection!!.layoutManager = GridLayoutManager(context, 3)
        rcvCollection?.hasFixedSize()
        rcvCollection!!.adapter = mCollectionAdapter

//        mCollectionAdapter.setInterface(this)
    }
    private fun setList(arr: ArrayList<Book>) {
        mCollectionAdapter.setList(arr)
    }
}