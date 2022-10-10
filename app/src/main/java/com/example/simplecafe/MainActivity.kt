package com.example.simplecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.simplecafe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMainBinding
    private val cafeViewModel : CafeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var name:String? = ""
        var password:String? = ""
        var order:String? = ""
        var additions:String? = ""
        cafeViewModel.orderData.observe(this){
            name = it.Name
            password = it.Password
            order = it.Order
            additions = it.Additions
        }
        if (savedInstanceState==null)
        {
            supportFragmentManager.beginTransaction().add(R.id.frameLayoutMain,AuthorizationFragment.newInstance(),"AuthorizationFragment").commit()
        }

    }
}