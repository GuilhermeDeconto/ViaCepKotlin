package com.example.viacepkotlin.network.promise

import com.example.viacepkotlin.network.Network
import retrofit2.Call
import retrofit2.Response

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/
open class NetworkPromise<T>(delegate: BaseNetworkPromise<T>.() -> Unit): BaseNetworkPromise<T>(delegate) {
    override fun onFailure(call: Call<T>, t: Throwable) {
        super.onFailure(call, t)
        for (interceptor in Network.responseInterceptors) {
            if (interceptor.onFailure(call, t, this)) {
                return
            }
        }
        this.failureCallback?.invoke(this, t.message)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        super.onResponse(call, response)
        for (interceptor in Network.responseInterceptors) {
            if (interceptor.onResponse(call, response, this)) {
                return
            }
        }

        if (response.isSuccessful) {
            this.successCallback?.invoke(this, response.body())
        } else if (!this.auth.isAuthenticated(response)) {
            this.auth.onUnauthorized(call, this)
        } else {
            this.failureCallback?.invoke(this, response.message())
        }
    }
}