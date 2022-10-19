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
import com.example.simplecafe.CafeViewModel
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.R
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.data.model.UserModel
import com.example.simplecafe.data.repository.UserRepositoryClass
import com.example.simplecafe.databinding.FragmentAuthorizationBinding
import com.example.simplecafe.presenter.UserPresenter
import java.lang.Exception

class AuthorizationFragment : Fragment(),SimpleCafeMVP.AuthorizationView {
    private val cafeViewModel : CafeViewModel by activityViewModels()
    private lateinit var binding :FragmentAuthorizationBinding
    private lateinit var presenter : SimpleCafeMVP.UserPresenter
    private lateinit var textLogin:EditText
    private lateinit var textPassword:EditText
    private lateinit var btnOrder: Button
    private lateinit var orderFragment: OrderFragment
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

        presenter = UserPresenter(UserModel(UserRepositoryClass()))

        presenter.setView(this)
        presenter.setOldFragment(this)
        orderFragment = OrderFragment.newInstance()
        presenter.setNewFragment(orderFragment)
        btnOrder.setOnClickListener{presenter.buttonClicked()}

        /*
        btnOrder.setOnClickListener{
            try{
                val login = textLogin.text.toString()
                val password = textPassword.text.toString()
                if (login!="")
                {
                    if(password!="")
                    {
                        val orderData = OrderData()
                        orderData.Name = login
                        orderData.Password = password
                        cafeViewModel.orderData.value = orderData
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
        }*/
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