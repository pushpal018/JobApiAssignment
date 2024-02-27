package com.api.search.data.network.api_service

import com.api.search.data.network.api_response.LoginResponseModel
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {


    @POST("login")
    fun loginn(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Single<LoginResponseModel>

}