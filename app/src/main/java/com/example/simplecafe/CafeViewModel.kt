package com.example.simplecafe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecafe.data.model.OrderData

open class CafeViewModel : ViewModel(){
    val orderData : MutableLiveData<OrderData> by lazy{
        MutableLiveData<OrderData>()
    }
}