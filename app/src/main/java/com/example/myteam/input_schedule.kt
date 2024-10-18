package com.example.myteam


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_input_schedule.*
import java.util.*

class input_schedule : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    val database = Firebase.database

    private lateinit var destination: String
    private lateinit var number_of_people: String

    private lateinit var begin_date: String
    private lateinit var end_date: String


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_schedule)


        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //check my trip button
        checkmytrip.setOnClickListener{

        }

        //handle click, logout usertent
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        logoutBtn.setOnClickListener{
            Firebase.auth.signOut()
            googleSignInClient.signOut()
            startActivity(Intent(this@input_schedule, MainActivity::class.java))
        }

        //get destination and number of people
        aSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                destination = parent?.selectedItem.toString()
            }
        }
        bSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                number_of_people = parent?.selectedItem.toString()
            }
        }


        //input month and day
        val c =Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)

        val Pickbegin_Button = findViewById<Button>(R.id.pickBeginDateBtn)
        //handle onClick
        Pickbegin_Button.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                val mm = mMonth+1
                Pickbegin_Button.setText("" + mYear + "/" + mm + "/" + mDay)
                begin_date = mYear.toString() +","+ mm.toString() +","+ mDay.toString()
            },year,month,day)

            dpd.show()
        }

        val Pickend_Button = findViewById<Button>(R.id.pickEndDateBtn)
        Pickend_Button.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                val mmm = mMonth+1
                Pickend_Button.setText("" + mYear + "/" + mmm + "/" + mDay)
                end_date = mYear.toString() +","+ mmm.toString() +","+ mDay.toString()
            },year,month,day)

            dpd.show()
        }

        val SButton = findViewById<Button>(R.id.submitmytrip)
        SButton.setOnClickListener {

            //在資料庫建立行程
            write_into_firebase(destination, number_of_people, begin_date ,end_date)

            //在 activities 間傳輸資料

            //有改
            val intent = Intent(this@input_schedule,hotel_main::class.java)
            val begin_date = begin_date
            val end_date = end_date
            val destination = destination
            val  number_of_people =  number_of_people
            intent.putExtra("begin_date",begin_date)
            intent.putExtra("end_date",end_date)
            intent.putExtra("destination",destination)
//            intent.putExtra("number_of_people",number_of_people)
            //有改

            //intent to start NewActivity
            startActivity(intent)
        }

        val checkButton = findViewById<Button>(R.id.checkmytrip)
        checkButton.setOnClickListener {

            startActivity(Intent(this@input_schedule, mySchedule::class.java))
        }
    }

    private fun checkUser()
    {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            //user not logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            //user logged in
            //get user info
            val email = firebaseUser.email
        }
    }

    private fun write_into_firebase(desti:String, people:String, begin_date:String, end_date:String)
    {
        val journey = mutableMapOf("destination" to desti, "number_of_people" to people, "begin_date" to begin_date, "end_date" to end_date)
        val title = begin_date + desti
        database.getReference("journey").child(title).setValue(journey).addOnSuccessListener {
            Toast.makeText(this, "Sucessfully saved",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()
        }
    }

}
