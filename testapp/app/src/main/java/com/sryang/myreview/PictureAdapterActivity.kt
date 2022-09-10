package com.sryang.myreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sarang.myreview.ui.adapter.AddPictureAdapter
import com.sryang.myreview.databinding.ActivityPictureAdapterBinding

class PictureAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPictureAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adapter = AddPictureAdapter()
        binding.images = ArrayList<String?>().apply {
            add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
            add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
            add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
            add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg/500px-Jennie_Kim_from_BLACKPINK_PUBG_210321_%28cropped%29.jpg")
        }
    }
}