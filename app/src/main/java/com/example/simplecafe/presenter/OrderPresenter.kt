package com.example.simplecafe.presenter

import androidx.fragment.app.Fragment
import com.example.simplecafe.R
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

class OrderPresenter(val model : SimpleCafeMVP.OrderModel):SimpleCafeMVP.Presenter {
    private var view :SimpleCafeMVP.View? = null
    private var order : OrderData? = null
    private var fragmentOld: Fragment? = null
    private var fragmentNew: Fragment? = null
    override fun setView(view: SimpleCafeMVP.View) {
        TODO("Not yet implemented")
    }

    override fun buttonClicked() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): User {
        TODO("Not yet implemented")
    }

    override fun setCurrentUser() {
        TODO("Not yet implemented")
    }

    override fun changeFragment(fragmentOld: Fragment, fragmentNew: Fragment) {
        fragmentOld.parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayoutMain,fragmentNew).commit()
    }

    override fun setOldFragment(fragment: Fragment) {
        this.fragmentOld = fragment
    }

    override fun setNewFragment(fragment: Fragment) {
        this.fragmentNew = fragment
    }
}