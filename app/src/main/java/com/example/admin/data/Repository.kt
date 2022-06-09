package com.example.admin.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.admin.util.LoadingHelper
import com.example.admin.util.log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    val realtime = FirebaseDatabase.getInstance().reference
    val firestore = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    fun regisFirebase(context: Context, email: String, password: String, task: Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Successful Create Account", Toast.LENGTH_SHORT).show()
                task
            } else {
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginFirebase(context: Context, email: String, password: String, task: Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
             if (it.isSuccessful) {
                log("Berhasil")
                 task
            } else {
                log("failed")
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    fun loginWithVeriFirebase(context: Context, email: String, password: String, task: Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                if (auth.currentUser!!.isEmailVerified) {
                    log("Berhasil")
                    task
                } else {
                    Toast.makeText(context, "Please Verified Your Email", Toast.LENGTH_SHORT).show()
                    auth.signOut()
                }
            } else {
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }
    fun setDataRealtime(context: Context, child: String, value: String) {
        realtime.child(child).setValue(value).addOnSuccessListener {
            Toast.makeText(context, "Success set Data", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteDataRealtime(context: Context, child: String) {
        realtime.child(child).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Success Delete Data", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    fun setDataFirestore(context: Context, collection: String, document: String, data: Any) {
        if (document == "") {
            firestore.collection(collection).document().set(data).addOnSuccessListener {
                Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }else{
            firestore.collection(collection).document(document).set(data).addOnSuccessListener {
                Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun setDataBarang(context: Context, data: Any, task: Unit){
//        val loading = LoadingHelper(context)
//        loading.show()
        log("repo set")
        val reff = FirebaseFirestore.getInstance().collection("Barang").document()
        data as ModelBarang
        val barang = ModelBarang(reff.id,data.namaBarang,data.hargaBarang,data.kategori,data.deskripsi,data.fotoProduk)
        reff.set(barang).addOnSuccessListener {
            Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            log("berhasil repo")
//            loading.dismiss()

        }.addOnFailureListener{
            log("failed")
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
//            loading.dismiss()
        }
    }

    fun getData():LiveData<MutableList<ModelBarang>>{
        val mutableList = MutableLiveData<MutableList<ModelBarang>>()
        FirebaseFirestore.getInstance().collection("Barang").get().addOnSuccessListener {
            val data = mutableListOf<ModelBarang>()
            it.forEach {
                val listData = it.toObject(ModelBarang::class.java)
                data.add(listData)
            }
            mutableList.value=data
        }
        return mutableList
    }

    fun updateData(modelBarang: ModelBarang,task: Unit){

        FirebaseFirestore.getInstance().collection("Barang").document(modelBarang.idBarang).set(modelBarang).
        addOnSuccessListener {
            task
            log("berhasil update barang dengan id")
        }.addOnFailureListener {
            log("gagal update barang dengan id ")
        }.addOnCompleteListener {
            log("berhasil update barang dengan id ")
        }
    }

   suspend fun deleteData(modelBarang: ModelBarang, task: Unit, context: Context){
        FirebaseFirestore.getInstance().collection("Barang").document(modelBarang.idBarang).delete().addOnSuccessListener {
            task
            Toast.makeText(context, "berhasil dihapus", Toast.LENGTH_SHORT).show()
        }
    }

//    fun getDataBarang():LiveData<MutableList<ModelBarang>>{
//
//    }
}
