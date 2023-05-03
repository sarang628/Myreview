package com.sarang.myreview.ui.viewmodel

import androidx.lifecycle.*
import com.sarang.myreview.ui.uistate.MyReviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyReviewFragment]
 * [FragmentAddReviewBinding]
 */
@HiltViewModel
class MyReviewViewModel @Inject constructor() :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        MyReviewUiState(
            reviewId = 0,
            restaurantId = 0,
            restaurantName = "abc",
            rating = 0f,
            isUploading = false,
            errorMsg = "",
            selectedImagePath = ArrayList()
        )
    )

    val uiState: StateFlow<MyReviewUiState> = _uiState

    /**
     * 업로드 할 이미지 설정
     */
    fun setSelectedImagePath(imagePathes: ArrayList<String>) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedImagePath = imagePathes
                )
            }
        }
    }

    /**
     * 리뷰를 수정 할 경우 리뷰 불러오는 기능
     */
    fun loadReview(reviewId: Int) {

    }

    /**
     * 리뷰 업로드
     */
    fun uploadReview() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isUploading = true
                )
            }
        }

        viewModelScope.launch {
            /*val reviewAndImage = ReviewAndImage(
                Feed(
                    userId = repository.userId1(),
                    restaurant_id = restaurantId,
                    contents = contents.value,
                    rating = rating.value
                ),
                ArrayList<ReviewImage>().apply {
                    for (path in selectedImagePath.value!!)
                        add(ReviewImage.uploadParam(path))
                },
                User(repository.userId1())
            )
            try {
                repository.uploadReview(reviewAndImage)
                _isUploaded.postValue(true)

            } catch (e: Exception) {
                _errorMsg.postValue(Event(e.message.toString()))
            }*/

            _uiState.update {
                it.copy(
                    isUploading = false
                )
            }
        }
    }

    fun modifyReview() {
        _uiState.update {
            it.copy(
                isUploading = true
            )
        }

        viewModelScope.launch {
            /*val reviewAndImage = ReviewAndImage(
                Feed(
                    review_id = this@MyReviewViewModel.reviewId.value!!,
                    userId = repository.userId1(),
                    restaurant_id = restaurantId,
                    contents = contents.value,
                    rating = rating.value
                ),
                ArrayList<ReviewImage>().apply {
                    for (path in selectedImagePath.value!!)
                        add(ReviewImage.uploadParam(path))
                },
                User(repository.userId1())
            )

            val modifyFeedData = ModifyFeedData(reviewAndImage, deleteuploadedPictures.value)

            try {
                repository.modifyReview(modifyFeedData)
                _isUploaded.postValue(true)

            } catch (e: Exception) {
                _errorMsg.postValue(Event(e.message.toString()))
            }*/
            _uiState.update {
                it.copy(
                    isUploading = false
                )
            }
        }
    }

    fun selectRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    restaurantId = restaurantId
                )
            }
        }
    }

    fun deleteUploadPicture(reviewImage: String) {
        /*selectedImagePath.value?.remove(reviewImage)
        selectedImagePath.postValue(selectedImagePath.value)*/
    }
}