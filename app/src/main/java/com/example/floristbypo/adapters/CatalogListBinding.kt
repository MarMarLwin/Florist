package com.example.floristbypo.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.floristbypo.models.Catalog

@BindingAdapter("app:catalogs")
fun setCatalogs(listView:RecyclerView, items:List<Catalog>)
{
    (listView.adapter as CatalogAdapter).submitList(items)
}