package com.example.myteam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteam.model.attractionData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class AttractionsRecycleAdapter (val attractionList: ArrayList<attractionData>,val main_hotelName: String,val context:Context,val node:String):
    RecyclerView.Adapter<AttractionsRecycleAdapter.ViewHolder>() {

    val database = Firebase.database

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.attraction_title)
        val address : TextView = itemView.findViewById(R.id.attraction_address)
        val phone : TextView = itemView.findViewById(R.id.attraction_phone)
//        val introduction : TextView = itemView.findViewById(R.id.attraction_introduction)
        val imageView : ImageView = itemView.findViewById(R.id.image_attraction)
        val attaddtomylove : ImageButton = itemView.findViewById(R.id.attractions_addtomylove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val k = LayoutInflater.from(parent.context).inflate(R.layout.attractions_cardview, parent, false)

        return ViewHolder(k)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAttitem = attractionList[position]
        val attpicurl = currentAttitem.pictureUrl
        Picasso.get().load(attpicurl).into(holder.imageView)

        holder.name.text = currentAttitem.name
        holder.address.text = currentAttitem.address
        holder.phone.text = currentAttitem.phone
//        下面那行馬掉後，在attraction_list中不顯示簡介
//        holder.introduction.text = currentAttitem.introduction

        holder.itemView.setOnClickListener {
            //點開景點看詳細資訊

            val pushitem = attractionList.get(position)
            val attName : String? = pushitem.name
            val attAddress : String? = pushitem.address
            val attPhone : String? = pushitem.phone
            val attIntroduction : String? = pushitem.introduction
            val attpictureUrl : String? = pushitem.pictureUrl
            val intent = Intent(context,click_attractions::class.java)
            intent.putExtra("main_hotelName",main_hotelName)
            intent.putExtra("name",attName)
            intent.putExtra("address",attAddress)
            intent.putExtra("phone",attPhone)
            intent.putExtra("introduction",attIntroduction)
            intent.putExtra("pictureUrl",attpictureUrl)
            context.startActivity(intent)

        }

        //我的最愛按鈕
        holder.attaddtomylove.setOnClickListener {

            //點下我的最愛會填滿
            if(holder.attaddtomylove.getTag() == R.drawable.ic_heart_outlined)
                holder.attaddtomylove.setImageResource(R.drawable.ic_heart_filled);
            else
                holder.attaddtomylove.setImageResource(R.drawable.ic_heart_filled);
            //點下我的最愛會填滿

            val pushitem = attractionList.get(position)
            val Name : String? = pushitem.name
            val Address : String? = pushitem.address
            val PictureUrl : String? = pushitem.pictureUrl
            val Pictureurl_new = PictureUrl?.replaceFirst("https:/","/")
            val  attdetail : String? = pushitem.introduction

            write_into_firebase(Name.toString(), Address.toString(),Pictureurl_new.toString(),attdetail.toString())
        }

    }

    override fun getItemCount(): Int {
        return attractionList.size
    }

    private fun write_into_firebase( name:String, address:String, url:String, detail:String){
        val mylove = mutableMapOf("name" to name,"address" to address,"url" to url, "detail" to detail)

        database.getReference("journey").child(node).child("favorite").child(name).setValue(mylove)

    }
}

