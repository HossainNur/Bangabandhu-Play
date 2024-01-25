package com.durbar.bangabandhuplay.ui.tunes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.ActivityPlayerBinding
import com.durbar.bangabandhuplay.databinding.FragmentTunesBinding


class TunesFragment : Fragment() {

    private lateinit var binding: FragmentTunesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTunesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}