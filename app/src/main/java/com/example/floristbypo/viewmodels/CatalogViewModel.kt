package com.example.floristbypo.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.models.User
import com.example.floristbypo.repo.CatalogRepository

//class CatalogViewModel(
//    private val catalogRepository: CatalogRepository
//): ViewModel() {
class CatalogViewModel:ViewModel(){
    private val _items = MutableLiveData<List<Catalog>>().apply { value = List<User() > }
    val items: LiveData<List<Catalog>> = _items
}