package com.sarang.myreview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.sarang.instagralleryModule.InstagramGalleryContract
import com.sarang.myreview.databinding.FragmentAddReview1Binding
import com.sarang.myreview.ui.adapter.AddPictureAdapter
import com.sarang.myreview.ui.adapter.UploadedPictureAdapter
import com.sarang.myreview.ui.usecase.AddReviewFragmentLayoutUseCase
import com.sarang.myreview.ui.usecase.PictureAdapterUseCase
import com.sarang.myreview.ui.usecase.UploadedAdapterUseCase
import com.sarang.myreview.ui.viewmodel.MyReviewViewModel
import com.sryang.torang_core.data.entity.ReviewImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UploadedPictureAdapter]
 * [AddingPictureViewHolder]
 * [UploadedPictureAdapter]
 * [FragmentAddReview1Binding]
 * [MyReviewViewModel]
 */
@AndroidEntryPoint
class MyReviewFragment : Fragment() {
    @Inject
    lateinit var findRestaurantNavigation: FindRestaurantNavigation

    /* 내리뷰 뷰모델 */
    private val mViewModel: MyReviewViewModel by viewModels()

    private val getContent = registerForActivityResult(InstagramGalleryContract()) { intent ->
        intent?.getStringArrayListExtra("pictures")?.let {
            mViewModel.setSelectedImagePath(it)
        }
    }

    private val layoutUsecase by lazy {
        MutableStateFlow(
            AddReviewFragmentLayoutUseCase(
                rating = MutableStateFlow(3f),
                contents = MutableStateFlow(""),
                clickSendListenr = { send() },
                uploadedAdapterUseCase = UploadedAdapterUseCase(
                    uploadAdapter = UploadedPictureAdapter(),
                    images = ArrayList<ReviewImage>().apply {
                        add(ReviewImage(0, 0))
                        add(ReviewImage(0, 0))
                        add(ReviewImage(0, 0))
                    }
                ),
                clickAddImage = { getContent.launch("a") },
                pictureAdapterUseCase = PictureAdapterUseCase(
                    adapter = AddPictureAdapter(),
                    images = ArrayList<String>().apply {
                        add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
                        add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
                        add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
                        add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
                    }
                ),
                restaurantName = "abcd",
                isUploading = false
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // 바인딩 초기
        val binding = FragmentAddReview1Binding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                subScribeLayoutUseCase(layoutUsecase)
                mViewModel.subScribeUiState(layoutUsecase)
            }
        getReviewId()?.let { mViewModel.loadReview(it) } // 리뷰 불러오기
        getRestaurantId()?.let { mViewModel.selectRestaurant(it) } // 식당 불러오기
        return binding.root
    }

    private fun MyReviewViewModel.subScribeUiState(
        useCase: MutableStateFlow<AddReviewFragmentLayoutUseCase>
    ) {
        viewModelScope.launch {
            uiState.collect() {
                layoutUsecase.update {
                    it.copy() //update layoutUsecase
                }
            }
        }
    }

    private fun FragmentAddReview1Binding.subScribeLayoutUseCase(
        useCase: MutableStateFlow<AddReviewFragmentLayoutUseCase>
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            useCase.collect {
                this@subScribeLayoutUseCase.useCase = it
            }
        }
    }

    private fun getReviewId(): Int? {
        var reviewId: Int? = null

        activity?.intent?.let {
            reviewId = it.getIntExtra("reviewId", -1)
            if (reviewId == -1)
                reviewId = null
        }
        return reviewId
    }

    private fun getRestaurantId(): Int? {
        var restaurantId: Int? = null

        activity?.intent?.let {
            restaurantId = it.getIntExtra("restaurantId", -1)
            if (restaurantId == -1)
                restaurantId = null
        }
        return restaurantId
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
}

private fun send() {
}

interface FindRestaurantNavigation {
    fun go(activity: AppCompatActivity)
}