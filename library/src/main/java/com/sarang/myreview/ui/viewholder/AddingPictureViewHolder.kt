package com.sarang.myreview.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.databinding.ItemAddPicture1Binding

class AddingPictureViewHolder(var itemAddPictureBinding: ItemAddPicture1Binding) : RecyclerView.ViewHolder(
    itemAddPictureBinding.root
) {
    fun setPicture(picture: String) {
        itemAddPictureBinding.picture = picture
    }

    companion object {
        fun create(parent: ViewGroup): AddingPictureViewHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding = ItemAddPicture1Binding.inflate(inflator, parent, false)
            return AddingPictureViewHolder(binding)
        }
    }
}