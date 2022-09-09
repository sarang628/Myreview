package com.sarang.myreview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sryang.torang_core.util.Logger.d
import java.util.*

class AddPicRvadt constructor(val myReviewViewModel: MyReviewViewModel) :
    RecyclerView.Adapter<AddPicHolder>() {
    var imagePathes = ArrayList<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddPicHolder {
        return AddPicHolder.create(parent, myReviewViewModel)
    }

    override fun onBindViewHolder(holder: AddPicHolder, position: Int) {
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