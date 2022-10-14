package com.example.simplecafe.data.repository

import com.example.simplecafe.data.model.OrderData

interface OrderRepository {
    fun getOrder(): OrderData
    fun setOrder(order: OrderData?)
}