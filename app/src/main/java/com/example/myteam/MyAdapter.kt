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
import com.example.myteam.model.restaurantData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MyAdapter(val restaurantList: ArrayList<restaurantData>,val main_hotelName: String, val context:Context,val node :String) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val database = Firebase.database

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = restaurantList[position]

        val url = currentitem.coverUrl

        Picasso.get().load(url).into(holder.imageView)

        holder.name.text = currentitem.name
        holder.avgPrice.text = currentitem.avgPrice.toString()
        holder.phone.text = currentitem.phone
        holder.address.text = currentitem.address

        holder.itemView.setOnClickListener {
            //點開餐廳，看詳細資訊
            val pushresitem = restaurantList.get(position)
            val resName : String? = pushresitem.name
            val resavgPrice : String? = pushresitem.avgPrice.toString()
            val resAddress : String? = pushresitem.address
            val resPhone : String? = pushresitem.phone
            var rescoverUrl : String? = pushresitem.coverUrl
            val resopeningHoursList :ArrayList<String>? = pushresitem.openingHoursList
            val intent = Intent(context,click_res::class.java)

            intent.putExtra("main_hotelName",main_hotelName)//new
            intent.putExtra("name",resName)
            intent.putExtra("avgPrice",resavgPrice)
            intent.putExtra("address",resAddress)
            intent.putExtra("phone",resPhone)
            intent.putExtra("coverUrl",rescoverUrl)
            intent.putExtra("openingHoursList",resopeningHoursList)
            context.startActivity(intent)

        }

        //我的最愛按鈕
        holder.resaddtomylove.setOnClickListener {

            //點下我的最愛會填滿
            if(holder.resaddtomylove.getTag() == R.drawable.ic_heart_outlined)
                holder.resaddtomylove.setImageResource(R.drawable.ic_heart_filled);
            else
                holder.resaddtomylove.setImageResource(R.drawable.ic_heart_filled);
            //點下我的最愛會填滿

            val pushitem = restaurantList.get(position)
            val Name : String? = pushitem.name
            val Address : String? = pushitem.address
            val PictureUrl : String? = pushitem.coverUrl
            val Pictureurl_new = PictureUrl?.replaceFirst("https:/","/")
            val resdetail : ArrayList<String>? = pushitem.openingHoursList

            write_into_firebase(Name.toString(), Address.toString(),Pictureurl_new.toString(),resdetail.toString())
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView : ImageView = itemView.findViewById(R.id.resImage)
        val name : TextView = itemView.findViewById(R.id.title_restaurant)
        val avgPrice : TextView = itemView.findViewById(R.id.description_restaurant01)
        val phone : TextView = itemView.findViewById(R.id.description_restaurant02)
        val address : TextView = itemView.findViewById(R.id.description_restaurant03)
        val resaddtomylove : ImageButton = itemView.findViewById(R.id.res_addtomylove)
    }

    private fun write_into_firebase(name:String, address:String, url:String, detail:String){
        val mylove = mutableMapOf("name" to name, "address" to address, "url" to url, "detail" to detail)

        database.getReference("journey").child(node).child("favorite").child(name).setValue(mylove)

    }
}