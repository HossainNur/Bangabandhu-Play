package live.durbar.bangabandhuapp.ui.photo_gallery.photo_gallery_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import live.durbar.bangabandhuapp.data.model.photo_gallery.Data
import live.durbar.bangabandhuapp.databinding.FragmentPhotoGalleryDetailsBinding
import live.durbar.bangabandhuapp.ui.photo_gallery.PhotoGalleryViewModel

class PhotoGalleryDetailsFragment : Fragment() {

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

                        if (position > -1)
                            setSlider(dataList!!,position)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    private fun setSlider(data: List<Data>,position: Int) {

        val callBacLeft : (Int) -> Unit ={
            if (binding.photoGallerySliderVp.currentItem > 0) {
                binding.photoGallerySliderVp.currentItem = binding.photoGallerySliderVp.currentItem - 1
            }
        }
        val callBackRight : (Int) -> Unit = {
            if (binding.photoGallerySliderVp.currentItem < dataList!!.size - 1) {
                binding.photoGallerySliderVp.currentItem = binding.photoGallerySliderVp.currentItem + 1
            }
        }

        binding.photoGallerySliderVp.adapter = PhotoGalleryDetailsSliderAdapter(data, callBacLeft, callBackRight)

        binding.photoGallerySliderVp.clipToPadding = false
        binding.photoGallerySliderVp.clipChildren = false
        binding.photoGallerySliderVp.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.photoGallerySliderVp.currentItem = position
    }

}