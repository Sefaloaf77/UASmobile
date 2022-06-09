package com.example.praktikum_psi_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

private val homeFragment = HomeFragment()
private val categoryFragment = CategoryFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginCheck()

        replaceFragment(homeFragment)

        var bnMain = findViewById<BottomNavigationView>(R.id.bn_main)
        var fabLogout = findViewById<FloatingActionButton>(R.id.fab_logout)

        fabLogout.setOnClickListener { logout() }


        bnMain.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item_home -> replaceFragment(homeFragment)
                R.id.item_category -> replaceFragment(categoryFragment)

            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_main, fragment)
            transaction.commit()
        }
    }

    fun logout(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun loginCheck(){
        val user = FirebaseAuth.getInstance().currentUser
        if(user == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity((intent))
        }
    }
}