package com.api.search.ui.view.splash

import dagger.Binds
import dagger.Module

@Module
abstract class SplashViewModule {
    @Binds
    abstract fun provideSplashView(activity: SplashScreenActivity): SplashContract.View
}