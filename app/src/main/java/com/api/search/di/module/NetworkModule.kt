package com.api.search.di.module

import android.content.Context
import com.api.search.data.network.ApiServiceBuilder
import com.api.search.data.network.RetrofitApiClient
import com.api.search.data.network.api_service.LoginApiService
import com.api.search.data.prefs.PreferenceManager
import com.api.search.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitApiClient(
        context: Context,
        networkUtils: NetworkUtils,
        preferenceManager: PreferenceManager
    ): Retrofit {
        return RetrofitApiClient.getRetrofit(context, networkUtils, preferenceManager)
    }


    @Provides
    @Singleton
    fun provideApiServiceBuilder(retrofit: Retrofit): ApiServiceBuilder {
        return ApiServiceBuilder(retrofit)
    }


    @Provides
    @Singleton
    fun provideLoginApiService(apiServiceBuilder: ApiServiceBuilder): LoginApiService {
        return apiServiceBuilder.buildService(LoginApiService::class.java)
    }


}