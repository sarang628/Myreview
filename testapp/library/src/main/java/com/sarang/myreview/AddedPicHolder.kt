package com.sarang.myreview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.databinding.ItemAddedPictureBinding
import com.sryang.torang_core.data.entity.ReviewImage

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