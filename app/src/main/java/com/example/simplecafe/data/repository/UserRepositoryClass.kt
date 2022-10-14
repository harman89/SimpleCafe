package com.example.simplecafe.data.repository

import com.example.simplecafe.data.model.User

class UserRepositoryClass : UserRepository {
    private var user : User? = null
    override fun getUser(): User {
        return if(user == null)
            User("Guest","Guest")
        else this.user!!
    }

    override fun setUser(user: User?) {
        if(user == null)
            this.user = getUser()
        else
            this.user=user
    }

}