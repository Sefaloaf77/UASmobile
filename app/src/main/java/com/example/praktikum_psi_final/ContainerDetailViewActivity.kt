package com.example.praktikum_psi_final

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.praktikum_psi_final.databinding.ActivityContainerDetailViewBinding

class ContainerDetailViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContainerDetailViewBinding
    private lateinit var navController:NavController
//    var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerDetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navHostFragment.navController
        navController = findNavController(R.id.nav_host_fragment)
    }
}