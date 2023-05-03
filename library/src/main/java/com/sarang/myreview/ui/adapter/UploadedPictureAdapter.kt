package com.sarang.myreview.ui.adapter

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.ui.viewholder.AddedPictureViewHolder
import java.util.*

class UploadedPictureAdapter : RecyclerView.Adapter<AddedPictureViewHolder>() {
    private var pictures: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedPictureViewHolder {
        return AddedPictureViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AddedPictureViewHolder, position: Int) {
        holder.setPicture(pictures[position])
        /*if (pictures.get(position).menu != null && pictures.get(position).menu.menu_name != null)
            ((TextView) holder.itemView.findViewById(R.id.tv_menu_name)).setText("" + pictures.get(position).menu.menu_name);*/
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    fun setPictures(imagePathes: List<String>?) {
        if (imagePathes != null) {
            pictures = imagePathes
            notifyDataSetChanged()
        }
    }
}

object UploadedBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:uploadedPicturedata")
    fun setAddpter(
        recyclerView: RecyclerView, data: ArrayList<String>?
    ) {
        recyclerView.adapter?.let { adapter ->
            data?.let {
                (adapter as UploadedPictureAdapter).setPictures(it)
            }
        }
    }
}