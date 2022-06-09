package com.example.praktikum_psi_final

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()


        var tvLogin = findViewById<TextView>(R.id.tv_login)
        var btRegister = findViewById<Button>(R.id.bt_register)

        tvLogin.setOnClickListener { newLogin() }
        btRegister.setOnClickListener { signUp() }

    }

    fun newLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity((intent))
    }

    fun signUp() {
        val email = findViewById<EditText>(R.id.et_reg_email).text.toString()
        val password = findViewById<EditText>(R.id.et_reg_password).text.toString()
        val cpPassword = findViewById<EditText>(R.id.et_reg_cppassword).text.toString()
        auth = FirebaseAuth.getInstance()


        if (cpPassword != password) {
            Toast.makeText(this, "Please check your password again", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(this, "Succesfully New Account Create", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed. Maybe account be used", Toast.LENGTH_SHORT).show()

                }
            }
    }

}