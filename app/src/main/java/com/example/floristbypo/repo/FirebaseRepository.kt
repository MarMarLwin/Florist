package com.example.floristbypo.repo

import androidx.lifecycle.MutableLiveData
import com.example.floristbypo.models.Catalog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {
    val database= FirebaseDatabase.getInstance()
    private var cloud_items: MutableLiveData<List<Catalog>>? = null

    private fun getCatalog(){
        val database = FirebaseFirestore.getInstance()
        //catalogs=MutableLiveData<List<Catalog>>().apply { value = mutableListOf()}
        database.collection("Catalog")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    catalogs = MutableLiveData<List<Catalog>>().apply { value = result.toObjects(Catalog::class.java) }
                    var catalog=document.toObject(Catalog::class.java)
                    cloud_items = MutableLiveData<List<Catalog>>().apply { value = mutableListOf(catalog)}
                }

//           Log.d(TAG, "${document.id} => ${document.data}")

            }
            .addOnFailureListener { exception ->
                //                Log.w(TAG, "Error getting documents.", exception)
                cloud_items= MutableLiveData<List<Catalog>>().apply { value = mutableListOf()}
            }
    }
}