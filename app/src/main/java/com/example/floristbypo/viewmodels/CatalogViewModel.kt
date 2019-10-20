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
    var items: LiveData<List<Catalog>> = _items

}