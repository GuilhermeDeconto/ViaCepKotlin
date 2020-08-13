package com.example.viacepkotlin
import android.app.Application
import android.content.Intent
import com.example.viacepkotlin.network.Network
import com.example.viacepkotlin.network.auth.NoAuth
import com.example.viacepkotlin.network.module.client.ConfigModule
import com.example.viacepkotlin.network.module.client.ConnectionModule
import com.example.viacepkotlin.network.module.client.LogModule
import com.example.viacepkotlin.network.module.interceptor.ConnectionInterceptor
import com.example.viacepkotlin.network.module.retrofit.GsonConverterModule
import com.example.viacepkotlin.network.promise.BaseNetworkPromise
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException
import java.util.*

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Network.initialize(
            retrofitLevelModules = Collections.singletonList(GsonConverterModule()),
            auth = NoAuth(),
            okHttpClientLevelModule = arrayListOf(
                ConnectionModule (this) {
                    sendOrderedBroadcast(Intent(ConnectionModule.ACTION), null)
                },
                ConfigModule(),
                LogModule()
            ),
            responseInterceptors = listOf(ConnectionFailed(), ConnectionInterceptor())
        )

    }

    class ConnectionFailed: Network.ResponseInterceptor {
        override fun <T> onFailure(
            call: Call<T>,
            t: Throwable,
            promise: BaseNetworkPromise<T>
        ): Boolean {
            if (t is UnknownHostException) {
                promise.failureCallback?.invoke(promise, "You are offline")
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

}