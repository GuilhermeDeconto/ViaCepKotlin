package com.example.viacepkotlin.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @Author Guilherme Dall`Agnol Deconto
 * @Since 14/07/20
 **/

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit){
    observe(owner, Observer{
        observer(it)
    })
}

fun EditText.addAfterTextChangedListener(listener: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    })
}

fun TextView.bindTo(property: LiveData<String>, owner: LifecycleOwner = context as LifecycleOwner) {
    property.observe(owner){
        this@bindTo.text = it
    }
}

fun EditText.bindTo(
    property: MutableLiveData<String>,
    owner: LifecycleOwner = context as LifecycleOwner,
    observer: ((String?) -> Unit)? = null
) {
    addAfterTextChangedListener { editable ->
        if (property.value != editable.toString()) {
            property.value = editable.toString()
            observer?.invoke(property.value)
        }
    }
    property.observe(owner) { value ->
        if (property.value != this.text.toString()) {
            setText(value)
            observer?.invoke(value)
        }
    }
}