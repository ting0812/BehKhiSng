package com.example.myteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_click_hotelchoose.*
import kotlinx.android.synthetic.main.activity_click_mylove_new.*

class click_mylove_new : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_mylove_new)


//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowHomeEnabled(true)

        val intent: Intent = intent
        val name = intent.getStringExtra("name").toString()
        val address = intent.getStringExtra("address")?.replace("住址：","")
        val detail = intent.getStringExtra("detail")
        val url = intent.getStringExtra("url")
        val url_new = url?.replaceFirst("/","https:/")

        mylove_title.text = name
        mylove_address.text = address
        mylove_detail.text = detail
        Picasso.get().load(url_new).into(mylove_Images)




    }
}