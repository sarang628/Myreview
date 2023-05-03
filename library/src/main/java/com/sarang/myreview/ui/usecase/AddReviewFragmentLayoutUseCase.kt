package com.sarang.myreview.ui.usecase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.ui.viewholder.AddedPictureViewHolder
import com.sarang.myreview.ui.viewholder.AddingPictureViewHolder
import kotlinx.coroutines.flow.MutableStateFlow

data class AddReviewFragmentLayoutUseCase(
    val rating: MutableStateFlow<Float>,
    val contents: MutableStateFlow<String>,
    val clickSendListenr: View.OnClickListener,
    val clickAddImage: View.OnClickListener,
    val pictureAdapterUseCase: PictureAdapterUseCase,
    val uploadedAdapterUseCase: UploadedAdapterUseCase,
    val restaurantName: String,
    val isUploading: Boolean
)

val AddReviewFragmentLayoutUseCase.isEnabledSend: Boolean get() = false

data class PictureAdapterUseCase(
    val adapter: RecyclerView.Adapter<AddingPictureViewHolder>,
    val images: ArrayList<String>
)

data class UploadedAdapterUseCase(
    val uploadAdapter: RecyclerView.Adapter<AddedPictureViewHolder>
)