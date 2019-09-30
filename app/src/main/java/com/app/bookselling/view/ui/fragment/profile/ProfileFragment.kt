package com.app.bookselling.view.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.ProfileModule
import com.app.bookselling.utils.ItemManageOrders
import com.app.bookselling.view.adapter.ManageOrderAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject lateinit var mAdapter: ManageOrderAdapter

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: Toolbar

    @BindView(R.id.recycler_view_personal)
    @JvmField var rcvPersonal : RecyclerView? = null

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
            ProfileModule(this)
        ).inject(this)
    }

    override fun initAttributes() {
        rcvPersonal?.layoutManager = LinearLayoutManager(context)
        rcvPersonal?.hasFixedSize()
        rcvPersonal?.adapter = mAdapter

        mActivity.setSupportActionBar(mToolbar)
        mToolbar.title = "Personal"
    }

    fun showList(arr : ArrayList<ItemManageOrders>) {
        mAdapter.setList(arr)
    }
}