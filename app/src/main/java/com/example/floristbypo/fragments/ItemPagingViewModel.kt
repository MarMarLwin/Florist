package com.example.floristbypo.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.floristbypo.models.Item
import com.example.floristbypo.repo.ItemDataSource
import com.example.floristbypo.repo.ItemDataSourceFactory


class ItemPagingViewModel : ViewModel() {
    //creating livedata for PagedList  and PagedKeyedDataSource
    var itemPagedList: LiveData<PagedList<Item?>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>? = null

    //constructor
    init{
        val itemDataSourceFactory = ItemDataSourceFactory()
        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ItemDataSource.PAGE_SIZE).build()
        //Building the paged list
        itemPagedList =
            LivePagedListBuilder<Int, Item>(itemDataSourceFactory, pagedListConfig)
                .build()
    }
}
