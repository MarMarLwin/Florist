package com.example.floristbypo.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.repo.CatalogRepository

class CatalogViewModel:ViewModel() {
    private val _items = MutableLiveData<List<Catalog>?>().apply { value = mutableListOf() }

    var items: MutableLiveData<List<Catalog>?> = _items
    private val repository:CatalogRepository = CatalogRepository()

    val itemList:LiveData<List<Catalog>?> = repository.fetchCataLogList()



}