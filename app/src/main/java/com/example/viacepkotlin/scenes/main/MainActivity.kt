package com.example.viacepkotlin.scenes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.viacepkotlin.R
import com.example.viacepkotlin.extensions.bindTo
import com.example.viacepkotlin.extensions.observe
import com.example.viacepkotlin.scenes.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    fun initComponents(){
        etCep.bindTo(viewModel.etCep)
        tvInfos.bindTo(viewModel.tvInfo)
        viewModel.cep.observe(this) {}

        btnService.setOnClickListener {
            viewModel.doSearch { response, error ->
                if (response){
                    Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Deu ruim", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val viewModel: MainActivityViewModel by lazy {
        initViewModelProvider<MainActivityViewModel>()
    }



}