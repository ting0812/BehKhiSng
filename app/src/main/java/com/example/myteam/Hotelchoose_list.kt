package com.example.myteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteam.model.attractionData
import com.example.myteam.model.emptyroomData
import com.example.myteam.model.hotelchooseData
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_hotelchoose_list.*


class Hotelchoose_list : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var emptydbref: DatabaseReference
    private lateinit var hcRecyclerView: RecyclerView
    private lateinit var hotelchoose_list: ArrayList<hotelchooseData>
    private lateinit var emptyroomList: ArrayList<emptyroomData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotelchoose_list)

        hcRecyclerView = findViewById(R.id.hotelchoose_recycleview)
        hcRecyclerView.layoutManager = LinearLayoutManager(this)
        hcRecyclerView.setHasFixedSize(true)


        hotelchoose_list = arrayListOf<hotelchooseData>()
        emptyroomList = arrayListOf<emptyroomData>()
        getchooseData()




//
//        val arrayList = ArrayList<Model_Hotelchoose>()
//        arrayList.add(Model_Hotelchoose("1"+getstring, "hotelchoose_list", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("2", "222", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("3", "33", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("4", "44", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("5", "se rfs", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("6", "vgyrc", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("7", "mkifv", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("8", "wdfth", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("9", "bte", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("10", "njuy", R.drawable.main_foot))
//        arrayList.add(Model_Hotelchoose("11", "xsrfv", R.drawable.main_foot))
//
//        val hotelchooseadapter = HotelchooseAdapter(arrayList, this)
//        hotelchoose_recycleview.layoutManager = LinearLayoutManager(this)
//        hotelchoose_recycleview.adapter = hotelchooseadapter
    }

    private fun getchooseData()
    {
        val getthree_star = intent.getBundleExtra("bundle")?.getString("three_star").toString()
        val getfour_star = intent.getBundleExtra("bundle")?.getString("four_star").toString()
        val getfive_star = intent.getBundleExtra("bundle")?.getString("five_star").toString()
        val gethotspring = intent.getBundleExtra("bundle")?.getString("hotspring").toString()

        val gettraffic = intent.getBundleExtra("bundle")?.getString("traffic").toString()
        val getsmart = intent.getBundleExtra("bundle")?.getString("smart").toString()
        val getchild = intent.getBundleExtra("bundle")?.getString("child").toString()
        val getenvironment = intent.getBundleExtra("bundle")?.getString("environment").toString()
        val getbussiness = intent.getBundleExtra("bundle")?.getString("bussiness").toString()
        val getcamp = intent.getBundleExtra("bundle")?.getString("camp").toString()
        val getyoung_people = intent.getBundleExtra("bundle")?.getString("young_people").toString()
        val getsky = intent.getBundleExtra("bundle")?.getString("sky").toString()
        val getonethousand_twothousand = intent.getBundleExtra("bundle")?.getString("onethousand_twothousand").toString()
        val gettwothousand_threethousand = intent.getBundleExtra("bundle")?.getString("twothousand_threethousand").toString()
        val getthreethousand_fourthousand = intent.getBundleExtra("bundle")?.getString("threethousand_fourthousand").toString()
        val getfourthousand_fivethousand = intent.getBundleExtra("bundle")?.getString("fourthousand_fivethousand").toString()
        val getonethousand_down = intent.getBundleExtra("bundle")?.getString("onethousand_down").toString()
        val getfivethousand_up = intent.getBundleExtra("bundle")?.getString("fivethousand_up").toString()



        val destination = intent.getBundleExtra("bundle")?.getString("destination").toString()
        val begin_date = intent.getBundleExtra("bundle")?.getString("begin_date").toString()
        val node = begin_date + destination

        val place = intent.getBundleExtra("bundle")?.getString("place_list").toString()//NEW




        if(getthree_star == "true"){

            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_3星級/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_3星級/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_3星級/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_3星級/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_3星級/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_3星級/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/3星級/"+destination)
        }
        else if(getfour_star == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_4星級/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_4星級/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_4星級/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_4星級/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_4星級/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_4星級/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/4星級/"+destination)
        }
        else if(getfive_star == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_5星級/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_5星級/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_5星級/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_5星級/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_5星級/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_5星級/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/5星級/"+destination)
        }
        else if(gethotspring == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_溫泉/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_溫泉/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_溫泉/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_溫泉/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_溫泉/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_溫泉/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/溫泉/"+destination)
        }
        else if(gettraffic == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_交通便利/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_交通便利/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_交通便利/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_交通便利/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_交通便利/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_交通便利/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/交通便利/"+destination)
        }
        else if(getsmart == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_智能/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_智能/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_智能/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_智能/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_智能/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_智能/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/智能/"+destination)
        }
        else if(getchild == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_親子/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_親子/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_親子/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_親子/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_親子/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_親子/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/親子/"+destination)
        }
        else if(getenvironment == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_環保/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_環保/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_環保/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_環保/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_環保/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_環保/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/環保/"+destination)
        }
        else if(getbussiness == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_商務/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_商務/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_商務/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_商務/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_商務/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_商務/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/商務/"+destination)
        }
        else if(getcamp == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_露營/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_露營/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_露營/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_露營/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_露營/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_露營/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/露營/"+destination)
        }
        else if(getyoung_people == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_青旅/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_青旅/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_青旅/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_青旅/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_青旅/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_青旅/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/青旅/"+destination)
        }
        else if(getsky == "true"){
            if(getonethousand_twothousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千_天文/"+destination)
            }
            else if(getonethousand_down == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/一千以下_天文/"+destination)
            }
            else if(getfivethousand_up == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/五千以上_天文/"+destination)
            }
            else if(gettwothousand_threethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千_天文/"+destination)
            }
            else if(getthreethousand_fourthousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/三千四千_天文/"+destination)
            }
            else if(getfourthousand_fivethousand == "true"){
                dbref = FirebaseDatabase.getInstance().getReference("category/四千五千_天文/"+destination)
            }
            else
                dbref = FirebaseDatabase.getInstance().getReference("category/天文/"+destination)
        }
        else if(getonethousand_twothousand == "true"){
            dbref = FirebaseDatabase.getInstance().getReference("category/一千兩千/"+destination)
        }
        else if(gettwothousand_threethousand == "true"){
            dbref = FirebaseDatabase.getInstance().getReference("category/兩千三千/"+destination)
        }
        else if(getthreethousand_fourthousand == "true"){
            dbref = FirebaseDatabase.getInstance().getReference("category/三千四千/"+destination)
        }
        else if(getfourthousand_fivethousand == "true"){
            dbref = FirebaseDatabase.getInstance().getReference("category/四千五千/"+destination)
        }
        else
            dbref = FirebaseDatabase.getInstance().getReference("room/Kaohsiung")


        emptydbref = FirebaseDatabase.getInstance()
            .getReference("emptyroom/" + begin_date + "/" + destination)

        emptydbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(emptysnapshot: DataSnapshot) {
                if (emptysnapshot.exists()) {
                    for (emptySnapshot in emptysnapshot.children) {
                        val emptyFirst = emptySnapshot.getValue(emptyroomData::class.java)
                        emptyroomList.add(emptyFirst!!)


                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (userSnapshot in snapshot.children)
                    {
                        val hotelchoose = userSnapshot.getValue(hotelchooseData::class.java)
                        for (i in 0..(emptyroomList.size - 1)) {
                            if (hotelchoose?.room_name.toString() == (emptyroomList[i]?.room_name.toString()))
                                hotelchoose_list.add(hotelchoose!!)
                        }



                        Log.d(TAG,"test*****************")
                    }

                    hcRecyclerView.adapter = HotelchooseAdapter(hotelchoose_list,this@Hotelchoose_list,node ,place)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}




