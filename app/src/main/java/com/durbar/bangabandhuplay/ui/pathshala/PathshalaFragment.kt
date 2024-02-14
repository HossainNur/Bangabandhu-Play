package com.durbar.bangabandhuplay.ui.pathshala

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.durbar.bangabandhuplay.data.ApiService
import com.durbar.bangabandhuplay.data.model.pathshala.PathshalaResponse
import com.durbar.bangabandhuplay.databinding.FragmentPathshalaBinding
import com.durbar.bangabandhuplay.utils.Constants
import com.durbar.bangabandhuplay.utils.checkInternet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PathshalaFragment : Fragment() {
    private lateinit var binding: FragmentPathshalaBinding
    private lateinit var viewModel: PathshalaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPathshalaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PathshalaViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.progressBar.visibility = View.VISIBLE

        viewModel.fetchPdfs().observe(viewLifecycleOwner) {pathshala ->
            try {
                if (pathshala?.data != null){
                    val concatAdapter = ConcatAdapter()
                    pathshala.data?.data?.forEach {
                        concatAdapter.addAdapter(CommonAdapter(it))
                    }
                    binding.rcvPathshala.adapter = concatAdapter
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}