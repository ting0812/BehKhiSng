package com.example.myteam

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteam.model.hotelFirstData
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class HotelRecycleAdapter(val hotelFirstList: ArrayList<hotelFirstData>, val context: Context, val node: String, val place:String) :
    RecyclerView.Adapter<HotelRecycleAdapter.ViewHolder>() {

    val database = Firebase.database

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val room_name : TextView = itemView.findViewById(R.id.hotel_name)
        val room_address : TextView = itemView.findViewById(R.id.hotel_address)
        val room_photo : ImageView = itemView.findViewById(R.id.hotel_Image)
        val hoteladdtomylove : ImageButton = itemView.findViewById(R.id.hotel_addtomylove)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.hotel_cardview, parent, false)
        return ViewHolder(v)

    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = hotelFirstList[position]
        val pictureurl = currentitem.room_photo
        val pictureurl_new = pictureurl?.replaceFirst("/","https:/")

        Picasso.get().load(pictureurl_new).into(holder.room_photo)
        holder.room_name.text = currentitem.room_name
        holder.room_address.text = currentitem.room_address?.replace("住址：","")


        holder.itemView.setOnClickListener {

            val pushitem = hotelFirstList.get(position)
            val hotelName : String? = pushitem.room_name
            val hotelAddress : String? = pushitem.room_address
            val hotelPictureUrl : String? = pushitem.room_photo
            val hotelIntroduction : String? = pushitem.room_detail
            val hotelprice : String? = pushitem.room_money
            val intent = Intent(getActivity(context), click_main_hotel::class.java)
            intent.putExtra("room_name",hotelName)
            intent.putExtra("room_address",hotelAddress)
            intent.putExtra("room_photo",hotelPictureUrl)
            intent.putExtra("room_detail",hotelIntroduction)
            intent.putExtra("room_money",hotelprice)
            intent.putExtra("place",place)
            intent.putExtra("node",node)

            context.startActivity(intent)

        }

        //我的最愛按鈕
        holder.hoteladdtomylove.setOnClickListener {

            //點下我的最愛會填滿
            if(holder.hoteladdtomylove.getTag() == R.drawable.ic_heart_outlined)
                holder.hoteladdtomylove.setImageResource(R.drawable.ic_heart_filled);
            else
                holder.hoteladdtomylove.setImageResource(R.drawable.ic_heart_filled);
            //點下我的最愛會填滿

            val pushitem = hotelFirstList.get(position)
            val hotelName : String? = pushitem.room_name
            val hotelAddress : String? = pushitem.room_address
            val hotelPictureUrl : String? = pushitem.room_photo
            val hoteldetail : String? = pushitem.room_detail

            write_into_firebase(hotelName.toString(), hotelAddress.toString(),hotelPictureUrl.toString(),hoteldetail.toString())
        }

    }

    override fun getItemCount(): Int {
        return hotelFirstList.size
    }


    private fun write_into_firebase( hotelname:String, hoteladdress:String, hotelurl:String, hoteldetail:String){
        val mylove = mutableMapOf("name" to hotelname,"address" to hoteladdress,"url" to hotelurl, "detail" to hoteldetail)

        database.getReference("journey").child(node).child("favorite").child(hotelname).setValue(mylove)

    }

}




