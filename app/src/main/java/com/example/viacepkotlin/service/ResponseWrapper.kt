package com.example.viacepkotlin.service

import com.google.gson.annotations.SerializedName

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 15/07/20
 **/
class ResponseWrapper<T>(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: T?
)