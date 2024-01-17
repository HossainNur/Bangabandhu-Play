package com.durbar.bangabandhuplay.ui.photo_gallery

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPhotoGalleryBinding
import com.squareup.picasso.Picasso


class PhotoGalleryFragment : Fragment(), PhotoGalleryAdapter.CallBack {
    private lateinit var binding: FragmentPhotoGalleryBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    private var photoGallery : Boolean = false
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
                    photoGallery = true
                    binding.photoGalleryRv.layoutManager = GridLayoutManager(requireContext(), 3)
                    binding.photoGalleryRv.adapter = PhotoGalleryAdapter(data, this)
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hideProgressBar() {
        if (photoGallery){
            binding.photoGalleryRv.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
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