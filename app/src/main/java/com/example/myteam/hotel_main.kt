package com.example.myteam

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ContextUtils.getActivity

class hotel_main : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_main)


        val destination = intent.getStringExtra("destination")
        val begin_date = intent.getStringExtra("begin_date")
        val end_date = intent.getStringExtra("end_date")



        var bundle = Bundle()
        bundle.putString("destination", destination)
        bundle.putString("begin_date", begin_date)
        bundle.putString("end_date", end_date)
//
//        var intent = Intent(this, Fragment_hotel::class.java)
//        intent.putExtra("bundle", bundle)
//        startActivity(intent)

        bottomNav = findViewById(R.id.bottomNav)
        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_hotel,
                R.id.fragment_choose,
                R.id.fragment_mylove
            )
        )

        bottomNav.setupWithNavController(navController)


    }


}
