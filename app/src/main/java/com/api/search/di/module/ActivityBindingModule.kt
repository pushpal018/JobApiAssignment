package com.api.search.di.module

import com.api.search.di.scope.ActivityScope
import com.api.search.ui.view.dashboard.DashBoardActivity
import com.api.search.ui.view.dashboard.DashBoardViewModel


import com.api.search.ui.view.splash.SplashScreenActivity
import com.api.search.ui.view.splash.SplashViewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [FragmentBindingModule::class])
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashViewModule::class])
    abstract fun bindEventSplashActivity(): SplashScreenActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [DashBoardViewModel::class])
    abstract fun bindEventDashBoard(): DashBoardActivity


}