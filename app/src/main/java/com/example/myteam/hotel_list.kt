package com.example.myteam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class hotel_list : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_list)

        //返回到input_schedule
        supportActionBar!!.title="BehKhiSng"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }


}