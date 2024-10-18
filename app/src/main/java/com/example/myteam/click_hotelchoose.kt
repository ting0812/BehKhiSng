package com.example.myteam

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_click_hotelchoose.*
import kotlinx.android.synthetic.main.activity_click_main_hotel.*
import kotlinx.android.synthetic.main.click_hotel.*
import kotlinx.android.synthetic.main.click_hotel.hotel_title

class click_hotelchoose : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_hotelchoose)


//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)

        val intent: Intent = intent
        val main_hotelName = intent.getStringExtra("room_name").toString()
        val place = intent.getStringExtra("place")
        val c_hotelName = intent.getStringExtra("room_name")
        val c_hotelAddress = intent.getStringExtra("room_address")?.replace("住址：","")
        val c_hoteldetail = intent.getStringExtra("room_detail")
        val c_hotelmoney = intent.getStringExtra("room_money")?.replace("元","元起")
        val gethotelurl = intent.getStringExtra("room_photo")
        val gethotelurl_new = gethotelurl?.replaceFirst("/","https:/")

        choose_name.text = c_hotelName
        choose_address.text = c_hotelAddress
        choose_introduction.text = c_hoteldetail
        choose_money.text = c_hotelmoney
        Picasso.get().load(gethotelurl_new).into(choose_image)



        val ToresButton = findViewById<Button>(R.id.to_res_btn)
        //handle onClick
        ToresButton.setOnClickListener {
            //intent to start NewActivity
            val intent = Intent(this@click_hotelchoose,restaurant_list::class.java)
            intent.putExtra("place",place)
            intent.putExtra("main_hotelName",main_hotelName)
            startActivity(intent)
        }


        val ToattButton = findViewById<Button>(R.id.to_attractions_btn)
        //handle onClick
        ToattButton.setOnClickListener {
            //intent to start NewActivity
            val intent = Intent(this@click_hotelchoose,attractions_list::class.java)
            intent.putExtra("place",place)
            intent.putExtra("main_hotelName",main_hotelName)
            startActivity(intent)
        }
        val locationBtn = findViewById<Button>(R.id.choose_googlemap_btn)
        locationBtn.setOnClickListener{
            val orgin = c_hotelName.toString()
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

