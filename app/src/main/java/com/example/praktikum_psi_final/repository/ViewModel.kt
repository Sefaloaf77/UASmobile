package com.example.praktikum_psi_final.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class ViewModel(application: Application): AndroidViewModel(application) {
    private val categoryRepository:CategoryRepository = CategoryRepository()

    fun setDataBarang(context: Context, modelBarang: ModelBarang, task:Unit){
            categoryRepository.setDataBarang(context, modelBarang, task)
    }

    fun getDataBarang():LiveData<MutableList<ModelBarang>>{
        return categoryRepository.getData()
    }

    fun findProductByCetegory(category: String): LiveData<MutableList<ModelBarang>> {
        return categoryRepository.findProductByCategory(category)
    }
}