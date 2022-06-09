package com.example.admin.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.admin.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class ViewModel(application: Application):AndroidViewModel(application) {
//    val readAllLatihan: LiveData<MutableList<ModelBarang>>
    private val repository: Repository = Repository()

    fun login(user:String, pass:String, context: Context, t:Unit){
        repository.loginFirebase(context,user,pass,t)
    }
    fun setDataBarang(context: Context,modelBarang: ModelBarang, task:Unit){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setDataBarang(context, modelBarang, task)
        }
    }

    fun getDataBarang():LiveData<MutableList<ModelBarang>>{
        return repository.getData()
    }
    fun updtaeBarang(modelBarang: ModelBarang,task: Unit){
        repository.updateData(modelBarang, task)

    }
    fun deleteBarang(modelBarang: ModelBarang, task: Unit, context: Context){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteData(modelBarang, task, context)
        }
    }
}