package com.sarang.myreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sarang.myreview.databinding.ActivityMyReviewBinding

//@AndroidEntryPoint
class MyReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMyReviewBinding = ActivityMyReviewBinding.inflate(layoutInflater)
    }
}