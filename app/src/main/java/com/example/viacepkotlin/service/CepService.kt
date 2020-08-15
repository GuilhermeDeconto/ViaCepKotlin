package com.example.viacepkotlin.service

import com.example.viacepkotlin.model.Cep
import mezzari.torres.lucas.network.source.Network
import mezzari.torres.lucas.network.source.promise.NetworkPromise


/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 15/07/20
 **/
class CepService {
    private val api: ICepAPI = Network.build()

    fun getCep(cep: String): NetworkPromise<Cep> {
        return NetworkPromise {
            api.getCep(cep).enqueue(this)
        }
    }

}