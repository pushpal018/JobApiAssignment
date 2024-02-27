package com.api.search.ui.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.api.search.base.MvpBaseActivity
import com.api.search.databinding.ActivityDashBoardBinding
import com.api.search.data.network.api_response.LoginResponseModel
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod


class DashBoardActivity : MvpBaseActivity<DashBoardPresenter>(), DashBoardContract.View {
    val handler = Handler()

    private lateinit var binding: ActivityDashBoardBinding

    private var isPinVisible = false
    private val VISIBLE: HideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance()
    private val HIDDEN: PasswordTransformationMethod = PasswordTransformationMethod.getInstance()


    override fun getContentView(): View {
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {


        binding.ivPinVisibility.setOnClickListener {
            isPinVisible = !isPinVisible
            binding.ivPinVisibility.isSelected = isPinVisible
            binding.etPin.transformationMethod = if (isPinVisible) VISIBLE else HIDDEN
            binding.etPin.setSelection(binding.etPin.text!!.length)
        }

       binding.click.setOnClickListener {

           val phone_number = binding.etPhoneNumber.text.toString()
           val password = binding.etPin.text.toString()


           mPresenter.doLogin(phone_number, password)
       }






    }

    override fun noDataFound(message: String) {
        Log.d("error message",message)
    }


    override fun responseLogin(response: LoginResponseModel) {
        Log.d("response",response.data.toString())

        //response.message
    }


}
