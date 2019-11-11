package com.app.bookselling.view.ui.fragment.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.HomeTopSellingModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemCommon
import com.app.bookselling.view.adapter.CommonAdapter
import com.app.bookselling.view.adapter.TopSellingAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import javax.inject.Inject

class HomeTopSellingFragment : BaseFragment() {

    private var mTopSellingArrayList= ArrayList<Book>()

    @Inject
    lateinit var mTopSellingAdapter : TopSellingAdapter

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @BindView(R.id.recycler_view_top_selling)
    @JvmField var rcvTopSelling : RecyclerView? = null


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_top_selling, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            HomeModule(Application.instance.getCurrentFragment() as HomeFragment)
        ).plus(HomeTopSellingModule(this)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.title = "Book Selling Online"

        mTopSellingArrayList.add(Book("Harry Potter","https://www.creativeparamita.com/wp-content/uploads/2018/12/spy-in-the-house.jpg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8a7nxItx2AB4lA--bEOjsMQLscGmxw3wmk28vu5jfysNzb0HEbw","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://www.bookbaby.com/plugins/coverscarousel/images/basic/EverlastingJoy.jpg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://blog-cdn.reedsy.com/directories/gallery/38/large_60b66e669d1d08645dcc69c28d68f027.jpeg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://www.bookbaby.com/plugins/coverscarousel/images/basic/EverlastingJoy.jpg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://blog-cdn.reedsy.com/directories/gallery/38/large_60b66e669d1d08645dcc69c28d68f027.jpeg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://blog-cdn.reedsy.com/directories/gallery/38/large_60b66e669d1d08645dcc69c28d68f027.jpeg","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://vignette.wikia.nocookie.net/wingsoffire/images/7/78/Dragonslayer_Placeholder.jpg/revision/latest?cb=20190507040739","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://about.canva.com/wp-content/uploads/sites/3/2015/01/art_bookcover.png","Ryze", "4.5","100USD"))
        mTopSellingArrayList.add(Book("Harry Potter","https://99designs-blog.imgix.net/blog/wp-content/uploads/2018/12/91lKQ1w00DL.jpg?auto=format&q=60&fit=max&w=930","Ryze", "4.5","100USD"))

        showListOfTopSelling(mTopSellingArrayList)

        rcvTopSelling?.layoutManager = LinearLayoutManager(context)
        rcvTopSelling?.hasFixedSize()
        rcvTopSelling?.adapter = mTopSellingAdapter
    }

    private fun showListOfTopSelling(arrayListOfTopSelling: ArrayList<Book>) {
        mTopSellingAdapter.setList(arrayListOfTopSelling)
    }
}