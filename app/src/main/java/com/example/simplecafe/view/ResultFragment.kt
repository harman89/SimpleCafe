package com.example.simplecafe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.simplecafe.ViewModel.CafeViewModel
import com.example.simplecafe.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private val cafeViewModel : CafeViewModel by activityViewModels()
    private lateinit var binding : FragmentResultBinding

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
        cafeViewModel.orders.observe(activity as LifecycleOwner){
            textName.text = "Name: ${it.User.login}"
            textPass.text = "Password: ${it.User.password}"
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
