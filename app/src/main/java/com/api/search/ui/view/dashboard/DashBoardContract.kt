package com.api.search.ui.view.dashboard

import com.api.search.base.BaseContract
import com.api.search.data.network.api_response.LoginResponseModel

interface DashBoardContract {

    interface View : BaseContract.View {


        fun noDataFound(message: String)


        fun responseLogin(response: LoginResponseModel)

    }

    interface Presenter : BaseContract.Presenter {


        fun doLogin(phone:String,password:String)
    }
}