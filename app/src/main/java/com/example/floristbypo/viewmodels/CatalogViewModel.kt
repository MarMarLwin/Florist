package com.example.floristbypo.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.Catalog

class CatalogViewModel(): ViewModel() {
    private val _items = MutableLiveData<List<Catalog>>().apply { value = emptyList() }
    val items: LiveData<List<Catalog>> = _items

    val name= MutableLiveData<String>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noCatalogLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int> = _noCatalogLabel

}