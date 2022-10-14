package com.example.simplecafe.data.model

import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.repository.UserRepositoryClass

class UserModel (private val repository: UserRepositoryClass): SimpleCafeMVP.UserModel {
    override fun setUser(login: String, password: String) {
        repository.setUser(User(login, password))
    }

    override fun getUser(): User {
        return repository.getUser()
    }

}