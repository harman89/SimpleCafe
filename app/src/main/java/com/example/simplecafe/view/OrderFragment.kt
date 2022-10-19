package com.example.simplecafe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.simplecafe.CafeViewModel
import com.example.simplecafe.data.model.OrderData
import com.example.simplecafe.R
import com.example.simplecafe.SimpleCafeMVP
import com.example.simplecafe.databinding.FragmentOrderBinding

class OrderFragment : Fragment(), SimpleCafeMVP.OrderView {
    private var login: String? = null
    private var password: String? = null
    private val coffeeTypes = arrayOf("Cappuccino","Americano")
    private val teaTypes = arrayOf("Black","Green")
    private var drink : String = "Coffee"
    private var type : String ="Cappuccino"
    private var additions = ""
    private val cafeViewModel : CafeViewModel by activityViewModels()
    private lateinit var textViewGreeting: TextView
    private lateinit var radioButtonTea :RadioButton
    private lateinit var radioButtonCoffee :RadioButton
    private lateinit var checkBoxMilk : CheckBox
    private lateinit var checkBoxSugar : CheckBox
    private lateinit var checkBoxLemon : CheckBox
    private lateinit var radioGroup :RadioGroup
    private lateinit var buttonOrder :Button
    private lateinit var spinner:Spinner
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
        /*cafeViewModel.orderData.observe(activity as LifecycleOwner){
            login = it.Name
            password = it.Password
        }*/
        login = "test"
        textViewGreeting = binding.textViewGreeting
        radioButtonTea =binding.radioButtonTea
        radioButtonCoffee =binding.radioButtonCoffee
        checkBoxMilk = binding.checkBoxMilk
        checkBoxSugar= binding.checkBoxSugar
        checkBoxLemon = binding.checkBoxLemon
        radioGroup = binding.radioGroup
        buttonOrder =binding.buttonMakeOrderFinal
        spinner = binding.spinner
        textViewGreeting.text="Hello, $login"


        checkBoxLemon.visibility = View.GONE
        radioButtonCoffee.isChecked = true
        spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,coffeeTypes)
        radioGroup.setOnCheckedChangeListener{ _, _ ->
            if(radioButtonTea.isChecked) {
                checkBoxLemon.visibility = View.VISIBLE
                drink = getTea()
                spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,teaTypes)
            }
            if(radioButtonCoffee.isChecked)
            {
                drink = getCoffee()
                checkBoxLemon.isChecked=false
                checkBoxLemon.visibility = View.GONE
                spinner.adapter = ArrayAdapter(this.requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,coffeeTypes)
            }
            //
        }
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                type = p0?.getItemAtPosition(p2).toString()
            }
        }
        buttonOrder.setOnClickListener{

            if(checkBoxLemon.isChecked)
                additions+=getLemon()+","
            if(checkBoxMilk.isChecked)
                additions+=getMilk()+","
            if(checkBoxSugar.isChecked)
                additions+=getSugar()+","
            if(additions=="")
                additions = "None"
            additions = additions.slice(0 until additions.length-1)
           // val orderData = OrderData()
            //orderData.Name = login
            //orderData.Password = password
            //orderData.Order = "$drink \n $type"
            //orderData.Additions = additions
            //cafeViewModel.orderData.value = orderData
            val fragmentResult : ResultFragment = ResultFragment.newInstance()
            parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frameLayoutMain,fragmentResult).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OrderFragment()
    }

    override fun getTea(): String {
        return radioButtonTea.text.toString()
    }

    override fun getCoffee(): String {
        return radioButtonCoffee.text.toString()
    }

    /*override fun getType(): String {
        return
    }*/

    override fun getLemon(): String {
        return checkBoxLemon.text.toString()
    }

    override fun getMilk(): String {
        return checkBoxMilk.text.toString()
    }

    override fun getSugar(): String {
        return checkBoxSugar.text.toString()
    }

}