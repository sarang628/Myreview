package com.sryang.myreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sryang.myreview.databinding.ActivityPictureAdapterBinding

class PictureAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPictureAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}