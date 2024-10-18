package com.example.myteam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myloveitem_caedview.view.*

class MyloveitemAdapter(val arrayList: ArrayList<Model_myloveitem>, val context: Context):
    RecyclerView.Adapter<MyloveitemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(modelmyloveitem: Model_myloveitem){
            itemView.title_myloveitem.text = modelmyloveitem.title
            itemView.description_myloveitem.text = modelmyloveitem.descriptor
            itemView.image_myloveitem.setImageResource(modelmyloveitem.imageDrawable)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val w = LayoutInflater.from(parent.context).inflate(R.layout.myloveitem_caedview, parent, false)

        return ViewHolder(w)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
        holder.itemView.setOnClickListener {
            val modelmyloveitem = arrayList.get(position)
            val myloveitemTitle: String = modelmyloveitem.title
            val myloveitemDescription: String = modelmyloveitem.descriptor
            var myloveitemImageView: Int = modelmyloveitem.imageDrawable

            val intent = Intent(context, click_attractions::class.java)
            intent.putExtra("iTitle", myloveitemTitle)
            intent.putExtra("iDescription", myloveitemDescription)
            intent.putExtra("iImageView", myloveitemImageView)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return arrayList.size
    }


}