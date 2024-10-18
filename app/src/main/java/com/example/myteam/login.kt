package com.example.myteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var auth: FirebaseAuth

        setContentView(R.layout.activity_login)

        //login按鈕進入行程介面
        val LButton = findViewById<Button>(R.id.l_btn)
        //handle onClick
        LButton.setOnClickListener {
            //intent to start NewActivity
            startActivity(Intent(this@login, input_schedule::class.java))
        }
        val FButton = findViewById<Button>(R.id.forget_pwd_btn)
        //handle onClick
        FButton.setOnClickListener {
            //intent to start NewActivity
            startActivity(Intent(this@login, forget_pwd::class.java))
        }
    }
//    forget_pwd
//class login : AppCompatActivity() {
//
//
//    }
//    private lateinit var accountEditText: EditText
//    private lateinit var passwordEditText: EditText
//    private  lateinit var loginButton: Button
//    private  lateinit var forgetPasswordButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        accountEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
//        passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
//        loginButton = findViewById(R.id.login_btn)
//        forgetPasswordButton = findViewById(R.id.button2)
//    }
}



