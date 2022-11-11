package com.example.simplecafe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.simplecafe.R
import com.example.simplecafe.ViewModel.CafeViewModel
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.data.model.User
import com.example.simplecafe.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment(),SimpleCafeMVP.AuthorizationView {
    private val cafeViewModel : CafeViewModel by activityViewModels()
    private lateinit var binding :FragmentAuthorizationBinding
    //private lateinit var presenter : SimpleCafeMVP.UserPresenter
    private lateinit var textLogin:EditText
    private lateinit var textPassword:EditText
    private lateinit var btnOrder: Button
    //private lateinit var orderFragment: OrderFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textLogin = binding.editTextUserName
        textPassword= binding.editTextTextPassword
        btnOrder = binding.buttonOrder

        btnOrder.setOnClickListener{
            try{
                val login = getLogin()
                val password = getPassword()
                if (login!="")
                {
                    if(password!="")
                    {
                        val orderData = OrderData(User(login, password),"","")
                        cafeViewModel.setOrder(orderData)
                        cafeViewModel.getOrder()
                        val fragmentOrder : OrderFragment = OrderFragment.newInstance()
                        parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayoutMain,fragmentOrder).commit()
                    }
                    else
                        Toast.makeText(this.context,"Password is empty",Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this.context,"Login is empty",Toast.LENGTH_LONG).show()
            }
            catch (e:Exception){
                throw e
            }
        }
        cafeViewModel.orders.observe(activity as LifecycleOwner){
            if(it!=null)
            {
                setLogin(it.User.login)
                setPassword(it.User.password)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthorizationFragment()
    }

    override fun getLogin(): String {

        return textLogin.text.toString()
    }
    override fun getPassword(): String {
        return textPassword.text.toString()
    }

    override fun setPassword(password: String?) {
        if(password == null)
            this.textPassword.setText("password")
        else
            this.textPassword.setText(password)
    }

    override fun setLogin(login: String?) {
        if(login == null)
            this.textLogin.setText("login")
        else
            this.textLogin.setText(login)
    }
}
