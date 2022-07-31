package com.sarang.screen_myreview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import com.sarang.screen_myreview.databinding.ItemAddPictureBinding
import com.sarang.screen_myreview.databinding.ItemAddedPictureBinding

class AddedPicHolder(var itemAddPictureBinding: ItemAddedPictureBinding) : RecyclerView.ViewHolder(
    itemAddPictureBinding.root
) {
    fun setPicture(picture: ReviewImage?) {
        itemAddPictureBinding.picture = picture
    }

    companion object {
        fun create(parent: ViewGroup, myreviewViewModel: MyReviewViewModel): AddedPicHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding = ItemAddedPictureBinding.inflate(inflator, parent, false).apply {
                viewmodel = myreviewViewModel
            }

            return AddedPicHolder(binding)
        }
    }
}