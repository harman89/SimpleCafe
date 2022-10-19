package com.example.simplecafe

import androidx.fragment.app.Fragment
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

interface SimpleCafeMVP {
    interface AuthorizationView{
        fun getLogin(): String
        fun getPassword(): String
        fun setLogin(value:String?)
        fun setPassword(value: String?)
    }
    interface OrderView {
        fun getTea():String
        fun getCoffee():String
        //fun getType(): String
        fun getLemon():String
        fun getMilk():String
        fun getSugar():String
    }
    interface Presenter{
        fun buttonClicked()
        fun changeFragment(fragmentOld : Fragment, fragmentNew: Fragment)
        fun setOldFragment(fragment: Fragment)
        fun setNewFragment(fragment: Fragment)
    }
    interface OrderPresenter: Presenter
    {
        fun setView(view: OrderView)
        fun getOrder(): OrderData
        fun setOrder()
    }
    interface UserPresenter : Presenter
    {
        fun setView(view: AuthorizationView)
        fun getCurrentUser(): User
        fun setCurrentUser()
    }
    interface UserModel{
        fun setUser(login: String, password: String)
        fun getUser() : User
    }
    interface OrderModel{
        fun setOrder(user: User, order : String?, additions : String?)
        fun getOrder() : OrderData
    }
}