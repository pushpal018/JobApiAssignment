package com.api.search.ui.view.dashboard

import android.util.Log
import com.api.search.R
import com.api.search.base.BasePresenter
import com.api.search.data.network.SSDisposableSingleObserver
import com.api.search.data.network.api_response.LoginResponseModel
import com.api.search.data.network.api_service.LoginApiService
import javax.inject.Inject

class DashBoardPresenter
@Inject constructor(view: DashBoardContract.View) : BasePresenter<DashBoardContract.View>(view),
    DashBoardContract.Presenter
    {
        @Inject

        lateinit var loginApiService: LoginApiService




        override fun doLogin(phone:String,password:String) {
            if (this::loginApiService.isInitialized) {
                mView?.onNetworkCallStarted(context.getString(R.string.please_wait))
                compositeDisposable?.add(
                    loginApiService.loginn(phone,password)
                        .subscribeOn(appSchedulerProvider.io())
                        .unsubscribeOn(appSchedulerProvider.computation())
                        .observeOn(appSchedulerProvider.ui())
                        .subscribeWith(object :
                            SSDisposableSingleObserver<LoginResponseModel, DashBoardContract.View>(mView) {
                            override fun onRequestSuccess(response: LoginResponseModel) {
                                mView?.responseLogin(response)

                            }

                            override fun onSuccess(t: LoginResponseModel) {
                                mView?.responseLogin(t)
                            }

                            override fun onError(throwable: Throwable) {
                                super.onError(throwable)
                                Log.d("error",throwable.message.toString())
                                mView?.noDataFound(throwable.message.toString())
                            }

                        })
                )
            }
        }


    }
