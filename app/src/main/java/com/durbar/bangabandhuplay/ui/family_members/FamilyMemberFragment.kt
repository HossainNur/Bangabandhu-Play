package com.durbar.bangabandhuplay.ui.family_members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding


class FamilyMemberFragment : Fragment() {

    private lateinit var binding: FragmentFamilyMemberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvFamilyMember.layoutManager = GridLayoutManager(requireContext(),3)
        val imageResIds = listOf(
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,
          R.drawable.dhaka_attack,

        ) // Replace with your image resource IDs
        val adapter = FamilyMemberAdapter(requireContext(), imageResIds)
        binding.rcvFamilyMember.adapter = adapter
    }
}