package com.sarang.myreview.ui.usecase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.ui.viewholder.AddingPictureViewHolder
import com.sarang.myreview.ui.viewholder.AddedPictureViewHolder
import kotlinx.coroutines.flow.MutableStateFlow

data class AddReviewFragmentLayoutUseCase(
    val rating: MutableStateFlow<Float>,
    val contents: MutableStateFlow<String>,
    val clickSendListenr: View.OnClickListener,
    val uploadAdapter: RecyclerView.Adapter<AddedPictureViewHolder>,
    val adapter: RecyclerView.Adapter<AddingPictureViewHolder>,
    val clickAddImage: View.OnClickListener
)