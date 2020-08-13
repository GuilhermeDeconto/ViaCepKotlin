package com.example.viacepkotlin.scenes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.viacepkotlin.model.Cep
import com.example.viacepkotlin.service.CepService

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class MainActivityViewModel(): ViewModel() {

    private val service: CepService = CepService()

    val etCep = MutableLiveData<String>()
    var cep = MutableLiveData<Cep>()

    val tvInfo: LiveData<String> = Transformations.map(cep) {
        return@map cep.value?.logradouro ?: "é us guri deu erro"
    }

    fun doSearch(callback: (Boolean, String?) -> Unit){
        service.getCep(etCep.value ?: "").then { response ->
                if (response != null) {
                    cep.value = response
                }
                callback(true, "Success")
        }.catch { error ->
            callback(false, "error")
        }
    }

}