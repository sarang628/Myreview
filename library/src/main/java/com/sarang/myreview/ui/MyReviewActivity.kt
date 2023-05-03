package com.sarang.myreview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.sarang.myreview.databinding.ActivityMyReviewBinding
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MyReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMyReviewBinding = ActivityMyReviewBinding.inflate(layoutInflater)
        setContentView(activityMyReviewBinding.root)
        setSupportActionBar(activityMyReviewBinding.tbMyReview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun go(context: Context, restaurantId: Int?, reviewId: Int) {
            context.startActivity(Intent(context, MyReviewActivity::class.java).apply {
                restaurantId?.let {
                    putExtra("restaurantId", it)
                }
                putExtra("reviewId", reviewId)
            })
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class MyReviewNavigationProvider {
    /*@Binds
    abstract fun provideAddReviewNavigation(myReviewNavigation: MyReviewNavigation): AddReviewNavigation*/
}