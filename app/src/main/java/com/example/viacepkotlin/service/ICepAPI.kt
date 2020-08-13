package com.example.viacepkotlin.service

import com.example.viacepkotlin.BuildConfig
import com.example.viacepkotlin.model.Cep
import com.example.viacepkotlin.network.annotation.Route
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

@Route(BuildConfig.BASE_URL)
interface ICepAPI {
    @GET("{cep}/json/")
    fun getCep(
        @Path("cep") cep: String
    ): Call<Cep>

}