package com.example.screen_myreview_and_map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.torang_core.navigation.AddReviewNavigation
import com.sarang.screen_myreview.FindRestaurantNavigation
import com.sarang.screen_myreview.MyReviewActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MyReviewMapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_reviewactivity)
    }

    companion object {
        fun go(context: Context, restaurantId: Int? = -1, reviewId: Int = -1) {

            context.startActivity(
                Intent(context, MyReviewMapActivity::class.java).apply {
                    putExtra("mode", 1)
                    restaurantId?.let {
                        putExtra("restaurantId", it)
                    }
                    putExtra("reviewId", reviewId)
                }
            )
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class FindRestaurantNavigationModule {
    @Binds
    abstract fun provideFindRestaurantNavigation(findNavigationNavigationImpl: FindNavigationNavigationImpl)
            : FindRestaurantNavigation
}

class FindNavigationNavigationImpl @Inject constructor() : FindRestaurantNavigation {
    override fun go(activity: AppCompatActivity) {
        activity.supportFragmentManager.findFragmentById(R.id.fc)
            ?.findNavController()?.navigate(R.id.action_myReviewFragment_to_findFragment2)
    }
}

class MyReviewNavigation @Inject constructor() : AddReviewNavigation {
    override fun go(context: Context, restaurantId: Int?, reviewId: Int) {
        MyReviewMapActivity.go(context, restaurantId, reviewId)
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class MyReviewNavigationProvider {
    @Binds
    abstract fun provideAddReviewNavigation(myReviewNavigation: MyReviewNavigation): AddReviewNavigation
}