package com.example.simplecafe.data.model

import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.repository.OrderRepositoryClass

class OrderModel(private val repository : OrderRepositoryClass):SimpleCafeMVP.OrderModel {
    override fun setOrder(user: User, order: String?, additions: String?) {
        repository.setOrder(OrderData(user,order,additions))
    }

    override fun getOrder(): OrderData {
        return repository.getOrder()
    }
}