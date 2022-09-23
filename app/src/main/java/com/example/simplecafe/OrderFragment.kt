package com.example.simplecafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "login"
private const val ARG_PARAM2 = "password"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var login: String? = null
    private var password: String? = null
    val coffeeTypes = arrayOf("Cappuccino","Americano")
    val teaTypes = arrayOf("Black","Green")
    var drink : String = "Coffee"
    var type : String ="Cappuccino"
    var additions = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(ARG_PARAM1)
            password = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View = inflater.inflate(R.layout.fragment_order, container, false)

        val textViewGreeting: TextView = v.findViewById(R.id.textViewGreeting)
        textViewGreeting.text="Hello, "+login

        val radioButtonTea :RadioButton =v.findViewById(R.id.radioButtonTea)
        val radioButtonCoffee :RadioButton =v.findViewById(R.id.radioButtonCoffee)
        val checkBoxMilk : CheckBox = v.findViewById(R.id.checkBoxMilk)
        val checkBoxSugar : CheckBox = v.findViewById(R.id.checkBoxSugar)
        val checkBoxLemon : CheckBox = v.findViewById(R.id.checkBoxLemon)
        val radioGroup :RadioGroup = v.findViewById(R.id.radioGroup)
        val buttonOrder :Button = v.findViewById(R.id.buttonMakeOrderFinal)
        val spinner:Spinner = v.findViewById(R.id.spinner)
        checkBoxLemon.visibility = View.GONE
        radioButtonCoffee.isChecked = true
        spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,coffeeTypes)
        radioGroup.setOnCheckedChangeListener{ _, _ ->
            if(radioButtonTea.isChecked) {
                checkBoxLemon.visibility = View.VISIBLE
                drink = radioButtonTea.text.toString()
                spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,teaTypes)
            }
            if(radioButtonCoffee.isChecked)
            {
                drink = radioButtonCoffee.text.toString()
                checkBoxLemon.isChecked=false
                checkBoxLemon.visibility = View.GONE
                spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,coffeeTypes)
            }
            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    type = p0?.getItemAtPosition(p2).toString()
                }
            }
        }
        buttonOrder.setOnClickListener{

            if(checkBoxLemon.isChecked)
                additions+=checkBoxLemon.text.toString()+","
            if(checkBoxMilk.isChecked)
                additions+=checkBoxMilk.text.toString()+","
            if(checkBoxSugar.isChecked)
                additions+=checkBoxSugar.text.toString()+","
            if(additions=="")
                additions = "None"
            additions = additions.slice(0 until additions.length-1)
            var resultArray = arrayOf(login,password,drink,type,additions)
            val fragmentResult :ResultFragment = ResultFragment.newInstance(resultArray)
            parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayoutMain,fragmentResult).commit()

        }
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param login Parameter 1.
         * @param password Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(login: String, password: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, login)
                    putString(ARG_PARAM2, password)
                }
            }
    }
}