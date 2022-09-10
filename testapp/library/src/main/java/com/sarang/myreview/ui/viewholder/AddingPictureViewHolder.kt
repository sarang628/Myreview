package com.sarang.myreview.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.databinding.ItemAddPictureBinding

class AddingPictureViewHolder(var itemAddPictureBinding: ItemAddPictureBinding) : RecyclerView.ViewHolder(
    itemAddPictureBinding.root
) {
    fun setPicture(picture: String) {
        itemAddPictureBinding.picture = picture
    }

    companion object {
        fun create(parent: ViewGroup): AddingPictureViewHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding = ItemAddPictureBinding.inflate(inflator, parent, false)
            return AddingPictureViewHolder(binding)
        }
    }
}