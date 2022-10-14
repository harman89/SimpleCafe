package com.example.simplecafe

import androidx.fragment.app.Fragment
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

interface SimpleCafeMVP {
    interface View{
        fun getLogin(): String
        fun getPassword(): String
        fun setLogin(value:String)
        fun setPassword(value: String)
    }
    interface Presenter{
        fun setView(view: View)
        fun buttonClicked()
        fun getCurrentUser(): User
        fun setCurrentUser()
        fun changeFragment(fragmentOld : Fragment, fragmentNew: Fragment)
        fun setOldFragment(fragment: Fragment)
        fun setNewFragment(fragment: Fragment)
    }
    interface UserModel{
        fun setUser(login: String, password: String)
        fun getUser() : User
    }
    interface OrderModel{
        fun setOrder(user: User, order : String, additions : String)
        fun getOrder() : OrderData
    }
}