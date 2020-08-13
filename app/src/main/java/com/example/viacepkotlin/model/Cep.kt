package com.example.viacepkotlin.model

import com.google.gson.annotations.SerializedName


/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 15/07/20
 **/
class Cep (
    @SerializedName("cep") var cep: String? = null,

    @SerializedName("logradouro") var logradouro: String? = null,

    @SerializedName("complemento") var complemento: String? = null,

    @SerializedName("bairro" )var bairro: String? = null,

    @SerializedName("localidade") var localidade: String? = null,

    @SerializedName("uf") var uf: String? = null,

    @SerializedName("unidade") var unidade: String? = null,

    @SerializedName("ibge") var ibge: String? = null,

    @SerializedName("gia") var gia: String? = null
)


