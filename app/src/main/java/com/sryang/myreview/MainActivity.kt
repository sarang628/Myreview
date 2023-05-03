package com.sryang.myreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sryang.myreview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}