package com.sarang.myreview.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.databinding.ItemAddedPicture1Binding

class AddedPictureViewHolder(var itemAddPictureBinding: ItemAddedPicture1Binding) : RecyclerView.ViewHolder(
    itemAddPictureBinding.root
) {
    fun setPicture(picture: String?) {

    }

    companion object {
        fun create(parent: ViewGroup): AddedPictureViewHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding = ItemAddedPicture1Binding.inflate(inflator, parent, false)

            return AddedPictureViewHolder(binding)
        }
    }
}