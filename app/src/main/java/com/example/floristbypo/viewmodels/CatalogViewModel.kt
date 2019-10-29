package com.example.floristbypo.viewmodels
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.repo.CatalogRepository
import com.google.firebase.firestore.FirebaseFirestore

//class CatalogViewModel(
//    private val catalogRepository: CatalogRepository
//): ViewModel() {
class CatalogViewModel:ViewModel() {
    private val _items = MutableLiveData<List<Catalog>>().apply {
        value =
            mutableListOf(
                Catalog(
                    "c01", "Tulip", "Tuplip", "White", 700.0, 20.0,
                    "White in color.Height is 110 cm.No scent.It contain 4 or 5 buds."
                ),
                Catalog(
                    "c02", "Honesty", "Lily(LA)", "Orange", 1300.0, 4.0,
                    "Orange in color.Height 130 cm, 3-5 buds. "
                )
            )
    }

    var items: LiveData<List<Catalog>> = MutableLiveData<List<Catalog>>()//= _items
    var item:Catalog = Catalog()

    fun getCatalogs(): LiveData<List<Catalog>>{
//        if(items.value!=null){
            val database = FirebaseFirestore.getInstance()
            //catalogs=MutableLiveData<List<Catalog>>().apply { value = mutableListOf()}
            database.collection("Catalog")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
//                    catalogs = MutableLiveData<List<Catalog>>().apply { value = result.toObjects(Catalog::class.java) }
                        var catalog=document.toObject(Catalog::class.java)
                        items.apply { catalog }
                    }

//           Log.d(TAG, "${document.id} => ${document.data}")

                }
                .addOnFailureListener { exception ->
                    //                Log.w(TAG, "Error getting documents.", exception)

                    items.apply { listOf(Catalog()) }
                }
//        }

        return  items
    }


}