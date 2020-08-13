package com.example.viacepkotlin.network.module.client

import com.example.viacepkotlin.network.Network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class LogModule : Network.OkHttpClientLevelModule {
    //Instantiate the logging interceptor
    private val loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        //Set it's level to body (shows everything)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    override fun onClientBuilderCreated(okHttpClientBuilder: OkHttpClient.Builder) {
        //Add the interceptor to the client
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
}