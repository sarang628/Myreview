package com.sarang.myreview.ui.uistate

import com.sryang.torang_core.data.entity.ReviewImage

data class MyReviewUiState(
    val reviewId: Int,
    val restaurantId: Int,
    val restaurantName: String,
    val rating: Float,
    val isUploading: Boolean,
    val errorMsg: String,
    val selectedImagePath: ArrayList<String>, // 업로드 할 선택한 사진 리스트
    val uploadedPictures : ArrayList<ReviewImage>, // 리뷰를 불러왔을 때 업로드 되었던 사진 리스트
    val deleteuploadedPictures : ArrayList<ReviewImage>
)
