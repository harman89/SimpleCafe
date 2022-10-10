package com.example.simplecafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.simplecafe.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private val cafeViewModel :CafeViewModel by activityViewModels()
    private lateinit var binding : FragmentResultBinding
    private var Name :String = ""
    private var Password :String = ""
    private var Order :String = ""
    private var Additions :String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textName :TextView = binding.textViewNameRes
        val textPass :TextView = binding.textViewPassRes
        val textOrder :TextView = binding.textViewOrderRes
        val textAdditions :TextView = binding.textViewIncludeRes
        cafeViewModel.orderData.observe(activity as LifecycleOwner){
            textName.text = "Name: ${it.Name}"
            textPass.text = "Password: ${it.Password}"
            textOrder.text = "Order: ${it.Order}"
            textAdditions.text = "Additions: \n ${it.Additions}"
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            ResultFragment()
    }
}