package com.sarang.myreview.ui.uistate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MyReviewUiState(
    val reviewId: Int? = null,
    val restaurantId: Int? = null,
    val restaurantName: String? = null,
    val rating: Float? = null,
    val isUploading: Boolean = false,
    val errorMsg: String? = null,
    val selectedImagePath: ArrayList<String>? = null,
    val contents: String? = null,
    val clickLocation: ((Int) -> Unit)? = null,
    val addImage: ((Int) -> Unit)? = null
)


fun testMyReviewUiState(): StateFlow<MyReviewUiState> {
    return MutableStateFlow(
        MyReviewUiState(
            rating = 3.0f,
            restaurantName = "맥도날드",
            contents = "베이컨 토마토 디럭스 버거"
        )
    )
}