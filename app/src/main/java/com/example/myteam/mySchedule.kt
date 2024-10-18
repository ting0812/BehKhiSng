package com.example.myteam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteam.model.myscheduleData
import com.google.firebase.database.*


class mySchedule : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myList: ArrayList<myscheduleData>
    private lateinit var floatingbtn : com.google.android.material.floatingactionbutton.FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_schedule)
//BenKhiSng圖
//        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
//        getSupportActionBar()?.setIcon(R.drawable.app_logo)

        myRecyclerView = findViewById(R.id.recyclerview_myschedule)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        myList = arrayListOf<myscheduleData>()
        getmyscheduleData()
        floatingbtn = findViewById(R.id.floatingActionButton)
        floatingbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //here
                startActivity(Intent(this@mySchedule, input_schedule::class.java))
            }
        })

    }
    //menu
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.appbar_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.getItemId()
//        if (id == R.id.logout_menu) {
//            startActivity(Intent(this@mySchedule, MainActivity::class.java))
//        }
//        return super.onOptionsItemSelected(item)
//    }
    private fun getmyscheduleData()
    {
        dbref = FirebaseDatabase.getInstance().getReference("journey")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (userSnapshot in snapshot.children)
                    {
                        val myschedule = userSnapshot.getValue(myscheduleData::class.java)
                        myList.add(myschedule!!)

                    }

                    myRecyclerView.adapter = myscheduleAdapter(myList,this@mySchedule)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}