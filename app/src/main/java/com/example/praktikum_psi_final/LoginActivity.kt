package com.example.praktikum_psi_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        var tvRegister = findViewById<TextView>(R.id.tv_register)
        var btLogin = findViewById<Button>(R.id.bt_login)

        tvRegister.setOnClickListener { newRegister() }
        btLogin.setOnClickListener { signIn() }


    }

    fun newRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity((intent))
    }

    fun signIn() {
        var email = findViewById<EditText>(R.id.et_log_email).text.toString()
        var password = findViewById<EditText>(R.id.et_log_password).text.toString()
        auth = FirebaseAuth.getInstance()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Main", "Failed Login : ${it.message}")
                Toast.makeText(this, "Email or Password incorrect", Toast.LENGTH_SHORT).show()
            }


    }
}