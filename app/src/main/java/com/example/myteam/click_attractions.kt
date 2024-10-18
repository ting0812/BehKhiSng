package com.example.myteam

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_click_attractions.*
import kotlinx.android.synthetic.main.activity_click_res.*


class click_attractions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_attractions)

//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)

        val intent =intent
        val main_hotelName = intent.getStringExtra("main_hotelName").toString()
        val att_Name = intent.getStringExtra("name")
        val att_Address = intent.getStringExtra("address")
        val att_Phone = intent.getStringExtra("phone")
        val att_Introduction = intent.getStringExtra("introduction")
        val getattImage = intent.getStringExtra("pictureUrl")



//        actionBar.setTitle(att_Title)
        attrction_detail_name.text = att_Name
        attrction_detail_address.text = att_Address
        attrction_detail_phone.text = att_Phone
        attrction_detail_introducton.text= att_Introduction
        Picasso.get().load(getattImage).into(attrction_imageView)

        val locationBtn = findViewById<Button>(R.id.att_location_btn)
        locationBtn.setOnClickListener{
            val orgin = main_hotelName
            val destination = att_Name.toString()
            DisplayTrack(orgin,destination)
        }

    }

    private fun DisplayTrack(origin:String, destination:String)
    {
        try{
            val uri = Uri.parse("http://www.google.co.in/maps/dir/" + origin + "/" + destination)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }catch (ex: android.content.ActivityNotFoundException){
            val uri = Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}


