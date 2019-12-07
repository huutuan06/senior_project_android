package com.app.vogobook.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import butterknife.BindView
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class AccountFragment : BaseFragment() {


    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @BindView(R.id.edit_text_name)
    lateinit var edtName: EditText

//    @BindView(R.id.edit_text_name)
//    lateinit var : EditText

    @BindView(R.id.edit_text_address)
    lateinit var edtAddress: EditText

    @BindView(R.id.radioButton_male)
    lateinit var rbMale: RadioButton

    @BindView(R.id.radioButton_female)
    lateinit var rbFeMale: RadioButton


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = "Account"
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE

        edtName.setText(mActivity.user.name.toString())
        if (edtAddress.text.isNotEmpty())
            edtAddress.setText(mActivity.user.address.toString())
        when {
            mActivity.user.gender.toString() == "0" -> {
                rbMale.isChecked = true
                rbFeMale.isChecked = false
            }
            mActivity.user.gender.toString() == "1" -> {
                rbMale.isChecked = false
                rbFeMale.isChecked = true
            }
            else -> {
                rbMale.isChecked = false
                rbFeMale.isChecked = false
            }
        }


    }

}