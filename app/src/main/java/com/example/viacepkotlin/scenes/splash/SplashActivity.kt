package com.example.viacepkotlin.scenes.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.viacepkotlin.R
import com.example.viacepkotlin.scenes.main.MainActivity

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        Handler().postDelayed({
            startActivity(intent)
        },2000)
    }

}