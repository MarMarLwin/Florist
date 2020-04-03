package com.example.floristbypo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.floristbypo.R
import com.example.floristbypo.models.Item

class ItemAdapter(context: Context?) : PagedListAdapter<Item,ItemAdapter.ItemViewHolder>(ItemDffCallBack()) {

    private var mCtx: Context? = null

    init {
        mCtx=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(mCtx).inflate(R.layout.item_row_layout, parent, false)
        return ItemViewHolder(view)    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.textView.text = item.owner!!.display_name
            Glide.with(mCtx)
                .load(item.owner.profile_image)
                .into(holder.imageView)
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show()
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = itemView.findViewById(R.id.textViewName)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    class ItemDffCallBack:DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.question_id == newItem.question_id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.equals(newItem)
        }

    }

}