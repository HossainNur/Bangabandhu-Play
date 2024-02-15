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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.photo_gallery.Data
import com.durbar.bangabandhuplay.databinding.FragmentPhotoGalleryBinding
import com.durbar.bangabandhuplay.ui.photo_gallery.photo_gallery_details.PhotoGalleryDetailsFragment
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.durbar.bangabandhuplay.utils.checkInternet
import com.squareup.picasso.Picasso


class PhotoGalleryFragment : Fragment() {
    private lateinit var binding: FragmentPhotoGalleryBinding
    private lateinit var viewModel: PhotoGalleryViewModel
    private var photoGallery: Boolean = false
    private lateinit var navController: NavController
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
        requireContext().checkInternet()

        navController = Navigation.findNavController(view)
        viewModel.fetchGalleryPhotos().observe(viewLifecycleOwner) { data ->
            try {
                if (data != null && !data.isNullOrEmpty()) {
                    photoGallery = true
                    binding.photoGalleryRv.layoutManager = GridLayoutManager(requireContext(), 3)
                    binding.photoGalleryRv.adapter = PhotoGalleryAdapter(data){ passedPosition ->
                        //showDialog(title, image)

                        val bundle = Bundle().apply {
                            putInt("position", passedPosition)
                        }
                        //navController.navigate(navController, R.id.action_photoGalleryFragment_to_photoGalleryDetailsFragment, bundle)
                        navController.navigate(R.id.photoGalleryDetailsFragment,bundle)
                    }
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hideProgressBar() {
        if (photoGallery) {
            binding.photoGalleryRv.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun showDialog(title: String?, image: String?) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.photo_gallery_dialog)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
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