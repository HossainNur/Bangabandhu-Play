package com.durbar.bangabandhuplay.ui.photo_gallery

import android.app.Dialog
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPhotoGalleryBinding
import com.squareup.picasso.Picasso


class PhotoGalleryFragment : Fragment(), PhotoGalleryAdapter.CallBack {
    private lateinit var binding: FragmentPhotoGalleryBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchGalleryPhotos().observe(viewLifecycleOwner) { data ->
            try {
                if (data != null && !data.isNullOrEmpty()) {
                    binding.photoGalleryRv.layoutManager = GridLayoutManager(requireContext(), 3)
                    binding.photoGalleryRv.adapter = PhotoGalleryAdapter(data, this)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun photoGalleryDetails(
        title: String, image: String
    ) {
        showDialog(title, image)
    }

    private fun showDialog(title: String?, image: String?) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.photo_gallery_dialog)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        val dialogTextview = dialog.findViewById<TextView>(R.id.title_text)
        val dialogImageView = dialog.findViewById<ImageView>(R.id.gallery_image)

        if (title != null) {
            dialogTextview.text = title
        }

        if (image != null) {
            Picasso.get().load(image).into(dialogImageView)
        }

        dialog.show()

    }

}