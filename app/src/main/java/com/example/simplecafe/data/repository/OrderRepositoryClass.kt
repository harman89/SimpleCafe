package com.example.simplecafe.data.repository

import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

class OrderRepositoryClass :OrderRepository{
    private var order:OrderData? = null
    override fun getOrder(): OrderData {
        return if (order == null)
            OrderData(User("Guest","Guest"),"none","none")
        else
            this.order!!
    }

    override fun setOrder(order: OrderData?) {
        if(order == null)
            this.order = getOrder()
        else
            this.order=order
    }

}