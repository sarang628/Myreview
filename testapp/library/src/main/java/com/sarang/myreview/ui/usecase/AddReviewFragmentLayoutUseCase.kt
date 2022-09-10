package com.sarang.myreview.ui.usecase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sarang.myreview.AddPicHolder
import com.sarang.myreview.AddedPicHolder
import kotlinx.coroutines.flow.MutableStateFlow

data class AddReviewFragmentLayoutUseCase(
    val rating: MutableStateFlow<Float>,
    val contents: MutableStateFlow<String>,
    val clickSendListenr: View.OnClickListener,
    val uploadAdapter: RecyclerView.Adapter<AddedPicHolder>,
    val adapter: RecyclerView.Adapter<AddPicHolder>,
    val clickAddImage: View.OnClickListener
)