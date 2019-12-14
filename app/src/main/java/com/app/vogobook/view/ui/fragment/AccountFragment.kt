package com.app.vogobook.view.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.AccountModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.presenter.AccountPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.custom.CircleTransform
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.AccountView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_account.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.xml.datatype.DatatypeConstants.MONTHS

class AccountFragment : BaseFragment(), AccountView {


    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mPresenter: AccountPresenter

    @BindView(R.id.image_view_avatar)
    lateinit var imgAvatar: ImageView

    @BindView(R.id.edit_text_name)
    lateinit var edtName: EditText

    @BindView(R.id.image_view_birthday)
    lateinit var imgBirthDay: ImageView

    @BindView(R.id.edit_text_birthday)
    lateinit var edtBirthday: EditText

    @BindView(R.id.edit_text_address)
    lateinit var edtAddress: EditText

    @BindView(R.id.radioButton_male)
    lateinit var rbMale: RadioButton

    @BindView(R.id.radioButton_female)
    lateinit var rbFeMale: RadioButton

    @BindView(R.id.button_submit)
    lateinit var btnSubmit: Button

    var pathImage: String? = null
    val mCalendar = Calendar.getInstance()


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(AccountModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = "Account"
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE

        Picasso.get().load(mActivity.user.avatar).transform(CircleTransform()).into(imgAvatar)
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

    @OnClick(R.id.image_view_avatar, R.id.image_view_birthday, R.id.button_submit)
    fun Click(view: View) {
        when (view.id) {
            R.id.image_view_avatar -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        val mPermisstion = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(mPermisstion, Constants.PERMISSION_CODE)
                    } else {
                        pickImageFromGallary()
                    }
                } else {
                    pickImageFromGallary()
                }
            }
            R.id.image_view_birthday -> {
                initDatePicker()
            }
            R.id.button_submit -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("image", pathImage)
                jsonObject.addProperty("name", edtName.text.toString())
                jsonObject.addProperty("date_of_birth", mCalendar.time.time/1000)
                jsonObject.addProperty("address", edtAddress.text.toString())
                if (rbMale.isChecked)
                    jsonObject.addProperty("gender", 0)
                else
                    jsonObject.addProperty("gender", 1)
                mPresenter.editAccount(jsonObject)
            }
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun initDatePicker() {
        val year = mCalendar.get(Calendar.YEAR)
        val month = mCalendar.get(Calendar.MONTH)
        val day = mCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                val thisMonth = monthOfYear + 1
                val simpleDateFormat = SimpleDateFormat(context!!.getString(R.string.partten_birthday_local))
                mCalendar.set(year, monthOfYear, dayOfMonth)
                edtBirthday.setText(simpleDateFormat.format(mCalendar.time.time))
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    private fun pickImageFromGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            Constants.PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallary()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.IMAGE_PICK_CODE) {
            imgAvatar.setImageURI(data?.data)
            pathImage = data?.data.toString()
        }
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        //TODO
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        //TODO
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }

}