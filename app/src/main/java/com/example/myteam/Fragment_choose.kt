package com.example.myteam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.annotations.Nullable
import kotlinx.android.synthetic.main.fragment_choose.*


class Fragment_choose : Fragment() {
    private lateinit var place_list: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose, container, false)
    }


    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sweepButton: Button = requireActivity().findViewById<View>(R.id.hotel_result_btn) as Button
        sweepButton.setOnClickListener(View.OnClickListener {
            var bundle = Bundle()
            val  three_star = three_star.isChecked
            if(three_star)
                bundle.putString("three_star",""+three_star)

            val  four_star = four_star.isChecked
            if(four_star)
                bundle.putString("four_star",""+four_star)

            val  five_star = five_star.isChecked
            if(five_star)
                bundle.putString("five_star",""+five_star)

            val  hotspring = hotspring.isChecked
            if(hotspring)
                bundle.putString("hotspring",""+hotspring)

            val  traffic = traffic.isChecked
            if(traffic)
                bundle.putString("traffic",""+traffic)

            val  child = child.isChecked
            if(child)
                bundle.putString("child",""+child)

            val  smart = smart.isChecked
            if(smart)
                bundle.putString("smart",""+smart)

            val  environment = environment.isChecked
            if(environment)
                bundle.putString("environment",""+environment)

            val  bussiness = bussiness.isChecked
            if(bussiness)
                bundle.putString("bussiness",""+bussiness)

            val  camp = camp.isChecked
            if(camp)
                bundle.putString("camp",""+camp)

            val  young_people = young_people.isChecked
            if(young_people)
                bundle.putString("young_people",""+young_people)

            val  sky = sky.isChecked
            if(sky)
                bundle.putString("sky",""+sky)

            val  onethousand_down = onethousand_down.isChecked
            if(onethousand_down)
                bundle.putString("onethousand_down",""+onethousand_down)
            val  onethousand_twothousand = onethousand_twothousand.isChecked
            if(onethousand_twothousand)
                bundle.putString("onethousand_twothousand",""+onethousand_twothousand)

            val  twothousand_threethousand = twothousand_threethousand.isChecked
            if(twothousand_threethousand)
                bundle.putString("twothousand_threethousand",""+twothousand_threethousand)

            val  threethousand_fourthousand = threethousand_fourthousand.isChecked
            if(threethousand_fourthousand)
                bundle.putString("threethousand_fourthousand",""+threethousand_fourthousand)

            val  fourthousand_fivethousand = fourthousand_fivethousand.isChecked
            if(fourthousand_fivethousand)
                bundle.putString("fourthousand_fivethousand",""+fourthousand_fivethousand)
            val  fivethousand_up = fivethousand_up.isChecked
            if(fivethousand_up)
                bundle.putString("fivethousand_up",""+fivethousand_up)


            var intent = activity?.getIntent()

            val sss= intent?.getStringExtra("begin_date")
            val eee= intent?.getStringExtra("end_date")
            val ddd= intent?.getStringExtra("destination")
            val nnn= intent?.getStringExtra("number_of_people")
            val begin_date = sss
            val end_date = eee
            val destination =ddd
            val number_of_people =nnn

            when(ddd) {
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


            bundle.putString("place_list",place_list)
            bundle.putString("begin_date",begin_date)
            bundle.putString("destination",destination)
            intent = Intent(activity, Hotelchoose_list::class.java)
            intent.putExtra("bundle",bundle)
            startActivity(intent)



            Toast.makeText(activity, "您的篩選結果"
                    + (if(three_star) "\n三星級" else "")
                    +(if(four_star) "\n四星級" else "")
                    + (if(five_star) "\n五星級" else "")
                    + (if(smart) "\n智能" else "")
                    + (if(environment) "\n環保" else "")
                    + (if(child) "\n親子" else "")
                    + (if(bussiness) "\n商務" else "")
                    + (if(hotspring) "\n溫泉" else "")
                    + (if(camp) "\n露營" else "")
                    + (if(sky) "\n星空" else "")
                    + (if(young_people) "\n青旅" else "")
                    + (if(traffic) "\n交通便利" else "")
                    + (if(onethousand_twothousand) "\n1000-2000" else "")
                    + (if(twothousand_threethousand) "\n2000-3000" else "")
                    + (if(threethousand_fourthousand) "\n3000-4000" else "")
                    + (if(fourthousand_fivethousand) "\n4000-5000" else "")
                    + (if(fivethousand_up) "\n5000以上" else "")
                    + (if(onethousand_down) "\n1000以下" else "")
                , Toast.LENGTH_SHORT).show()

            //從fragment跳轉到activity中
//            startActivity(Intent(activity, Hotelchoose_list::class.java))


        })

    }

}