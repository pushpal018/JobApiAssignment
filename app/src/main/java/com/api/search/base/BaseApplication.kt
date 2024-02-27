package com.api.search.base


import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.api.search.data.prefs.PrefKeys
import com.api.search.data.prefs.PreferenceManager
import com.api.search.di.component.DaggerAppComponent
import com.api.search.service.sms_retriver.AppSignatureHashHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import javax.inject.Inject


class BaseApplication : Application(), HasActivityInjector, LifecycleObserver {


    companion object {
        var islogindta = false
    }

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>
    private val TAG = BaseApplication::class.java.simpleName

    @Inject
    lateinit var mPrefManager: PreferenceManager
    var sInstance: BaseApplication=this



    override fun onCreate() {
        super.onCreate()
        Log.d("lifecycle",islogindta.toString())
        Log.d("MyApp", "App in first time")
        initDagger()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        genHexStringForSmsRetrieverAPI()
        loadAppLanguage()



    }









    private fun genHexStringForSmsRetrieverAPI(){
        val appSignatureHashHelper = AppSignatureHashHelper(this)
        mPrefManager.putString(PrefKeys.APP_SIGNING_KEY, appSignatureHashHelper.appSignatures[0])
        Log.e(TAG, "SMSHashKey: " + appSignatureHashHelper.appSignatures[0])

    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("lifecycle","onTermination")

    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(this)

    }




    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityInjector
    }

    private fun loadAppLanguage() {
//        val appLang = mPrefManager.getString(Keys.APP_LANGUAGE.name, AppLanguage.ENGLISH.name)
//
//        when (appLang) {
//            AppLanguage.BENGALI.name -> {
//                AppUtils.shared.setLocale(this, AppLanguage.BENGALI)
//                this.getTheme().applyStyle(R.style.AppThemeBangla, true);
//            }
//            AppLanguage.ENGLISH.name -> {
//                AppUtils.shared.setLocale(this, AppLanguage.ENGLISH)
//                this.getTheme().applyStyle(R.style.AppThemeEnglish, true);
//            }
//
//        }
    }
}