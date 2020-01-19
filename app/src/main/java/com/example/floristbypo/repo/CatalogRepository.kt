package com.example.floristbypo.repo
import android.os.Build.VERSION_CODES.P
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.floristbypo.models.Catalog
import com.google.firebase.firestore.FirebaseFirestore

class CatalogRepository {

    private val itemList: MutableLiveData<List<Catalog>?> =MutableLiveData()

    fun getFirebaseCatalogList():LiveData<List<Catalog>?> = itemList

    fun fetchCataLogList(): MutableLiveData<List<Catalog>?> {
        val database = FirebaseFirestore.getInstance()

        val item:MutableList<Catalog> = mutableListOf()
            database.collection("Catalog")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val catalog=document.toObject(Catalog::class.java)

                     item.add(catalog)
//                    itemList.postValue(P)
                }
                itemList.postValue(item)
            }
            .addOnFailureListener { exception ->
                itemList.apply { listOf(Catalog()) }
            }
        return  itemList
    }

}