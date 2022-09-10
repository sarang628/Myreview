package com.sarang.myreview.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.ui.viewholder.AddingPictureViewHolder
import com.sryang.torang_core.util.Logger.d
import java.util.*

class AddPictureAdapter constructor() :
    RecyclerView.Adapter<AddingPictureViewHolder>() {
    var imagePathes = ArrayList<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddingPictureViewHolder {
        return AddingPictureViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AddingPictureViewHolder, position: Int) {
        holder.itemAddPictureBinding.picture = imagePathes[position]
    }

    override fun getItemCount(): Int {
        return imagePathes.size
    }

    fun setImages(imagePathes: ArrayList<String>) {
        d(imagePathes.toString())
        this.imagePathes = imagePathes
        notifyDataSetChanged()
    }
}