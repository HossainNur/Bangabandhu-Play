package com.durbar.bangabandhuplay.ui.photo_gallery.photo_gallery_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.FragmentPhotoGalleryDetailsBinding
import com.durbar.bangabandhuplay.ui.photo_gallery.PhotoGalleryAdapter
import com.durbar.bangabandhuplay.ui.photo_gallery.PhotoGalleryViewModel

class PhotoGalleryDetailsFragment : Fragment() {

    private lateinit var binding : FragmentPhotoGalleryDetailsBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoGalleryDetailsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val photoList = it.getParcelableArrayList<Data>("photoList")
            val position = it.getInt("position")
            if (photoList != null){
                setSlider(photoList,position)
            }
        }


    }

    private fun setSlider(data: List<Data>,position: Int) {
        binding.photoGallerySliderVp.adapter = PhotoGalleryDetailsSliderAdapter(data)
        binding.photoGallerySliderVp.clipToPadding = false
        binding.photoGallerySliderVp.clipChildren = false
        binding.photoGallerySliderVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.photoGallerySliderVp.currentItem = position
    }

}