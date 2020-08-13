package com.example.viacepkotlin.network.module.interceptor

import com.example.viacepkotlin.network.Network
import com.example.viacepkotlin.network.module.client.ConnectionModule
import com.example.viacepkotlin.network.promise.BaseNetworkPromise
import retrofit2.Call
import retrofit2.Response

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class ConnectionInterceptor: Network.ResponseInterceptor {
    override fun <T> onFailure(
        call: Call<T>,
        t: Throwable,
        promise: BaseNetworkPromise<T>
    ): Boolean {
        if (t is ConnectionModule.NoInternetException) {
            promise.failureCallback?.invoke(promise, null)
            return true
        }
        return false
    }

    override fun <T> onResponse(
        call: Call<T>,
        response: Response<T>,
        promise: BaseNetworkPromise<T>
    ): Boolean {
        return false
    }
}