package com.app.vogobook.view.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.app.vogobook.R
import kotlinx.android.synthetic.main.dialog_vogo.*


class VogoDialog : DialogFragment() {

    @BindView(R.id.tvTitle)
    @JvmField var tvTitle: TextView? = null

    @BindView(R.id.tvDesc)
    @JvmField var tvDesc: TextView? = null

    @BindView(R.id.btnCancle)
    @JvmField var btnCancel: Button? = null


    private var mUnbinder: Unbinder? = null
    private var mErrorTitle: String? = null
    private var mErrorDesc: String? = null
    private var mContext: Context? = null

    override fun onStart() {
        super.onStart()
        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.dialog_vogo, container)
        mUnbinder = ButterKnife.bind(this, view)
        tvTitle!!.text = mErrorTitle
        tvDesc!!.text = mErrorDesc
        if (TextUtils.equals(mContext!!.getString(R.string.submit_review_successfully), mErrorDesc) || TextUtils.equals(mContext!!.getString(R.string.submit_review_successfully), mErrorDesc) ||
                TextUtils.equals(mContext!!.getString(R.string.cannot_process_request), mErrorDesc)) {
            btnCancel!!.visibility = View.GONE
        } else {
            btnCancel!!.visibility = View.VISIBLE
        }
        return view
    }

    override fun onDestroyView() {
        if (mUnbinder != null) mUnbinder!!.unbind()
        super.onDestroyView()
    }

    fun setListener(listener: IListener?) {
        mListener = listener
    }

    private var mListener: IListener? = null

    interface IListener {
        fun doYourAction()
        fun dimiss()
    }

    public fun updateMessageDialog(
        context: Context?,
        errorTitle: String?,
        errorMessage: String?
    ) {
        mContext = context
        mErrorTitle = errorTitle
        mErrorDesc = errorMessage
    }

    @OnClick(R.id.btnOk, R.id.btnCancle)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.btnOk -> {
                mListener!!.doYourAction()
                dismiss()
            }
            R.id.btnCancle -> {
                mListener!!.dimiss()
                dismiss()
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }
}