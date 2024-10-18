package com.example.myteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hotel.view.*
import com.example.myteam.model.hotelFirstData
import com.example.myteam.model.emptyroomData

import com.google.firebase.database.*

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import androidx.navigation.fragment.FragmentNavigator
import kotlinx.android.synthetic.main.fragment_hotel.*
import org.jetbrains.anko.support.v4.intentFor
import android.content.Intent.getIntent
import android.util.Log


class Fragment_hotel() : Fragment() {

    private lateinit var dbref: DatabaseReference
    private lateinit var emptydbref: DatabaseReference
    private lateinit var hotelFirstList: ArrayList<hotelFirstData>
    private lateinit var emptyroomList: ArrayList<emptyroomData>
    private lateinit var place: String
    private lateinit var node: String
    private lateinit var place_list: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        val view = inflater.inflate(R.layout.fragment_hotel, container, false)
        view.recyclerview_hotel.layoutManager = LinearLayoutManager(activity)
        view.setHasFixedSize(true)
//        val bundle = arguments
//        if(bundle != null){
//            val dest = bundle.getString("destination")
//            if (dest != null) {
//
//            }
//        }
        getFitstHotelData()
        hotelFirstList = arrayListOf<hotelFirstData>()
        emptyroomList = arrayListOf<emptyroomData>()
//        view.recyclerview_hotel.adapter = HotelRecycleAdapter(getFitstHotelData(), activity!!)


        return view
    }


    private fun getFitstHotelData() {
        //有改
        val intent = activity?.getIntent()
        val sss = intent?.getStringExtra("begin_date")
        val eee = intent?.getStringExtra("end_date")
        val ddd = intent?.getStringExtra("destination")
        val nnn = intent?.getStringExtra("number_of_people")

        when (ddd) {
            "台北市" -> place_list = "Taipei City"
            "新北市" -> place_list = "New Taipei City"
            "基隆市" -> place_list = "Keelung City"
            "宜蘭市" -> place_list = "Yilan City"
            "桃園市" -> place_list = "Taoyuan City"
            "新竹縣" -> place_list = "Hsinchu County"
            "新竹市" -> place_list = "Hsinchu City"
            "苗栗縣" -> place_list = "Miaoli City"
            "台中市" -> place_list = "Taichung City"
            "彰化縣" -> place_list = "Changhua County"
            "南投縣" -> place_list = "Nantou County"
            "雲林縣" -> place_list = "Yunlin County"
            "嘉義市" -> place_list = "Chiayi City"
            "嘉義縣" -> place_list = "Chiayi County"
            "台南市" -> place_list = "Tainan City"
            "高雄市" -> place_list = "Kaohsiung City"
            "屏東市" -> place_list = "Pingtung City"
            "花蓮縣" -> place_list = "Hualien County"
            "台東縣" -> place_list = "Taitung County"
            "澎湖縣" -> place_list = "Penghu County"
            "金門縣" -> place_list = "Kinmen County"


            else -> place_list = "Taipei City"
        }

        val begin_date = sss
        val end_date = eee
        val destination = ddd
        val new_begin_date = begin_date?.replace(oldValue = ",", newValue = "")?.substring(startIndex = 0, endIndex = 5)?.toInt()
        node = sss.toString() + ddd.toString()
        val number_of_people = nnn
        //有改
        when (destination) {
            "台北市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Taipei")
            "新北市" -> dbref = FirebaseDatabase.getInstance().getReference("room/New Taipei")
            "基隆市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Keelung")
            "宜蘭市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Yilan")
            "桃園市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Taoyuan")
            "新竹縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Hsinchu County")
            "新竹市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Hsinchu City")
            "苗栗縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Miaoli")
            "台中市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Taichung")
            "彰化縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Changhua")
            "南投縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Nantou")
            "雲林縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Yunlin")
            "嘉義市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Chiayi City")
            "嘉義縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Chiayi County")
            "台南市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Tainan")
            "高雄市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Kaohsiung")
            "屏東市" -> dbref = FirebaseDatabase.getInstance().getReference("room/Pingtung")
            "花蓮縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Hualien")
            "台東縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Taitung")
            "澎湖縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Penghu")
            "金門縣" -> dbref = FirebaseDatabase.getInstance().getReference("room/Kinmen")


            else -> dbref = FirebaseDatabase.getInstance().getReference("room/Chiayi")
        }


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
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        val hotelFirst = userSnapshot.getValue(hotelFirstData::class.java)
                        if(new_begin_date!! > 20221)
                            hotelFirstList.add(hotelFirst!!)
                        else {
                            for (i in 0..(emptyroomList.size - 1)) {
                                if (hotelFirst?.room_name.toString() == (emptyroomList[i]?.room_name.toString()))
                                    hotelFirstList.add(hotelFirst!!)
                            }
                        }

                    }

                    view?.recyclerview_hotel?.adapter =
                        HotelRecycleAdapter(hotelFirstList, activity!!, node, place_list)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}









