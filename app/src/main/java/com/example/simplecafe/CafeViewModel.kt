package com.example.simplecafe.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.repository.OrderRepositoryClass

open class CafeViewModel (private val repository : OrderRepositoryClass = OrderRepositoryClass()): ViewModel(){
    private val _orders = MutableLiveData<OrderData>()
    val orders: LiveData<OrderData>
        get() = _orders

    fun getOrder() {
        _orders.value = repository.getOrder()
    }

    fun setOrder(order: OrderData) {
        repository.setOrder(order)
    }
}
