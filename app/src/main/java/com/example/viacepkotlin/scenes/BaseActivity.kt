package com.example.viacepkotlin.scenes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viacepkotlin.network.module.client.ConnectionModule


/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/


abstract class BaseActivity: AppCompatActivity() {

    private val connectionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.let {
                onInternetDisconnected(context)
            }
            abortBroadcast()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(connectionReceiver, IntentFilter(ConnectionModule.ACTION))
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectionReceiver)
    }


    open fun onInternetDisconnected(context: Context) {
        AlertDialog.Builder(this)
            .setTitle("Atenção")
            .setMessage("Sem conexão de internet")
            .setPositiveButton("OK", null)
            .show()
    }

    protected inline fun <reified VM : ViewModel> initViewModelProvider(): VM {
        return ViewModelProvider(this)[VM::class.java]
        //return ViewModelProviders.of(this)[VM::class.java]
    }
}