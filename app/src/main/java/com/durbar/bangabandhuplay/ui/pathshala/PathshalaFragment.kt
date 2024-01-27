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

       /* viewModel.fetchPdfs().observe(viewLifecycleOwner) {datax ->
            try {
                // concat adapter
                val concatAdapter = ConcatAdapter()

                it.data?.data?.forEach {
                    concatAdapter.addAdapter(CommonAdapter(it))
                }
                binding.rcvPathshala.adapter = concatAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }*/


        val  retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_LIVE) // Replace with your actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.pathsahlaPdf

        call.enqueue(object : Callback<PathshalaResponse> {
            override fun onResponse(call: Call<PathshalaResponse>, response: Response<PathshalaResponse>) {
                if (response.isSuccessful && response.body() != null && response.body()?.data != null) {

                    binding.progressBar.visibility = View.GONE

                    val responseData = response.body()

                    responseData?.let {
                        if (it.status == true) {
                            //    val ebooksList = it.data?.data?.first()?.ebooks as? List<Ebook>

                            // concat adapter
                            val concatAdapter = ConcatAdapter()

                            it.data?.data?.forEach {
                                concatAdapter.addAdapter(CommonAdapter(it))
                            }
                            binding.rcvPathshala.adapter = concatAdapter

                        }
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<PathshalaResponse>, t: Throwable) {
                // Handle failure, such as network issues
                Log.e("pathshala", "pathshala API call failed. ${t.message}")
            }
        })
    }

}