package com.sarang.myreview.ui.uistate

data class MyReviewUiState(
    val reviewId: Int,
    val restaurantId: Int,
    val restaurantName: String,
    val rating: Float,
    val isUploading: Boolean,
    val errorMsg: String,
    val selectedImagePath: ArrayList<String>, // 업로드 할 선택한 사진 리스트
)
