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
import com.example.simplecafe.databinding.FragmentAuthorizationBinding
import java.lang.Exception

class AuthorizationFragment : Fragment() {
    private val cafeViewModel : CafeViewModel by activityViewModels()
    private lateinit var binding :FragmentAuthorizationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textLogin:EditText = binding.editTextUserName
        val textPassword:EditText = binding.editTextTextPassword

        val btnOrder: Button = binding.buttonOrder
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
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthorizationFragment()
    }
}