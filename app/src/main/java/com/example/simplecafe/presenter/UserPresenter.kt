package com.example.simplecafe.presenter

import androidx.fragment.app.Fragment
import com.example.simplecafe.R
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

class UserPresenter( val model : SimpleCafeMVP.UserModel): SimpleCafeMVP.Presenter {
    private var view :SimpleCafeMVP.View? = null
    private var user : User? = null
    private var fragmentOld: Fragment? = null
    private var fragmentNew: Fragment? = null

    override fun setView(view: SimpleCafeMVP.View) {
        this.view = view
    }

    override fun buttonClicked() {
        if(view!=null)
        {
            model.setUser(view!!.getLogin(), view!!.getPassword())
            changeFragment(fragmentOld!!,fragmentNew!!)
        }
    }

    override fun getCurrentUser(): User {
        return model.getUser()
    }

    override fun setCurrentUser() {
        this.user = getCurrentUser()
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