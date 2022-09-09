package com.sarang.myreview

import android.content.Context
import android.content.Intent
import com.sryang.torang_core.navigation.WriteReviewNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class FindRestaurantModule {
    @Binds
    abstract fun provideWriteReviewNavigation(testWriteReviewNavigation: WriteReviewNavigationImpl): WriteReviewNavigation
}


// 테스트 리뷰 작성 이동 내비게이션
class WriteReviewNavigationImpl @Inject constructor() : WriteReviewNavigation {
    override fun go(context: Context, reviewId: Int) {
        context.startActivity(Intent(context, MyReviewActivity::class.java).apply {
            putExtra("restaurantId", reviewId)
        })
    }
}