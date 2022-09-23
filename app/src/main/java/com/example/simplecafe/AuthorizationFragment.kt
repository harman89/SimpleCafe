package com.example.simplecafe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthorizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthorizationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View = inflater.inflate(R.layout.fragment_authorization, container, false)
        val textLogin:EditText = v.findViewById(R.id.editTextUserName)
        val textPassword:EditText = v.findViewById(R.id.editTextTextPassword)

        val btnOrder: Button = v.findViewById(R.id.buttonOrder)
        btnOrder.setOnClickListener{
            try{
                val login = textLogin.text.toString()
                val password = textPassword.text.toString()
                if (login!="")
                {
                    if(password!="")
                    {
                        val fragmentOrder :OrderFragment = OrderFragment.newInstance(login,password)
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
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AuthorizationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AuthorizationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}