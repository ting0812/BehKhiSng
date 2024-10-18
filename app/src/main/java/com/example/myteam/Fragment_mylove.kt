package com.example.myteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myteam.model.journeyData
import com.example.myteam.model.myloveData
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_hotel.view.*
import kotlinx.android.synthetic.main.fragment_mylove.view.*



class Fragment_mylove : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var myloveList: ArrayList<myloveData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mylove, container, false)
        view.recyclerview_mylove.layoutManager = LinearLayoutManager(activity)
        view.setHasFixedSize(true)
        getmyloveData()
        myloveList = arrayListOf()
        return view
    }
    private fun getmyloveData(){

        val intent = activity?.getIntent()
        val node = intent?.getStringExtra("begin_date") + intent?.getStringExtra("destination")


        dbref = FirebaseDatabase.getInstance().getReference("journey").child(node).child("favorite")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (userSnapshot in snapshot.children)
                    {
                        val mylove = userSnapshot.getValue(myloveData::class.java)
                        myloveList.add(mylove!!)

                    }

                    view?.recyclerview_mylove?.adapter = MyloveRecycleAdapter(myloveList,activity!!)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}
public fun View.setHasFixedSize(b: Boolean) {

}
