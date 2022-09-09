package com.sryang.myreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sarang.myreview.MyReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyReviewActivity.go(this, 1, 541)
    }
}