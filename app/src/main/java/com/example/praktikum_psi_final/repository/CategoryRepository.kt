package com.example.praktikum_psi_final.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class CategoryRepository {

    val database = FirebaseDatabase.getInstance().reference
    fun setDataBarang(context: Context, data: Any, task: Unit) {
        val reff = FirebaseFirestore.getInstance().collection("Barang").document()
        data as ModelBarang
        val barang = ModelBarang(
            reff.id,
            data.namaBarang,
            data.hargaBarang,
            data.kategori,
            data.deskripsi,
            data.fotoProduk
        )
        reff.set(barang).addOnSuccessListener {
            Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun getData(): LiveData<MutableList<ModelBarang>> {
        val mutableList = MutableLiveData<MutableList<ModelBarang>>()
        FirebaseFirestore.getInstance().collection("Barang").get().addOnSuccessListener {
            val data = mutableListOf<ModelBarang>()
            it.forEach {
                val listData = it.toObject(ModelBarang::class.java)
                data.add(listData)
            }
            mutableList.value = data
        }
        return mutableList
    }

    fun findProductByCategory(category: String): LiveData<MutableList<ModelBarang>> {
        val mutableList = MutableLiveData<MutableList<ModelBarang>>()
        FirebaseFirestore.getInstance().collection("Barang")
            .whereEqualTo("kategori", category.lowercase()).get().addOnSuccessListener {
                val data = mutableListOf<ModelBarang>()
                it.forEach {
                    val listData = it.toObject(ModelBarang::class.java)
                    data.add(listData)
                }
                mutableList.value = data
            }

        Log.d("#category", category)
        return mutableList
    }
}