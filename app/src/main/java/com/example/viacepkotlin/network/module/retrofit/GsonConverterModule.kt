package com.example.viacepkotlin.network.module.retrofit

import com.example.viacepkotlin.network.Network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/
class GsonConverterModule : Network.RetrofitLevelModule {

    private val factory: GsonConverterFactory = GsonConverterFactory.create()

    override fun onRetrofitBuilderCreated(retrofitBuilder: Retrofit.Builder) {
        //Set the GsonConverterFactory
        retrofitBuilder.addConverterFactory(factory)
    }
}