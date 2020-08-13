package com.example.viacepkotlin.network.module.client

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.viacepkotlin.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class ConnectionModule(
    context: Context,
    callback: () -> Unit
): Network.OkHttpClientLevelModule {

    private val interceptor = Interceptor {
        if (!context.isConnectedToInternet()) {
            CoroutineScope(Dispatchers.Main).launch {
                callback()
            }

            throw NoInternetException()
        }
        return@Interceptor it.proceed(it.request())
    }

    override fun onClientBuilderCreated(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.addInterceptor(interceptor)
    }

    fun Context.isConnectedToInternet(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager ?: return false
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null
        } else {
            connectivityManager.activeNetworkInfo != null
        }
    }

    class NoInternetException: IOException("Internet disconnected")

    companion object {
        val ACTION = ConnectionModule::class.java.name
    }
}