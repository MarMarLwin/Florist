package com.example.floristbypo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.floristbypo.R
import com.example.floristbypo.adapters.ItemAdapter
import com.example.floristbypo.models.Item
import kotlinx.android.synthetic.main.item_paging_fragment.*


class ItemPagingFragment : Fragment() {

    companion object {
        fun newInstance() = ItemPagingFragment()
    }

    private lateinit var viewModel: ItemPagingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_paging_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemPagingViewModel::class.java)

        //creating the Adapter
        val adapter = ItemAdapter(this.context)
        itemRecyclerView.layoutManager = LinearLayoutManager(this.context)
        itemRecyclerView.setHasFixedSize(true)

        //observing the itemPagedList from view model
        viewModel.itemPagedList?.observe(this.viewLifecycleOwner,
            Observer<PagedList<Item?>?> { items ->
                if(items!=null)
                adapter.submitList(items)
            })

        //setting the adapter
        itemRecyclerView.adapter=adapter

    }

}
