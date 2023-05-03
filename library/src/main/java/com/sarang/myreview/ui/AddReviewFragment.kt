package com.sarang.myreview.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sarang.myreview.databinding.FragmentAddReview1Binding
import com.sarang.myreview.ui.adapter.UploadedPictureAdapter
import com.sarang.myreview.ui.usecase.AddReviewFragmentLayoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * layout - [FragmentAddReview1Binding]
 * [UploadedPictureAdapter]
 * [AddingPictureViewHolder]
 * [UploadedPictureAdapter]
 * [MyReviewViewModel]
 */
//@AndroidEntryPoint
class MyReviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // 바인딩 초기
        val binding = FragmentAddReview1Binding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
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