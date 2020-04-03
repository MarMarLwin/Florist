package com.example.floristbypo.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.floristbypo.R
import com.example.floristbypo.databinding.CatalogItemBinding
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.repo.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalog_item.view.*

class CatalogAdapter:RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.catlogItemBinding.catalog= catalogList!![position]
        Picasso.get()
            .load(catalogList!![position].Photo_URL)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(holder.catlogItemBinding.imageView3)
    }

    private var catalogList: List<Catalog>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val catalogItemBinding= DataBindingUtil.inflate<CatalogItemBinding>(LayoutInflater.from(parent.context),R.layout.catalog_item,parent,false)

        catalogItemBinding.tableLayout.setOnClickListener {
            Utils.firebaseAnalytics.setUserProperty("favourite_flower",it.nameTxt.text.toString())
        }
        return ViewHolder(catalogItemBinding)
    }

    fun setCatalogList(catList:List<Catalog>?)
    {

        catalogList=catList

        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return catalogList!!.size
    }

    inner class ViewHolder(val catlogItemBinding: CatalogItemBinding) :
        RecyclerView.ViewHolder(catlogItemBinding.root)

    }



