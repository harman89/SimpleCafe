package com.example.simplecafe.data.repository

import com.example.simplecafe.data.model.User

interface UserRepository {
    fun getUser():User
    fun setUser(user: User?)
}