package com.sryang.myreview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sarang.myreview.MyReviewActivity
import com.sryang.myreview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMyReviewTestActivity.setOnClickListener {
            MyReviewActivity.go(this, 1, 541)
        }

        binding.btnPictureAdapter.setOnClickListener {
            startActivity(
                Intent(this, PictureAdapterActivity::class.java)
            )
        }
    }
}