package com.sryang.myreviewtestapp

import androidx.appcompat.app.AppCompatActivity
import com.sarang.screen_myreview.FindRestaurantNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class TestModule {
    @Binds
    abstract fun provideFindRestaurantNavigation(testFindRestaurantNavigation: TestFindRestaurantNavigation): FindRestaurantNavigation

}

class TestFindRestaurantNavigation @Inject constructor() : FindRestaurantNavigation {
    override fun go(activity: AppCompatActivity) {

    }
}