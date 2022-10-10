package com.example.simplecafe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CafeViewModel : ViewModel(){
    val orderData : MutableLiveData<OrderData> by lazy{
        MutableLiveData<OrderData>()
    }
}