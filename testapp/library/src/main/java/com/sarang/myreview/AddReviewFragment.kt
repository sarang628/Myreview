package com.sarang.myreview

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sryang.torang_core.util.EventObserver
import com.sryang.torang_core.util.Logger
import com.sarang.instagralleryModule.InstagramGalleryContract
import com.sarang.myreview.databinding.FragmentAddReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [UploadedPicRvadt]
 * [AddPicHolder]
 * [UploadedPicRvadt]
 * [FragmentAddReviewBinding]
 * [MyReviewViewModel]
 */
@AndroidEntryPoint
class MyReviewFragment : Fragment() {
    @Inject
    lateinit var findRestaurantNavigation: FindRestaurantNavigation

    /** 공유 뷰모델 */
    //private val mapSharedViewModel: MapSharedViewModel by activityViewModels()

    /* 내리뷰 뷰모델 */
    private val mViewModel: MyReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Logger.v("enter")
        // 바인딩 초기화
        val binding = FragmentAddReviewBinding.inflate(layoutInflater, container, false)
            .apply {
                // 뷰모델 설정
                viewModel = mViewModel
                // 공유 뷰모델 설정
                //mapSharedViewModel = mapSharedViewModel
                // lifecyclerowner 설정
                lifecycleOwner = viewLifecycleOwner

                // 업로드된 이미지 불러오는 아답터 설정
                rvUploadedPictures.adapter = UploadedPicRvadt(mViewModel)

                // 업로드 할 이미지 불러오는 아답터 설정
                rvMyReivew.adapter = AddPicRvadt(mViewModel)

                // 사진 업로드 모듈 contract 초기화
                val getContent = registerForActivityResult(InstagramGalleryContract()) {
                    it?.getStringArrayListExtra("pictures")?.also {
                        Logger.d(it.toString())
                        mViewModel.setSelectedImagePath(it)
                    }
                }

                // 사진 업로드 모듈 호출
                button2.setOnClickListener {
                    getContent.launch("a")
                }
            }


        // 리뷰 불러오기
        getReviewId()?.let {
            mViewModel.loadReview(it)
        }

        // 식당 불러오기
        getRestaurantId()?.let {
            mViewModel.selectRestaurant(it)
        }

        // UI 구독
        subscribeUi(binding)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentAddReviewBinding) {
        mViewModel.myReview?.observe(viewLifecycleOwner, {
            it?.let {
                binding.review = it
                it.review?.let {
                    binding.editText.setText(it.contents)
                }
                it.review?.rating?.let {
                    binding.ratingBar3.rating = it
                }
                mViewModel.uploadedPictures.postValue(ArrayList(it.images))
            }
        })

        mViewModel.selectedImagePath.observe(viewLifecycleOwner, {
            (binding.rvMyReivew.adapter as AddPicRvadt).setImages(it)
        })
        mViewModel.uploadedPictures.observe(viewLifecycleOwner, {
            (binding.rvUploadedPictures.adapter as UploadedPicRvadt).setPictures(
                it
            )
        })

        mViewModel.deleteuploadedPictures.observe(viewLifecycleOwner, {
            (binding.rvUploadedPictures.adapter as UploadedPicRvadt).notifyDataSetChanged()
        })

        mViewModel.isUploaded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    requireActivity().finish()
            }
        })

        mViewModel.clickSelectLocation.observe(viewLifecycleOwner, EventObserver {
            findRestaurantNavigation.go(requireActivity() as AppCompatActivity)
        })

        /*mapSharedViewModel.selectedRestaurant.observe(viewLifecycleOwner) {

        }*/

        mViewModel.errorMsg.observe(viewLifecycleOwner, EventObserver {
            AlertDialog.Builder(requireContext())
                .setMessage(it)
                .show()
        })
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

interface FindRestaurantNavigation {
    fun go(activity: AppCompatActivity)
}