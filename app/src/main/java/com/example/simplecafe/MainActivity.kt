package com.example.simplecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.simplecafe.ViewModel.CafeViewModel
import com.example.simplecafe.view.AuthorizationFragment

class MainActivity : AppCompatActivity() {
    private val cafeViewModel : CafeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null)
        {
            supportFragmentManager.beginTransaction().add(R.id.frameLayoutMain,
                AuthorizationFragment.newInstance(),"AuthorizationFragment").commit()
        }

    }
}
