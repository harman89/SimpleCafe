package com.example.simplecafe.presenter

import androidx.fragment.app.Fragment
import com.example.simplecafe.R
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User

class OrderPresenter(val model : SimpleCafeMVP.OrderModel):SimpleCafeMVP.OrderPresenter {
    private var view :SimpleCafeMVP.OrderView? = null
    private var order : OrderData? = null
    private var fragmentOld: Fragment? = null
    private var fragmentNew: Fragment? = null
    override fun getOrder(): OrderData {
        return  model.getOrder()
    }

    override fun setOrder() {
        this.order = model.getOrder()
    }

    override fun setView(view: SimpleCafeMVP.OrderView) {
        this.view = view
    }

    override fun buttonClicked() {
        if(view!=null)
        {
            model.setOrder(model.getOrder().User,model.getOrder().Order,model.getOrder().Additions)
            changeFragment(fragmentOld!!,fragmentNew!!)
        }
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