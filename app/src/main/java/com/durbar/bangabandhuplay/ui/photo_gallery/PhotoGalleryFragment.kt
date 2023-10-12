package com.durbar.bangabandhuplay.ui.photo_gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.durbar.bangabandhuplay.MainActivity
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPhotoGalleryBinding
import com.durbar.bangabandhuplay.utils.Constants


class PhotoGalleryFragment : Fragment() {

    private lateinit var binding: FragmentPhotoGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        MainActivity.checkAppBarVisibility() //to check AppBar visibility is Visible or Gone

        binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}