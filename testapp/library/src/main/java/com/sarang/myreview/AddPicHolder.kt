package com.sarang.myreview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.databinding.ItemAddPictureBinding

class AddPicHolder(var itemAddPictureBinding: ItemAddPictureBinding) : RecyclerView.ViewHolder(
    itemAddPictureBinding.root
) {
    fun setPicture(picture: String) {
        itemAddPictureBinding.picture = picture
    }

    companion object {
        fun create(parent: ViewGroup, myreviewViewModel: MyReviewViewModel): AddPicHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding = ItemAddPictureBinding.inflate(inflator, parent, false).apply {
                viewmodel = myreviewViewModel
            }

            return AddPicHolder(binding)
        }
    }
}