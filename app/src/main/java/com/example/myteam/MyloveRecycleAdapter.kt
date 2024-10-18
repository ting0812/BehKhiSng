package com.example.myteam


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myteam.model.journeyData
import com.example.myteam.model.myloveData
import com.google.android.material.internal.ContextUtils
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class MyloveRecycleAdapter(val myloveList: ArrayList<myloveData>, val context: Context) :
    RecyclerView.Adapter<MyloveRecycleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mylovename : TextView = itemView.findViewById(R.id.title_mylove)
        val myloveaddress : TextView = itemView.findViewById(R.id.address_mylove)
        val myloveurl : ImageView = itemView.findViewById(R.id.image_mylove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.mylove_cardview, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pushitem = myloveList[position]
        holder.mylovename.text = pushitem.name
        holder.myloveaddress.text = pushitem.address?.replace("住址：","")
        val pictureurl = pushitem.url
        val pictureurl_new = pictureurl?.replaceFirst("/","https:/")
        Picasso.get().load(pictureurl_new).into(holder.myloveurl)



        holder.itemView.setOnClickListener {
            val pushitem = myloveList.get(position)
            val mylovename = pushitem.name
            val myloveaddress = pushitem.address
            val mylovedetail = pushitem.detail
            val mylovephoto = pushitem.url
            val intent = Intent(getActivity(context), click_mylove_new::class.java)
            intent.putExtra("name",mylovename)
            intent.putExtra("address",myloveaddress)
            intent.putExtra("detail",mylovedetail)
            intent.putExtra("url",mylovephoto)
            context.startActivity(intent)
        }

    }



    override fun getItemCount(): Int {
        return myloveList.size
    }

}

