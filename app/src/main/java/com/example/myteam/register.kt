package com.example.myteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


class register : AppCompatActivity() {

    /*lateinit var auth: FirebaseAuth
    val databaseReference : DatabaseReference? = null
    val database : FirebaseDatabase? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /*auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")*/
    }

    /*private fun register(){

        val inputEmail : EditText = findViewById(R.id.register_email)
        val inputPwd : EditText = findViewById(R.id.register_pwd)

        registerButton.setClickListener {

            if(TextUtils.isEmpty(inputEmail.toString()))
            {
                inputEmail.setError("Please enter Email")
                return@setClickListener
            }
            else if(TextUtils.isEmpty(inputPwd.toString()))
            {
                inputEmail.setError("Please enter password")
                return@setClickListener
            }

            auth.createUserWithEmailAndPassword(inputEmail.toString(), inputPwd.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful)
                    {
                        val currentUser = auth.currentUser

                    }
                    else
                    {
                        Toast.makeText(this@register, "Register failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }*/
}