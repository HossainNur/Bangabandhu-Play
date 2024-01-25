package com.durbar.bangabandhuplay.ui.live

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ActivityLiveBinding
import com.durbar.bangabandhuplay.ui.photo_gallery.PhotoGalleryAdapter

class LiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)
    }

    /*private fun getActiveLiveStreaming() {
        viewModel.liveStreaming.observe(this,{data ->
            try {
                if ()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }*/


}