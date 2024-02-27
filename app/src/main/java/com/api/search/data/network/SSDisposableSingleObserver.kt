package com.api.search.data.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.api.search.base.BaseContract
import com.api.search.utils.AppLogger
import io.reactivex.observers.DisposableSingleObserver
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.LinkedHashMap
import javax.net.ssl.SSLHandshakeException


abstract class SSDisposableSingleObserver<R : Any, out V : BaseContract.View>(private val mView: V?) : DisposableSingleObserver<R>() {


    abstract fun onRequestSuccess(response: R)

    override fun onSuccess(response: R) {
        mView?.onNetworkCallEnded()
        Log.d("response",response.toString())
        onRequestSuccess(response)
    }

    override fun onStart() {
        super.onStart()
    }



    override fun onError(throwable: Throwable) {
        var logger = AppLogger()
        mView?.onNetworkCallEnded()
        Log.d("onErrorCode","");
        Log.d("response",throwable.toString())
        when (throwable) {
            is NoConnectivityException -> mView?.onNetworkUnavailable()
            is HttpException -> {
                val errorCode = throwable.code()
                val res = throwable.response()
                logger.logE("Errorcode-${errorCode}")
                when (errorCode) {

                    401 -> {

                        try {
                            val rootObj = JSONObject(res!!.errorBody()!!.string())
                            val errorMsg = rootObj.getString("message")
                            logger.logE("${errorCode}-${errorMsg}")
                            var map=LinkedHashMap<String, String>()
                            map.put("message",errorMsg)
                            if(errorMsg.equals("Unauthenticated.")){
                                mView?.onUserUnauthorized("Not Found")
                            }else{
                                mView?.onValidationFailed(map)
                            }

                        } catch (exception: Exception) {
                            logger.logE(exception.toString())
                        }
                    }       // 401 = USER UNAUTHORIZED
//                    403 -> mView?.applyForcePinReset()
                    403 -> {

                        try {

                            val jsonObject = JSONObject(res!!.errorBody()!!.string())
                            val jsonObject2 = jsonObject.getJSONObject("error")





                            if (jsonObject2.has("password")){
                                Log.d("Login Failed",jsonObject2.getJSONArray("password").getString(0).toString())
                                mView?.onUserDisabled(jsonObject2.getJSONArray("password").getString(0).toString())
                            }
                            if (jsonObject2.has("phone")){
                                Log.d("Login Failed",jsonObject2.getJSONArray("phone").getString(0).toString())
                                mView?.onUserDisabled(jsonObject2.getJSONArray("phone").getString(0).toString())
                            }


                            //Log.d("Login Failed","Login Failed Object"+jsonObject2.toString())



                        } catch (exception: Exception) {
                            logger.logE(exception.toString())
                            Log.d("errorrrr",exception.toString())
                        }
                    }
                    404 -> mView?.onUserUnauthorized("Not Found")// 404 = NOT FOUND
                    417 -> {
                        try {
                            val rootObj = JSONObject(res!!.errorBody()!!.string())
                            val message = rootObj.getString("message")
                            mView?.onExpectationFailed(message)
                        } catch (exception: Exception) {
                        }
                    }// 417 = Expectation Failed
                    422 -> { // 422 = FORM DATA NOT VALID
                        try {

                            val rootObj = JSONObject(res!!.errorBody()!!.string())
                            val errors = rootObj.getJSONObject("data")
                            val errorMap = Gson().fromJson<LinkedHashMap<String, String>>(errors.toString(), object : TypeToken<LinkedHashMap<String, String>>() {}.type)
                            logger.logE(errorMap.toString())
                            mView?.onValidationFailed(errorMap)
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }
                    }
                    423 -> {
                        // 423 = User locked
                        Log.d("my app code","")
                        Log.d("code","423")
                        try {
                            val rootObj = JSONObject(res!!.errorBody()!!.string())
                            val message = rootObj.getString("message")
                            mView?.onUserDisabled(message)

                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }
                    }
                    429 -> mView?.onUserDidTooManyAttempts("Too many Requests")
                    500 -> mView?.onServerError()
                    503 -> mView?.onSystemUpgrading()
                }
            }

            is SocketTimeoutException, is UnknownHostException -> mView?.onServerError()
            is SSLHandshakeException, is ConnectException -> mView?.onTimeOutError()







        }
    }
}


