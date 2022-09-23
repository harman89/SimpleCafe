package com.example.simplecafe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getStringArray(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View = inflater.inflate(R.layout.fragment_result, container, false)
        val textName :TextView = v.findViewById(R.id.textViewNameRes)
        val textPass :TextView = v.findViewById(R.id.textViewPassRes)
        val textOrder :TextView = v.findViewById(R.id.textViewOrderRes)
        val textAdditions :TextView = v.findViewById(R.id.textViewIncludeRes)
        if (param1 != null) {
            textName.text = "Name: "+ param1!![0]
            textPass.text = "Password: "+ param1!![1]
            textOrder.text = "Order: "+ param1!![2]+"\n"+ param1!![3]
            textAdditions.text = "Additions: \n"+ param1!![4]
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
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Array<String?>) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(ARG_PARAM1,param1)
                }
            }
    }
}