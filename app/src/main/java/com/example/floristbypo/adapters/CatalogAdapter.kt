package com.example.floristbypo.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.floristbypo.databinding.CatalogItemBinding
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.viewmodels.CatalogViewModel

class CatalogAdapter(private val catalogViewModel: CatalogViewModel):
ListAdapter<Catalog,CatalogAdapter.ViewHolder>(TaskDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(catalogViewModel,item)
    }

    class ViewHolder private constructor(val binding: CatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CatalogViewModel, item: Catalog) {

            binding.viewModel = viewModel
            binding.catalog = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatalogItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
class TaskDiffCallback : DiffUtil.ItemCallback<Catalog>() {
    override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
        return oldItem.CatalogId == newItem.CatalogId
    }

    override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
        return oldItem == newItem
    }
}


