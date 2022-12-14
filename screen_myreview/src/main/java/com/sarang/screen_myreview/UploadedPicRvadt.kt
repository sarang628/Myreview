package com.sarang.screen_myreview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import java.util.*

class UploadedPicRvadt(private val viewmodel: MyReviewViewModel) : RecyclerView.Adapter<AddedPicHolder>() {
    private var pictures: List<ReviewImage> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedPicHolder {
        return AddedPicHolder.create(parent, viewmodel)
    }

    override fun onBindViewHolder(holder: AddedPicHolder, position: Int) {
        holder.setPicture(pictures[position])
        /*if (pictures.get(position).menu != null && pictures.get(position).menu.menu_name != null)
            ((TextView) holder.itemView.findViewById(R.id.tv_menu_name)).setText("" + pictures.get(position).menu.menu_name);*/
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    fun setPictures(imagePathes: List<ReviewImage>?) {
        if (imagePathes != null) {
            pictures = imagePathes
            notifyDataSetChanged()
        }
    }
}