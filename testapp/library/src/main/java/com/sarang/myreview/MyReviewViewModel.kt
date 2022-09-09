package com.sarang.myreview

import androidx.lifecycle.*
import com.sryang.torang_core.data.model.*
import com.sryang.torang_core.repository.MyReviewRepository
import com.sarang.myreview.databinding.FragmentAddReviewBinding
import com.sryang.torang_core.data.entity.*
import com.sryang.torang_core.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyReviewFragment]
 * [FragmentAddReviewBinding]
 */
@HiltViewModel
class MyReviewViewModel @Inject constructor(private val repository: MyReviewRepository) :
    ViewModel() {
    var restaurantId: Int = 0

    var reviewId = MutableLiveData<Int>(-1)
    var _reviewId: LiveData<Int> = reviewId


    /** 리뷰가 업로드 중인지 상태 */
    private val _isUploaded = MutableLiveData<Boolean>().apply { value = false }
    val isUploaded: LiveData<Boolean> = _isUploaded

    private val _isUploading = MutableLiveData<Boolean>().apply { value = false }
    val isUploading: LiveData<Boolean> = _isUploading

    private val _clickSelectLocation = MutableLiveData<Event<Boolean>>()
    val clickSelectLocation: LiveData<Event<Boolean>> = _clickSelectLocation

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    fun clickSelectLocation() {
        _clickSelectLocation.postValue(Event(true))
    }

    /** 리뷰 내용 two-way binding */
    val contents = MutableLiveData<String>("")

    /** 평점 two-way binding */
    val rating = MutableLiveData<Float>()

    /** 업로드 할 식당 정보 */
    private val _selectedRestaurant = MutableLiveData<Restaurant>()
    val selectedRestaurant: LiveData<Restaurant> = _selectedRestaurant

    /** 업로드 된 리뷰를 불러올 때 사용 */
    @SuppressWarnings("todo delete")
    var myReview: LiveData<ReviewAndImage?> = reviewId.switchMap {
        repository.getMyReview(it)
    }

    /** 업로드 할 선택한 사진 리스트 */
    val selectedImagePath = MutableLiveData<ArrayList<String>>().apply {
        value = ArrayList()
    }

    /** 리뷰를 불러왔을 때 업로드 되었던 사진 리스트 */
    var uploadedPictures: MutableLiveData<ArrayList<ReviewImage>> = MutableLiveData()

    /** 리뷰를 불러왔을 때 업로드 되었던 사진 리스트 */
    var deleteuploadedPictures: MutableLiveData<ArrayList<ReviewImage>> =
        MutableLiveData(ArrayList())

    /**
     * 업로드 할 이미지 설정
     */
    fun setSelectedImagePath(imagePathes: ArrayList<String>) {
        selectedImagePath.postValue(imagePathes)
    }

    /**
     * 리뷰를 수정 할 경우 리뷰 불러오는 기능
     */
    fun loadReview(reviewId: Int) {
        this.reviewId.postValue(reviewId)
    }

    /**
     * 리뷰 업로드
     */
    fun uploadReview() {
        _isUploading.postValue(true)

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
            _isUploading.postValue(false)
        }
    }

    fun modifyReview() {
        _isUploading.postValue(true)

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
            _isUploading.postValue(false)
        }
    }

    fun selectRestaurant(restaurantId: Int) {
        this.restaurantId = restaurantId
        viewModelScope.launch {
            _selectedRestaurant.postValue(repository.getRestaurant(restaurantId))
        }
    }

    fun deleteUploadedPicture(reviewImage: ReviewImage) {
        deleteuploadedPictures.value?.let {
            if (!it.contains(reviewImage)) {
                it.add(reviewImage)
                deleteuploadedPictures.postValue(deleteuploadedPictures.value)
            } else {
                it.remove(reviewImage)
                deleteuploadedPictures.postValue(deleteuploadedPictures.value)
            }
        }
    }

    fun deleteUploadPicture(reviewImage: String) {
        selectedImagePath.value?.remove(reviewImage)
        selectedImagePath.postValue(selectedImagePath.value)
    }

    fun isDelete(reviewImage: ReviewImage): Boolean {
        var isContain = false
        deleteuploadedPictures.value?.let {
            isContain = it.contains(reviewImage)
        }

        return isContain
    }

}