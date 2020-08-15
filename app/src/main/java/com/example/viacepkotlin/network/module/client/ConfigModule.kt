package com.example.viacepkotlin.network.module.client

import mezzari.torres.lucas.network.source.Network
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 15/07/20
 **/
class ConfigModule: Network.OkHttpClientLevelModule {

    private val interceptor: Interceptor = Interceptor {
        val request = it.request().newBuilder().apply {
//            addHeader("Authorization", "Bearer ${""SessionManager.accessToken""}"
        }.build()
        return@Interceptor it.proceed(request)
    }

    override fun onClientBuilderCreated(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(120, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(interceptor)
    }
}