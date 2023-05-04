package com.sarang.myreview.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.sarang.myreview.databinding.FragmentAddReviewBinding
import com.sarang.myreview.ui.adapter.UploadedPictureAdapter
import com.sarang.myreview.ui.uistate.MyReviewUiState
import com.sarang.myreview.ui.uistate.testMyReviewUiState
import kotlinx.coroutines.flow.StateFlow
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
        val binding = FragmentAddReviewBinding.inflate(inflater, container, false)
        subScribeUiState(testMyReviewUiState(), binding)
        initEvent(binding)
        return binding.root
    }

    fun initEvent(binding: FragmentAddReviewBinding){
        binding.btnUpload.setOnClickListener { Snackbar.make(binding.root,"clickUpload", Snackbar.LENGTH_SHORT).show() }
        binding.btnAddImage.setOnClickListener { Snackbar.make(binding.root,"clickAddImage", Snackbar.LENGTH_SHORT).show() }
    }

    private fun subScribeUiState(
        uiState: StateFlow<MyReviewUiState>,
        binding: FragmentAddReviewBinding
    ) {
        lifecycleScope.launch {
            uiState.collect {
                it.rating?.let { binding.rbMyReivew.rating = it }
                binding.tvLocation.text = it.restaurantName
                binding.etContents.setText(it.contents)
                binding.pbMyReview.visibility = if(it.isUploading) View.VISIBLE else View.GONE
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