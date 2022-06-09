package com.example.admin.util

import android.app.Dialog
import android.content.Context
import android.util.Log
import com.example.admin.R
import com.example.admin.data.ModelBarang

class Util {

}
fun log(message:Any){
    Log.d("LatansaAdmin",message.toString())
}

fun data(): ArrayList<ModelBarang>{
    val listBarang = ArrayList<ModelBarang>()
    listBarang.add(ModelBarang("1","kemeja",10000,"formal","kemeja formal premium",""))
    listBarang.add(ModelBarang("2","kemeja",10000,"formal","kemeja formal premium",""))
    listBarang.add(ModelBarang("3","kemeja",10000,"formal","kemeja formal premium",""))
    listBarang.add(ModelBarang("4","kemeja",10000,"formal","kemeja formal premium",""))
    listBarang.add(ModelBarang("5","kemeja",10000,"formal","kemeja formal premium",""))
return listBarang
}

class LoadingHelper(context: Context) {

    private val dialog = Dialog(context).apply {
        setContentView(R.layout.layout_loading) // TODO("Should Be Change to ViewBinding Inflation")
        setCancelable(false)
    }

    fun show() = show(null, null)

    fun show(title: String) = show(title, null)

    fun show(title: String?, desc: String?) {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

}
