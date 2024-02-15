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

class PhotoGalleryDetailsFragment : Fragment(),PhotoGalleryDetailsSliderAdapter.CallBack {

    private lateinit var binding : FragmentPhotoGalleryDetailsBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    private var dataList : List<Data>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoGalleryDetailsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.fetchGalleryPhotos().observe(viewLifecycleOwner) { data ->
            try {
                if (data != null && !data.isNullOrEmpty()) {
                    dataList = data
                    arguments?.let {
                        val position = it.getInt("position")
                            setSlider(dataList!!,position)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    private fun setSlider(data: List<Data>,position: Int) {
        binding.photoGallerySliderVp.adapter = PhotoGalleryDetailsSliderAdapter(data,this)
        binding.photoGallerySliderVp.clipToPadding = false
        binding.photoGallerySliderVp.clipChildren = false
        binding.photoGallerySliderVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.photoGallerySliderVp.currentItem = position
    }

    override fun photoGalleryLeftClick(position: Int) {
        if (binding.photoGallerySliderVp.currentItem > 0) {
            binding.photoGallerySliderVp.currentItem = binding.photoGallerySliderVp.currentItem - 1
        }
    }

    override fun photoGalleryRightClick(position: Int) {
        if (binding.photoGallerySliderVp.currentItem < dataList!!.size - 1) {
            binding.photoGallerySliderVp.currentItem = binding.photoGallerySliderVp.currentItem + 1
        }
    }

}