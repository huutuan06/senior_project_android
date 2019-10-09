package com.app.bookselling.view.ui.fragment.profile

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.PersonalModule
import com.app.bookselling.utils.Book
import com.app.bookselling.utils.ItemPersonal
import com.app.bookselling.view.adapter.PersonalAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import javax.inject.Inject

class PersonalFragment : BaseFragment() {
    private var mItemPersonalArrayList = ArrayList<ItemPersonal>()


    @Inject lateinit var mAdapter: PersonalAdapter

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: Toolbar

    @BindView(R.id.recycler_view_personal)
    @JvmField var rcvPersonal : RecyclerView? = null

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            PersonalModule(this)
        ).inject(this)
    }

    override fun initAttributes() {

        mItemPersonalArrayList.add(ItemPersonal("https://image.shutterstock.com/image-vector/male-profile-picture-placeholder-vector-260nw-228952291.jpg", "Nguyễn Hữu Tuấn","",""))
        mItemPersonalArrayList.add(ItemPersonal("","","","Manage orders"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The orders have been seen"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The orders have been confirmed"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The orders are being shipped"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The orders successful"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The orders canceled"))
        mItemPersonalArrayList.add(ItemPersonal("","","","The books have been purchased"))
        mItemPersonalArrayList.add(ItemPersonal("","","","My comments"))
        mItemPersonalArrayList.add(ItemPersonal("","","",""))
        showList(mItemPersonalArrayList)

        rcvPersonal?.layoutManager = LinearLayoutManager(context)
        rcvPersonal?.hasFixedSize()
        rcvPersonal?.adapter = mAdapter

        mActivity.setSupportActionBar(mToolbar)
        mToolbar.title = "Personal"
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_item_search).isVisible = false
    }

    fun showList(arr : ArrayList<ItemPersonal>) {
        mAdapter.setList(arr)
    }
}