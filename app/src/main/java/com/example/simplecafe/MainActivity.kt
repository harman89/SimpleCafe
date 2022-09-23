package com.example.simplecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null)
        {
            supportFragmentManager.beginTransaction().add(R.id.frameLayoutMain,AuthorizationFragment.newInstance(),"AuthorizationFragment").commit()
        }
    }
}