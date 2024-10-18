package com.example.myteam

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_click_main_hotel.*
import kotlinx.android.synthetic.main.activity_click_main_hotel.hotel_address
import kotlinx.android.synthetic.main.activity_click_res.*
import kotlinx.android.synthetic.main.click_hotel.*
import kotlinx.android.synthetic.main.click_hotel.hotel_title
import kotlinx.android.synthetic.main.hotel_cardview.*

class click_main_hotel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_click_main_hotel)
        super.onCreate(savedInstanceState)

//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)
        val intent: Intent = intent
        val main_hotelName = intent.getStringExtra("room_name").toString()
        val main_hotelName1 = main_hotelName
        val main_hotelAddress = intent.getStringExtra("room_address")?.replace("住址：","")
        val main_hoteldetail = intent.getStringExtra("room_detail")
        val main_hotelmoney = intent.getStringExtra("room_money")?.replace("元","元起")
        val gethotelurl = intent.getStringExtra("room_photo")
        val gethotelurl_new = gethotelurl?.replaceFirst("/","https:/")
        var place = intent.getStringExtra("place")
        val node = intent.getStringExtra("node")

        //標題傳過去//
//        actionBar.setTitle(main_hotelTitle)
        hotel_title.text = main_hotelName
        hotel_address.text = main_hotelAddress
        hotel_introduction.text = main_hoteldetail
        hotel_money.text = main_hotelmoney
        Picasso.get().load(gethotelurl_new).into(hotel_Images)

        //button
        val grbButton = findViewById<Button>(R.id.goto_res_btn)
        //handle onClick
        grbButton.setOnClickListener {
            //intent to start NewActivity
            val intent = Intent(this@click_main_hotel,restaurant_list::class.java)
            if(place=="Hualien County")
                place="Hualien  City"
            if(place == "Taitung County")
                place ="Taitung City"
            if(place == "Changhua County")
                place ="Changhua City"
            if(place == "Nantou County")
                place ="Nantou City"
            if(place == "Yunlin County")
                place ="Yunlin City"
            if(place == "Yilan County")
                place ="Yilan City"
            intent.putExtra("place",place)
            intent.putExtra("node",node)
            intent.putExtra("main_hotelName",main_hotelName)
            startActivity(intent)
        }

        val gabButton = findViewById<Button>(R.id.goto_att_btn)
        //handle onClick
        gabButton.setOnClickListener {
            //intent to start NewActivity
            val intent = Intent(this@click_main_hotel,attractions_list::class.java)
            intent.putExtra("place",place)
            intent.putExtra("node",node)
            intent.putExtra("main_hotelName",main_hotelName)
            startActivity(intent)
        }

        val locationBtn = findViewById<Button>(R.id.mhotel_googlemap_btn)
        locationBtn.setOnClickListener{
            val orgin = main_hotelName1
            val destination = orgin
            DisplayTrack(orgin,destination)
        }

    }
    private fun DisplayTrack(origin:String, destination:String)
    {
        try{
            val uri = Uri.parse("https://www.google.co.in/maps/search/" + origin + "/")
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

