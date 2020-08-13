package com.example.viacepkotlin.service

import com.example.viacepkotlin.model.Cep
import com.example.viacepkotlin.network.Network
import com.example.viacepkotlin.network.promise.NetworkPromise

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 15/07/20
 **/
class CepService {
    private val api: ICepAPI = Network.build()

    fun getCep(cep: String): NetworkPromise<Cep>{
        return NetworkPromise {
            api.getCep(cep).enqueue(this)
        }
    }

}