package com.example.viacepkotlin.network.auth

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class NoAuth : BaseAuth() {
    override fun onAuthenticationFailed() {
        //Nothing will be done here
    }

    override fun <T> doAuthentication(call: Call<T>, callback: Callback<T>) {
        //Nothing will be done here
    }

    override fun <T> isAuthenticated(response: Response<T>): Boolean {
        return true //It will always be authenticated
    }
}