package com.api.search.di.module

import android.content.Context
import com.api.search.base.BaseApplication
import com.api.search.data.prefs.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.api.search.rx.AppSchedulerProvider
import com.api.search.utils.*

@Module
class ApplicationModule {

    @Provides
    fun provideContext(baseApp: BaseApplication): Context {
        return baseApp
    }

    @Provides
    @Singleton
    fun providePreferenceManager(context: Context): PreferenceManager {
        return PreferenceManager(context)
    }

    @Provides
    @Singleton
    fun providePermissionUtils(preferenceManager: PreferenceManager): PermissionUtils {
        return PermissionUtils(preferenceManager)
    }

    @Provides
    @Singleton
    fun provideAlertService(): MyAlertService {
        return MyAlertService()
    }

    @Provides
    @Singleton
    fun provideAppSchedule(): AppSchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }

    @Provides
    @Singleton
    fun provideAppLogger(): AppLogger {
        return AppLogger()
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtils()
    }
}