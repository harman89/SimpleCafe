package com.example.simplecafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.simplecafe.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var login: String? = null
    private var password: String? = null
    private val coffeeTypes = arrayOf("Cappuccino","Americano")
    private val teaTypes = arrayOf("Black","Green")
    private var drink : String = "Coffee"
    private var type : String ="Cappuccino"
    private var additions = ""
    private val cafeViewModel :CafeViewModel by activityViewModels()
    private lateinit var binding : FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cafeViewModel.orderData.observe(activity as LifecycleOwner){
            login = it.Name
            password = it.Password
        }
        val textViewGreeting: TextView = binding.textViewGreeting
        textViewGreeting.text="Hello, $login"
        val radioButtonTea :RadioButton =binding.radioButtonTea
        val radioButtonCoffee :RadioButton =binding.radioButtonCoffee
        val checkBoxMilk : CheckBox = binding.checkBoxMilk
        val checkBoxSugar : CheckBox = binding.checkBoxSugar
        val checkBoxLemon : CheckBox = binding.checkBoxLemon
        val radioGroup :RadioGroup = binding.radioGroup
        val buttonOrder :Button =binding.buttonMakeOrderFinal
        val spinner:Spinner = binding.spinner

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
            val orderData = OrderData()
            orderData.Name = login
            orderData.Password = password
            orderData.Order = "$drink \n $type"
            orderData.Additions = additions
            cafeViewModel.orderData.value = orderData
            val fragmentResult :ResultFragment = ResultFragment.newInstance()
            parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayoutMain,fragmentResult).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OrderFragment()
    }
}