package com.example.myteam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_click_mylove.*

class click_mylove : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_mylove)

        val arrayList = ArrayList<Model_myloveitem>()

        arrayList.add(Model_myloveitem("1", "11", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("2", "222", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("3", "33", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("4", "44", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("5", "serfs", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("6", "vgyrc", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("7", "mkifv", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("8", "wdfth", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("9", "bte", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("10", "njuy", R.drawable.main_foot))
        arrayList.add(Model_myloveitem("11", "xsrfv", R.drawable.main_foot))

        val myloveitemAdapter = MyloveitemAdapter(arrayList, this)

        journeyRecyclerview.layoutManager = LinearLayoutManager(this)
        journeyRecyclerview.adapter = myloveitemAdapter
    }
}