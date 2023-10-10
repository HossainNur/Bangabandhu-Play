package com.durbar.bangabandhuplay.ui.family_members

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding


class FamilyMemberFragment : Fragment() {

    private lateinit var binding: FragmentFamilyMemberBinding

    private lateinit var familyMemberViewModel: FamilyMemberViewModel
    private lateinit var adapter: FamilyMemberAdapter
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



       /* binding.rcvFamilyMember.layoutManager = GridLayoutManager(requireContext(),3)
        val imageResIds = listOf(
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,

        ) // Replace with your image resource IDs
        val adapter = FamilyMemberAdapter(requireContext(), imageResIds)
        binding.rcvFamilyMember.adapter = adapter*/

        familyMemberViewModel = ViewModelProvider(this).get(FamilyMemberViewModel::class.java)

        familyMemberViewModel.getPhotos()?.observe(viewLifecycleOwner) { data ->
            data?.let {
                binding.rcvFamilyMember.layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = FamilyMemberAdapter(it)
                binding.rcvFamilyMember.adapter = adapter
            }
        }

        familyMemberViewModel.getPhotos()
    }

    override fun onResume() {
        super.onResume()
    }
}