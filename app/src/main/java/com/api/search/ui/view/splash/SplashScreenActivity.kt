package com.api.search.ui.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View

import com.api.search.base.MvpBaseActivity
import com.api.search.data.prefs.PreferenceManager
import com.api.search.databinding.ActivitySplashScreenBinding
import com.api.search.ui.view.dashboard.DashBoardActivity


class SplashScreenActivity : MvpBaseActivity<SplashPresenter>(), SplashContract.View  {


    val handler = Handler()

    var bundle=Bundle()

    private lateinit var binding: ActivitySplashScreenBinding


    override fun getContentView(): View {

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        return view


    }

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        mPrefManager = PreferenceManager(this)

        Handler().postDelayed({
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }

    override fun onNetworkCallStarted(loadingMessage: String) {
        TODO("Not yet implemented")
    }

    override fun onServerError() {
        TODO("Not yet implemented")
    }

    override fun onTimeOutError() {
        TODO("Not yet implemented")
    }

    override fun onNetworkUnavailable() {
        TODO("Not yet implemented")
    }

    override fun onSystemUpgrading() {
        TODO("Not yet implemented")
    }

    override fun onUserDidTooManyAttempts(errorMsg: String) {
        TODO("Not yet implemented")
    }


}

