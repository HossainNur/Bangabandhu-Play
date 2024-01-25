package com.durbar.bangabandhuplay.ui.pathshala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPathshalaBinding


class PathshalaFragment : Fragment() {

    private lateinit var binding: FragmentPathshalaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPathshalaBinding.inflate(layoutInflater)
        return binding.root
    }

}